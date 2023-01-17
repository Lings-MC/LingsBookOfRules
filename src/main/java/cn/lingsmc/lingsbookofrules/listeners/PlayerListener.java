package cn.lingsmc.lingsbookofrules.listeners;

import cn.lingsmc.lingsbookofrules.constants.ConfigConstants;
import cn.lingsmc.lingsbookofrules.utils.ConfigUtils;
import fr.xephi.authme.events.LoginEvent;
import fr.xephi.authme.events.RegisterEvent;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedMainHandEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Crsuh2er0
 * @apiNote
 * @since 2023/1/16
 */
public class PlayerListener implements Listener {
    @Getter
    @Setter
    private static Map<Player, ItemStack> bookMap = new ConcurrentHashMap<>();
    @Setter
    private static FileConfiguration config;

    @EventHandler
    public void onRegister(@NotNull RegisterEvent event) {
        System.out.println("register");
        Player p = event.getPlayer();
        // open book for p
        ItemStack book = ConfigUtils.getBook().clone();
        bookMap.put(p, book);
        p.openBook(book);
    }

    @EventHandler
    public void onLogin(@NotNull LoginEvent event) {
        System.out.println("login");
        Player p = event.getPlayer();
        if (p.isOp() || ConfigUtils.getAgreedPlayers().contains(p.getUniqueId().toString())) {
            return;
        }
        // open book for p
        ItemStack book = ConfigUtils.getBook().clone();
        bookMap.put(p, book);
        p.openBook(book);
    }

    @EventHandler
    public void onPlayerCloseBook(@NotNull PlayerMoveEvent event) {
        System.out.println("move");
        Player p = event.getPlayer();
        if (!bookMap.containsKey(p)) {
            return;
        }
        // reopen book
        ItemStack book = ConfigUtils.getBook().clone();
        bookMap.put(p, book);
        p.openBook(book);
        p.sendMessage(Objects.requireNonNull(config.getString(ConfigConstants.DONE)));
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerCloseBook(@NotNull PlayerInteractEvent event) {
        System.out.println("interact");
        Player p = event.getPlayer();
        if (!bookMap.containsKey(p)) {
            return;
        }
        // reopen book
        ItemStack book = ConfigUtils.getBook().clone();
        bookMap.put(p, book);
        p.openBook(book);
        p.sendMessage(Objects.requireNonNull(config.getString(ConfigConstants.DONE)));
        event.setCancelled(true);
    }
}
