package cn.lingsmc.lingsbookofrules.commands.subcommands;

import cn.lingsmc.lingsbookofrules.constants.ConfigConstants;
import cn.lingsmc.lingsbookofrules.constants.MessageConstants;
import cn.lingsmc.lingsbookofrules.listeners.PlayerListener;
import lombok.Setter;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

/**
 * @author Crsuh2er0
 * @apiNote
 * @since 2023/1/17
 */
public class DisagreeCommand {
    @Setter
    private static FileConfiguration config;

    private DisagreeCommand() {
    }

    public static void disagree(CommandSender sender) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(MessageConstants.CONSOLE);
            return;
        }
        Player p = (Player) sender;
        if (!PlayerListener.getBookMap().containsKey(p)) {
            sender.sendMessage(MessageConstants.UNKNOWN_COMMAND);
            return;
        }
        p.kickPlayer(!"" .equals(config.getString(ConfigConstants.DISAGREE)) ? config.getString(ConfigConstants.DISAGREE) : null);
    }
}
