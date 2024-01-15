package github.samyycx.fisherman.modules.config

import github.samyycx.fisherman.Main
import github.samyycx.fisherman.modules.gameconfig.bait.BaitManager
import github.samyycx.fisherman.modules.gameconfig.fish.FishConfigManager
import github.samyycx.fisherman.modules.gameconfig.fishgroup.FishGroupManager
import github.samyycx.fisherman.modules.gameconfig.rod.RodManager
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

object ConfigManager {

    lateinit var default: YamlConfiguration

    fun setup(plugin: JavaPlugin) {

        plugin.reloadConfig()

        if (!plugin.dataFolder.exists()) {
            plugin.dataFolder.mkdir()
        }

        plugin.saveDefaultConfig()
        plugin.saveResource("default.yml", false)

        Main.prefix = plugin.config.getString("Prefix")!!

        val resources = listOf(
            "fish${File.separator}example_fish.yml",
            "lang${File.separator}zh_CN.yml",
            "bait${File.separator}example_bait.yml",
            "rod${File.separator}example_rod.yml"
        )

        resources.forEach { plugin.saveResource(it, false) }

        default = YamlConfiguration.loadConfiguration(File(plugin.dataFolder, "default.yml"))

        FishConfigManager.setup(File(plugin.dataFolder, "fish"))
        FishGroupManager.setup(File(plugin.dataFolder, "fishgroup"))
        BaitManager.setup(File(plugin.dataFolder, "bait"))
        RodManager.setup(File(plugin.dataFolder, "rod"), default)
    }

}