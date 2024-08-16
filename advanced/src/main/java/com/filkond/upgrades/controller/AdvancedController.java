package com.filkond.upgrades.controller;

import com.filkond.upgrades.configuration.UpgradeType;
import com.filkond.upgrades.configuration.buff.UpgradeBuff;
import com.filkond.upgrades.holder.AdvancedHolder;
import lombok.val;  

import java.util.*;
import java.util.stream.Collectors;

public interface AdvancedController<H extends AdvancedHolder<E>, E>{
    Optional<H> getHolder(E entity);

    H getHolderOrDefault(E entity);

    <T extends UpgradeBuff<?>> Set<T> getLevelBuffs(E entity, String buffId, Class<T> clazz, UpgradeType type);

    <T extends UpgradeBuff<?>> Set<T> getLevelBuffs(E entity, String buffId, Class<T> clazz);

    <T extends UpgradeBuff<?>> Set<T> getAllBuffs(E entity, String buffId, Class<T> clazz, UpgradeType type);

    <T extends UpgradeBuff<?>> Set<T> getAllBuffs(E entity, String buffId, Class<T> clazz);

    Optional<UpgradeType> getUpgradeType(String id);

    H getDefaultHolder(final E entity);

    Set<UpgradeType> getUpgradeTypes();
}
