package com.filkond.upgrades.controller;

import com.filkond.upgrades.configuration.UpgradeType;
import com.filkond.upgrades.configuration.buff.UpgradeBuff;
import com.filkond.upgrades.db.DatabaseCredentials;
import com.filkond.upgrades.holder.SimpleUpgradeHolder;
import lombok.val;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AdvancedController<E> extends UpgradeController {
    public AdvancedController(DatabaseCredentials credentials) {
        super(credentials);
    }

    public Optional<SimpleUpgradeHolder> getHolder(final E entity) {
        return getHolder(getUniqueId(entity));
    }

    public SimpleUpgradeHolder getHolderOrDefault(final E entity) {
        return getHolderOrDefault(getUniqueId(entity));
    }

    public <T extends UpgradeBuff<?>> Set<T> getLevelBuffs(final E entity, String buffId, Class<T> clazz, UpgradeType type) {
        val holder = getHolderOrDefault(entity);
        return type.getLevels().stream()
                .filter(level -> holder.getLevel(type) == level.getIntValue())
                .flatMap(level -> level.getBuffs().stream())
                .filter(buff -> buffId.equals(buff.getId()))
                .filter(clazz::isInstance)
                .map(clazz::cast)
                .collect(Collectors.toSet());
    }

    public <T extends UpgradeBuff<?>> Set<T> getLevelBuffs(final E entity, String buffId, Class<T> clazz) {
        return getUpgradeTypes().stream()
                .flatMap(type -> getLevelBuffs(entity, buffId, clazz, type).stream())
                .collect(Collectors.toSet());
    }

    public <T extends UpgradeBuff<?>> Set<T> getAllBuffs(final E entity, String buffId, Class<T> clazz, UpgradeType type) {
        val holder = getHolderOrDefault(entity);
        return type.getLevels().stream()
                .filter(level -> holder.getLevel(type) <= level.getIntValue())
                .flatMap(level -> level.getBuffs().stream())
                .filter(buff -> buffId.equals(buff.getId()))
                .filter(clazz::isInstance)
                .map(clazz::cast)
                .collect(Collectors.toSet());
    }

    public <T extends UpgradeBuff<?>> Set<T> getAllBuffs(final E entity, String buffId, Class<T> clazz) {
        return getUpgradeTypes().stream()
                .flatMap(type -> getAllBuffs(entity, buffId, clazz, type).stream())
                .collect(Collectors.toSet());
    }

    public Optional<UpgradeType> getUpgradeType(final String id) {
        return getUpgradeTypes().stream().filter(type -> type.getId().equals(id)).findFirst();
    }

    public abstract UUID getUniqueId(E entity);
    public abstract Set<UpgradeType> getUpgradeTypes();
}
