package com.filkond.upgrades_example.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import com.filkond.upgrades.configuration.SimpleUpgradeType;
import com.filkond.upgrades.configuration.UpgradeType;
import com.filkond.upgrades_example.PlayerUpgradesController;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("upgrades|up")
public class UpgradeExecutor extends BaseCommand {
    private final PlayerUpgradesController controller;

    public UpgradeExecutor(PlayerUpgradesController controller) {
        this.controller = controller;
    }

    @Subcommand("set")
    @CommandCompletion("@players @types @level")
    @Syntax("<player> <upgrade-type> <upgrade-level>")
    public void set(Player sender,
                    @Name("target") OnlinePlayer target,
                    @Name("upgrade-type") UpgradeType upgradeType,
                    @Name("upgrade-level") Integer level) {
        controller.getHolderOrDefault(target.getPlayer()).setLevel(upgradeType, level);
        sender.sendMessage("Set " + target.getPlayer().getName() + " " + upgradeType.getId() + " to level " + level);
    }

    @Subcommand("get")
    @Syntax("<player> <upgrade-type>")
    @CommandCompletion("@players @types")
    public void get(CommandSender sender, @Flags("other") Player target, UpgradeType type) {
        int level = controller.getHolderOrDefault(target).getLevel(type);
        sender.sendMessage("Level of %s %s is %d".formatted(target.getName(), type.getId(), level));
    }
}
