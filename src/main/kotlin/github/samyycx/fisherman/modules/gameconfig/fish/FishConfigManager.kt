package github.samyycx.fisherman.modules.gameconfig.fish

import org.bukkit.Bukkit
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.configuration.file.YamlConfiguration
import taboolib.module.lang.Level
import taboolib.platform.BukkitPlugin
import taboolib.platform.util.sendLang
import java.io.File

object FishConfigManager {

    private val configs = mutableMapOf<String, FishConfig>()

    fun setup() {

        configs.clear()

        val dataFolder = File(BukkitPlugin.getInstance().dataFolder, "fish")

        val configNeedTemplates = mutableMapOf<String, MutableList<ConfigurationSection>>()

        dataFolder
            .listFiles { _, name -> name.endsWith(".yml") }
            ?.forEach {
                val config = YamlConfiguration.loadConfiguration(it)

                for (id in config.getKeys(false)) {
                    val section = config.getConfigurationSection(id)!!
                    val (missingTemplate, loadedConfig) = FishConfigParser.parseFromSection(section, configs)

                    // 加载出现错误
                    if (missingTemplate == null && loadedConfig == null) {
                        continue
                    }

                    if (missingTemplate != null) {

                        var list = configNeedTemplates[missingTemplate]
                        if (list == null) {
                            list = mutableListOf()
                        }
                        list.add(section)
                        configNeedTemplates[missingTemplate] = list!!

                    } else {
                        configs[id] = loadedConfig!!
                    }
                }
            }

        var lastModified = false
        while (configNeedTemplates.isNotEmpty()) {
            for (key in configNeedTemplates.keys) {
                if (key in configs) {
                    configNeedTemplates[key]!!.forEach {
                        val (_, loadedConfig) = FishConfigParser.parseFromSection(it, configs)
                        configs[loadedConfig!!.id] = loadedConfig
                    }
                    configNeedTemplates.remove(key)
                    lastModified = true
                }
            }

            if (!lastModified) {
                Bukkit.getConsoleSender().sendLang(Level.ERROR, "Console.Error.Fish-Template-Unknown", configNeedTemplates.keys.joinToString(","))
                break
            }
        }

    }

    fun test() {
        println(configs)
    }

}