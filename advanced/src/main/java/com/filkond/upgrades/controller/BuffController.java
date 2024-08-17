package com.filkond.upgrades.controller;

import com.filkond.upgrades.configuration.UpgradeType;
import com.filkond.upgrades.configuration.buff.UpgradeBuff;
import com.filkond.upgrades.utils.BuffCollector;

import java.util.Set;

public interface BuffController<E> {
    <T extends UpgradeBuff<?>> Set<T> getLevelBuffs(E entity, String buffId, Class<T> clazz, UpgradeType type);

    <T extends UpgradeBuff<?>> Set<T> getLevelBuffs(E entity, String buffId, Class<T> clazz);

    <T extends UpgradeBuff<?>> Set<T> getAllBuffs(E entity, String buffId, Class<T> clazz, UpgradeType type);

    <T extends UpgradeBuff<?>> Set<T> getAllBuffs(E entity, String buffId, Class<T> clazz);

    <T extends UpgradeBuff<?>> BuffCollector<T> getCollector(String buffId, Class<T> clazz, boolean certainLevel, UpgradeType... exceptions);
}
