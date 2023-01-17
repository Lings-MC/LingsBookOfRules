package cn.lingsmc.lingsbookofrules.commands;

import cn.lingsmc.lingsbookofrules.commands.subcommands.AgreeCommand;
import cn.lingsmc.lingsbookofrules.commands.subcommands.DisagreeCommand;
import cn.lingsmc.lingsbookofrules.commands.subcommands.ReloadCommand;
import cn.lingsmc.lingsbookofrules.constants.CommandConstants;
import cn.lingsmc.lingsbookofrules.constants.MessageConstants;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

/**
 * @author Crsuh2er0
 * @apiNote
 * @since 2023/1/17
 */
public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull org.bukkit.command.Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            for (String s : MessageConstants.getROOT_MESSAGE()) {
                sender.sendMessage(s);
            }
        } else if (args.length == 1) {
            switch (args[0]) {
                case CommandConstants.RELOAD:
                    ReloadCommand.reload(sender);
                    break;
                case CommandConstants.AGREE:
                    AgreeCommand.agree(sender);
                    break;
                case CommandConstants.DISAGREE:
                    DisagreeCommand.disagree(sender);
                    break;
                default:
                    sender.sendMessage(MessageConstants.UNKNOWN_COMMAND);
            }
        } else {
            sender.sendMessage(MessageConstants.UNKNOWN_COMMAND);
        }
        return true;
    }
}
