package cn.lingsmc.lingsbookofrules.commands.subcommands;

import cn.lingsmc.lingsbookofrules.constants.ConfigConstants;
import cn.lingsmc.lingsbookofrules.constants.MessageConstants;
import cn.lingsmc.lingsbookofrules.listeners.PlayerListener;
import cn.lingsmc.lingsbookofrules.utils.ConfigUtils;
import lombok.Setter;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.Objects;

/**
 * @author Crsuh2er0
 * @apiNote
 * @since 2023/1/17
 */
public class AgreeCommand {
    @Setter
    private static FileConfiguration config;

    private AgreeCommand() {
    }

    public static void agree(CommandSender sender) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(MessageConstants.CONSOLE);
            return;
        }
        Player p = (Player) sender;
        if (!PlayerListener.getBookMap().containsKey(p)) {
            sender.sendMessage(MessageConstants.UNKNOWN_COMMAND);
            return;
        }
        // add agreed for the p
        ConfigUtils.addAgreedPlayer(p.getUniqueId().toString());

        // play sound
        Sound sound = Sound.valueOf(Objects.requireNonNull(config.getString(ConfigConstants.AGREE_SOUND)));
        p.playSound(p.getLocation(), sound, 1, 1);

        Map<Player, ItemStack> bookMap = PlayerListener.getBookMap();
        bookMap.remove(p);
        PlayerListener.setBookMap(bookMap);
        p.closeInventory();
        if ("" .equals(Objects.requireNonNull(config.getString(ConfigConstants.AGREE)))) {
            return;
        }
        p.sendMessage(Objects.requireNonNull(config.getString(ConfigConstants.AGREE)));
    }
}
