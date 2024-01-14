package github.samyycx.fisherman.modules.config

import github.samyycx.fisherman.modules.gameconfig.fish.FishConfigManager
import github.samyycx.fisherman.modules.gameconfig.fishgroup.FishGroupManager
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


        FishConfigManager.setup(File(plugin.dataFolder, "fish"))
        FishGroupManager.setup(File(plugin.dataFolder, "fishgroup"))
    }

}