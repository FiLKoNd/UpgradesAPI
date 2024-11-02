package com.filkond.upgrades.controller;

import com.filkond.upgrades.datasource.UpgradesDataSource;
import com.filkond.upgrades.holder.UpgradeHolder;
import lombok.*;

import java.util.*;

@Getter
@RequiredArgsConstructor
public class UpgradeControllerImpl<H extends UpgradeHolder> implements UpgradeController<H> {
    private final UpgradesDataSource<H> dataSource;
    protected final Set<H> holders = new HashSet<>();
    private final Class<H> holderClass;

    @Override
    @SneakyThrows
    public void initialize() {
        holders.addAll(dataSource.getHolders());
    }

    @Override
    @SneakyThrows
    public void save() {
        for (H holder : holders) dataSource.createOrUpdate(holder);
    }
}
