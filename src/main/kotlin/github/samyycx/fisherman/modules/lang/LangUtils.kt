package github.samyycx.fisherman.modules.lang

import github.samyycx.fisherman.Main
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import taboolib.module.lang.Level
import taboolib.platform.util.asLangText
import taboolib.platform.util.asLangTextOrNull
import taboolib.platform.util.sendLang

object LangUtils {

    fun Player.sendPrefixedLang(lang: String, vararg args: Any) {
        this.sendMessage(Main.prefix+this.asLangText(lang, args))
    }

    fun String.parseForConsole(level: Level = Level.INFO, vararg args: String): String {
        return Bukkit.getConsoleSender().asLangText(level, this, args);
    }

}