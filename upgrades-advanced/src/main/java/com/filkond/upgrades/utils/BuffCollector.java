package com.filkond.upgrades.utils;

import com.filkond.upgrades.configuration.UpgradeType;
import com.filkond.upgrades.configuration.buff.UpgradeBuff;
import com.filkond.upgrades.holder.AdvancedHolder;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
public class BuffCollector<T> {
    private final Class<T> clazz;
    private final String buffId;
    private final boolean certainLevel;
    private final Collection<? extends UpgradeType> toSearch;
    @Builder.Default
    private final Collection<? extends UpgradeType> exceptions = new HashSet<>();

    @SuppressWarnings("unchecked")
    public Set<UpgradeBuff<T>> collect(AdvancedHolder<?> holder) {
        return toSearch.stream()
                .flatMap(type -> {
                    if (certainLevel ^ exceptions.contains(type)) return type.getLevels().stream().filter(level -> holder.getLevel(type) == level.getIntValue());
                    return type.getLevels().stream().filter(level -> holder.getLevel(type) >= level.getIntValue());
                })
                .flatMap(level -> level.getBuffs().stream())
                .filter(buff -> buffId.equals(buff.getId()))
                .filter(upgradeBuff -> upgradeBuff.getValue().getClass().isInstance(clazz))
                .map(upgradeBuff -> (UpgradeBuff<T>) upgradeBuff)
                .collect(Collectors.toSet());
    }
}
