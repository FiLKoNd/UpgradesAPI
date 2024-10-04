package com.filkond.upgrades.controller;

import com.filkond.upgrades.configuration.UpgradeType;
import com.filkond.upgrades.configuration.buff.UpgradeBuff;
import com.filkond.upgrades.utils.BuffCollector;

import java.util.Set;

public interface BuffController<E> {
    <T> Set<UpgradeBuff<T>> getLevelBuffs(E entity, String buffId, Class<T> clazz, UpgradeType type);

    <T> Set<UpgradeBuff<T>> getLevelBuffs(E entity, String buffId, Class<T> clazz);

    <T> Set<UpgradeBuff<T>> getAllBuffs(E entity, String buffId, Class<T> clazz, UpgradeType type);

    <T> Set<UpgradeBuff<T>> getAllBuffs(E entity, String buffId, Class<T> clazz);

    <T> BuffCollector<T> getCollector(String buffId, Class<T> clazz, boolean certainLevel, UpgradeType... exceptions);
}
