package com.filkond.upgrades.controller;

import com.filkond.upgrades.configuration.UpgradeType;
import com.filkond.upgrades.holder.AdvancedHolder;

import java.util.*;

public interface AdvancedController<H extends AdvancedHolder<E>, E, T extends UpgradeType> extends BuffController<E> {
    Optional<H> getHolder(E entity);

    H getHolderOrDefault(E entity);

    Optional<T> getUpgradeType(String id);

    H getDefaultHolder(final E entity);

    Set<T> getUpgradeTypes();
}
