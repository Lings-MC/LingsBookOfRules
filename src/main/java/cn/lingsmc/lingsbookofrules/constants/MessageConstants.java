package cn.lingsmc.lingsbookofrules.constants;

import cn.lingsmc.lingsbookofrules.LingsBookOfRules;
import lombok.Getter;

import static cn.lingsmc.lingsbookofrules.constants.CommandConstants.ALIAS;
import static cn.lingsmc.lingsbookofrules.constants.CommandConstants.RELOAD;

/**
 * @author Crsuh2er0
 * @apiNote
 * @since 2023/1/17
 */
public class MessageConstants {
    public static final String RELOAD_SUCCESS = "§a重载成功.";
    public static final String UNKNOWN_COMMAND = "§c未知命令.";
    public static final String NO_PERMISSION = "§c你没有执行该命令的权限.";
    public static final String CONSOLE = "§c该命令必须由玩家执行.";
    static LingsBookOfRules plugin = LingsBookOfRules.getInstance();
    @Getter
    protected static final String[] ROOT_MESSAGE = new String[]{
            String.format("§3此服务器正在运行 §b%s %s§3 by %s", plugin.getName(), plugin.getDescription().getVersion(), "§aC§br§cs§du§eh§a2§be§cr§d0"),
            String.format("§3重载: §b/%s %s", ALIAS, RELOAD),
    };

    private MessageConstants() {
    }
}
