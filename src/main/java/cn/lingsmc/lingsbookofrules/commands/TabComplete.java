package cn.lingsmc.lingsbookofrules.commands;

import cn.lingsmc.lingsbookofrules.constants.CommandConstants;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

/**
 * @author Crsuh2er0
 * @apiNote
 * @since 2023/1/17
 */
public class TabComplete implements TabCompleter {
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (CommandConstants.RELOAD.startsWith(args[0])) {
            return Collections.singletonList(CommandConstants.RELOAD);
        }
        return Collections.emptyList();
    }

}
