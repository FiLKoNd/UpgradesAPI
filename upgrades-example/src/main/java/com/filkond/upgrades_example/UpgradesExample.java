package com.filkond.upgrades_example;

import co.aikar.commands.*;
import com.filkond.upgrades.configuration.UpgradeType;
import com.filkond.upgrades_example.listener.PlayerListener;
import com.filkond.upgrades_example.command.UpgradeExecutor;
import com.filkond.upgrades.db.MariaDBCredentials;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.stream.Collectors;

@Getter
public class UpgradesExample extends JavaPlugin {
    private static UpgradesExample INSTANCE;
    private PlayerUpgradesController controller;

    @Override
    public void onEnable() {
        INSTANCE = this;
        MariaDBCredentials credentials = new MariaDBCredentials("upgrades", "localhost", "filkond", "1230");
        controller = new PlayerUpgradesController(PlayerHolder.class, credentials);
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);

        PaperCommandManager manager = new PaperCommandManager(this);
        CommandContexts<BukkitCommandExecutionContext> contexts = manager.getCommandContexts();
        contexts.registerContext(UpgradeType.class, ctx -> {
                    String id = ctx.popFirstArg();
                    return controller.getUpgradeType(id).orElseThrow(() -> new InvalidCommandArgument("Unknown upgrade type: " + id));
                }
        );

        CommandCompletions<BukkitCommandCompletionContext> completions = manager.getCommandCompletions();
        completions.registerCompletion("types", ctx -> controller.getUpgradeTypes().stream()
                .map(UpgradeType::getId)
                .collect(Collectors.toList()));

        completions.registerCompletion("level", ctx -> ctx.getContextValue(UpgradeType.class).getLevels().stream()
                .map(lvl -> String.valueOf(lvl.getIntValue()))
                .toList());
        manager.registerCommand(new UpgradeExecutor(controller));
    }

    @Override
    public void onDisable() {
        controller.save();
    }

    public static UpgradesExample getInstance() {
        return INSTANCE;
    }
}