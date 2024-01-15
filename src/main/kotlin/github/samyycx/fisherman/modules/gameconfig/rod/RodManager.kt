package github.samyycx.fisherman.modules.gameconfig.rod

import org.bukkit.configuration.file.YamlConfiguration
import taboolib.module.chat.colored
import java.io.File

object RodManager {

    private var defaultRod: Rod? = null

    private val rods = mutableMapOf<String, Rod>()

    fun setup(folder: File, defaultConfig: YamlConfiguration) {

        defaultRod = null
        rods.clear()

        folder
            .listFiles { _, name -> name.endsWith(".yml") }
            ?.forEach {
                val config = YamlConfiguration.loadConfiguration(it)
                config.getKeys(false).forEach { it2 ->
                    rods[it2] = RodParser.parseFromSection(config.getConfigurationSection(it2)!!)
                }
            }

        if (defaultConfig.contains("VanillaRodOverride")) {
            defaultRod = RodParser.parseFromSection(defaultConfig.getConfigurationSection("VanillaRodOverride")!!)
        }
    }

    fun getById(id: String): Rod? {
        return rods[id]
    }

    fun getByName(name: String?): Rod? {
        if (name == null) return null
        return rods.values.firstOrNull { rod -> rod.name.colored() == name }
    }

    fun vanillaOverrided(): Boolean {
        return defaultRod == null
    }

    // 请先调用vanillaOverrided判断
    fun getVanillaOverrideRod(): Rod {
        return defaultRod!!
    }

}