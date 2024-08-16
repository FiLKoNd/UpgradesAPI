package com.filkond.upgrades_example;

import com.filkond.upgrades.holder.AdvancedHolder;
import com.filkond.upgrades.holder.SimpleUpgradeHolder;
import com.filkond.upgrades.holder.dao.UpgradeHolderDAOImpl;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@DatabaseTable(tableName = "upgrade_holders", daoClass = UpgradeHolderDAOImpl.class)
public class PlayerHolder extends SimpleUpgradeHolder implements AdvancedHolder<Player> {
    @Override
    public @Nullable Player getEntity() {
        return Bukkit.getPlayer(getUniqueId());
    }
}
