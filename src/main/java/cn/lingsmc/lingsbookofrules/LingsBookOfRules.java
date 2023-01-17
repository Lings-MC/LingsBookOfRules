package cn.lingsmc.lingsbookofrules;

import cn.lingsmc.lingsbookofrules.commands.Commands;
import cn.lingsmc.lingsbookofrules.commands.TabComplete;
import cn.lingsmc.lingsbookofrules.listeners.PlayerListener;
import cn.lingsmc.lingsbookofrules.utils.ConfigUtils;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Crsuh2er0
 * @since 2023/1/16
 */
public final class LingsBookOfRules extends JavaPlugin {
    @Getter
    private static LingsBookOfRules instance;

    @Override
    public void onLoad() {
        instance = this;
        ConfigUtils.initialize();
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        // init commands
        final PluginCommand command = this.getCommand(instance.getName());
        assert command != null;
        command.setExecutor(new Commands());
        command.setTabCompleter(new TabComplete());
        // init listeners
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        System.out.println("loaded");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        ConfigUtils.saveAgreedPlayers();
    }
}
