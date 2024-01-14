package github.samyycx.fisherman.modules.config

import org.bukkit.plugin.java.JavaPlugin
import java.io.File

object ConfigManager {

    fun setup(plugin: JavaPlugin) {

        if (!plugin.dataFolder.exists()) {
            plugin.dataFolder.mkdir()
        }

        plugin.saveDefaultConfig()

        val resources = listOf(
            "fish${File.separator}example_fish.yml",
            "lang${File.separator}zh_CN.yml"
        )

        resources.forEach { plugin.saveResource(it, false) }

    }

}