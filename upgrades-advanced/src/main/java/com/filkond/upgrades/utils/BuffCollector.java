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
    private final Collection<? extends UpgradeType> reversed = new HashSet<>();
    @Builder.Default
    private final Collection<? extends UpgradeType> exclude = new HashSet<>();
    @Builder.Default
    private final Collection<? extends UpgradeType> include = new HashSet<>();

    @SuppressWarnings("unchecked")
    public Set<UpgradeBuff<T>> collect(AdvancedHolder<?> holder) {
        if (!exclude.isEmpty() && !include.isEmpty()) {
            throw new IllegalArgumentException("Cannot use both exclude and include");
        }
        return toSearch.stream()
                .filter(type -> !exclude.contains(type) || include.contains(type))
                .flatMap(type -> {
                    if (certainLevel ^ reversed.contains(type))
                        return type.getLevels().stream().filter(level -> holder.getLevel(type) == level.getIntValue());
                    return type.getLevels().stream().filter(level -> holder.getLevel(type) >= level.getIntValue());
                })
                .flatMap(level -> level.getBuffs().stream())
                .filter(buff -> buffId.equals(buff.getId()))
                .filter(upgradeBuff -> clazz.isInstance(upgradeBuff.getValue()))
                .map(upgradeBuff -> (UpgradeBuff<T>) upgradeBuff)
                .collect(Collectors.toSet());
    }
}
