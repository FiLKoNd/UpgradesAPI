package com.filkond.upgrades.configuration.buff;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpgradeBuff<V> {
    private final String id;
    private final V value;
}
