package github.samyycx.fisherman.modules.lang

import org.bukkit.Bukkit
import taboolib.module.lang.Level
import taboolib.platform.util.asLangText
import taboolib.platform.util.asLangTextOrNull

object LangUtils {

    fun String.parseForConsole(level: Level = Level.INFO, vararg args: String): String {
        return Bukkit.getConsoleSender().asLangText(level, this, args);
    }

}