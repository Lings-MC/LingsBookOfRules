package cn.lingsmc.lingsbookofrules.commands.subcommands;

import cn.lingsmc.lingsbookofrules.constants.MessageConstants;
import cn.lingsmc.lingsbookofrules.utils.ConfigUtils;
import cn.lingsmc.lingsbookofrules.utils.PermissionUtils;
import org.bukkit.command.CommandSender;

/**
 * @author Crsuh2er0
 * @apiNote
 * @since 2023/1/16
 */
public class ReloadCommand {
    private ReloadCommand() {
    }

    public static void reload(CommandSender sender) {
        if (PermissionUtils.nonAdminAuth(sender, true)) {
            return;
        }

        // 重载配置文件
        ConfigUtils.initialize();

        sender.sendMessage(MessageConstants.RELOAD_SUCCESS);
    }
}
