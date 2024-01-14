package github.samyycx.fisherman.modules.gameconfig.fish

import org.bukkit.Bukkit
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.configuration.file.YamlConfiguration
import taboolib.module.chat.colored
import taboolib.module.lang.Level
import taboolib.platform.util.sendLang
import java.io.File

object FishConfigManager {

    private val configs = mutableMapOf<String, FishConfig>()

    fun setup(folder: File) {

        configs.clear()

        val configNeedTemplates = mutableMapOf<String, MutableList<ConfigurationSection>>()

        folder
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

    fun contains(id: String): Boolean {
        return configs.containsKey(id)
    }

    fun getById(id: String): FishConfig? {
        return configs[id]
    }

    /**
     * Pair的第一个是配置
     * Pair的第二个是是否是烤过的鱼
     */
    fun getByName(name: String?): Pair<FishConfig, Boolean>? {

        if (name == null) return null

        configs.values.forEach {
            if (it.base!!.name!!.colored() == name) {
                return it to false
            } else if (it.cooked?.name!!.colored() == name) {
                return it to true
            }
        }

        return null
    }

}