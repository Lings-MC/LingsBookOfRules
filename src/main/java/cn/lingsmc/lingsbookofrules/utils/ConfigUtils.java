package cn.lingsmc.lingsbookofrules.utils;

import cn.lingsmc.lingsbookofrules.LingsBookOfRules;
import cn.lingsmc.lingsbookofrules.commands.subcommands.AgreeCommand;
import cn.lingsmc.lingsbookofrules.commands.subcommands.DisagreeCommand;
import cn.lingsmc.lingsbookofrules.constants.CommandConstants;
import cn.lingsmc.lingsbookofrules.constants.ConfigConstants;
import cn.lingsmc.lingsbookofrules.listeners.PlayerListener;
import lombok.Getter;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Crsuh2er0
 * @apiNote
 * @since 2023/1/16
 */
public class ConfigUtils {
    static LingsBookOfRules plugin = LingsBookOfRules.getInstance();
    static String title;
    static String author;
    static List<String> pages;
    @Getter
    private static ItemStack book;
    @Getter
    private static List<String> agreedPlayers = new ArrayList<>();

    private ConfigUtils() {
    }

    public static void initialize() {
        // 初始化配置文件
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
        plugin.getConfig().options().copyDefaults(true);
        FileConfiguration config = plugin.getConfig();
        AgreeCommand.setConfig(config);
        DisagreeCommand.setConfig(config);
        PlayerListener.setConfig(config);

        // init agreed players
        agreedPlayers = config.getStringList(ConfigConstants.AGREED_PLAYERS);

        // init book
        title = config.getString(ConfigConstants.TITLE);
        author = config.getString(ConfigConstants.AUTHOR);
        pages = config.getStringList(ConfigConstants.PAGES);
        book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookMeta = (BookMeta) book.getItemMeta();
        assert bookMeta != null;
        bookMeta.setTitle(title);
        bookMeta.setAuthor(author);

        // to create a clickable text
        BaseComponent[] agree = new ComponentBuilder("[同意]")
                .color(ChatColor.GREEN)
                .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, String.format("/%s %s", CommandConstants.ALIAS, CommandConstants.AGREE)))
                .create();
        BaseComponent[] disagree = new ComponentBuilder("[拒绝]")
                .color(ChatColor.RED)
                .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, String.format("/%s %s", CommandConstants.ALIAS, CommandConstants.DISAGREE)))
                .create();

        // add the text to the book
        BaseComponent[] newPage = new ComponentBuilder(pages.get(pages.size() - 1) + "\n").append(agree).append(disagree).create();
        pages.remove(pages.size() - 1);
        bookMeta.setPages(pages);
        bookMeta.spigot().addPage(newPage);
        book.setItemMeta(bookMeta);
    }

    public static void addAgreedPlayer(String uuid) {
        agreedPlayers.add(uuid);
    }

    public static void saveAgreedPlayers() {
        plugin.getConfig().set(ConfigConstants.AGREED_PLAYERS, agreedPlayers);
        plugin.saveConfig();
    }
}
