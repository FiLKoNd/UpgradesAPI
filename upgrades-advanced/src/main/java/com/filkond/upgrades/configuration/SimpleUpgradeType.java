package com.filkond.upgrades.configuration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Comparator;
import java.util.Optional;
import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
public class SimpleUpgradeType implements UpgradeType {
    private final String id;
    private final Set<UpgradeLevel> levels;

    @Override
    public Optional<UpgradeLevel> getMaxLevel() {
        return levels.stream().max(Comparator.comparingInt(UpgradeLevel::getIntValue));
    }

    @Override
    public Optional<UpgradeLevel> getLevel(int level) {
        return levels.stream().filter(l -> l.getIntValue() == level).findFirst();
    }
}
