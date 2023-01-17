package cn.lingsmc.lingsbookofrules.utils;

import cn.lingsmc.lingsbookofrules.constants.MessageConstants;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

/**
 * @author Crsuh2er0
 * @apiNote
 * @since 2023/1/17
 */
public class PermissionUtils {
    private PermissionUtils() {
    }

    public static boolean nonAdminAuth(CommandSender sender, boolean notify) {
        if (sender instanceof ConsoleCommandSender || sender.isOp()) {
            return false;
        } else {
            if (notify) {
                sender.sendMessage(MessageConstants.NO_PERMISSION);
            }
            return true;
        }
    }
}
