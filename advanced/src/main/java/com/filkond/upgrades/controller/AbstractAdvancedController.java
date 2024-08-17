package com.filkond.upgrades.controller;

import com.filkond.upgrades.configuration.UpgradeType;
import com.filkond.upgrades.utils.BuffCollector;
import com.filkond.upgrades.configuration.buff.UpgradeBuff;
import com.filkond.upgrades.db.DatabaseCredentials;
import com.filkond.upgrades.holder.AdvancedHolder;
import lombok.val;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractAdvancedController<H extends AdvancedHolder<E>, E> extends UpgradeControllerImpl<H>
        implements AdvancedController<H, E>, BuffController<E> {
    public AbstractAdvancedController(Class<H> holderClass) {
        super(holderClass);
    }

    public AbstractAdvancedController(Class<H> holderClass, @NotNull DatabaseCredentials credentials) {
        super(holderClass, credentials);
    }

    @Override
    public Optional<H> getHolder(final E entity) {
        return getHolders().stream()
                .filter(holder -> entity.equals(holder.getEntity()))
                .findFirst();
    }

    @Override
    public H getHolderOrDefault(final E entity) {
        return getHolder(entity).orElseGet(() -> {
            val defaultHolder = getDefaultHolder(entity);
            getHolders().add(defaultHolder);
            return defaultHolder;
        });
    }

    @Override
    public <T extends UpgradeBuff<?>> Set<T> getLevelBuffs(final E entity, String buffId, Class<T> clazz, UpgradeType type) {
        val holder = getHolderOrDefault(entity);
        return getCollector(buffId, clazz, true, type).collect(holder);
    }

    @Override
    public <T extends UpgradeBuff<?>> Set<T> getLevelBuffs(final E entity, String buffId, Class<T> clazz) {
        return getUpgradeTypes().stream()
                .flatMap(type -> getLevelBuffs(entity, buffId, clazz, type).stream())
                .collect(Collectors.toSet());
    }

    @Override
    public <T extends UpgradeBuff<?>> Set<T> getAllBuffs(final E entity, String buffId, Class<T> clazz, UpgradeType type) {
        val holder = getHolderOrDefault(entity);
        return getCollector(buffId, clazz, true, type).collect(holder);
    }

    @Override
    public <T extends UpgradeBuff<?>> Set<T> getAllBuffs(final E entity, String buffId, Class<T> clazz) {
        return getUpgradeTypes().stream()
                .flatMap(type -> getAllBuffs(entity, buffId, clazz, type).stream())
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<? extends UpgradeType> getUpgradeType(final String id) {
        return getUpgradeTypes().stream().filter(type -> type.getId().equals(id)).findFirst();
    }

    @Override
    public <T extends UpgradeBuff<?>> BuffCollector<T> getCollector(String buffId, Class<T> clazz, boolean certainLevel, UpgradeType... exceptions) {
        return BuffCollector.<T>builder()
                .clazz(clazz)
                .buffId(buffId)
                .certainLevel(certainLevel)
                .toSearch(getUpgradeTypes())
                .exceptions(Arrays.stream(exceptions).collect(Collectors.toSet()))
                .build();
    }
}
