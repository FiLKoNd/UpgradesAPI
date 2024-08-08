package com.filkond.upgrades.configuration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Optional;
import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
public class UpgradeType {
    private final String id;
    private final Set<UpgradeLevel> levels;

    public Optional<UpgradeLevel> getLevel(int level) {
        return levels.stream().filter(l -> l.getLevel() == level).findFirst();
    }
}
