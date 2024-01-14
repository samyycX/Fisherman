package github.samyycx.fisherman.modules.gameconfig.fishgroup

import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

object FishGroupManager {

    private val fishGroups = mutableMapOf<String, FishGroup>()

    fun setup(folder: File) {
        fishGroups.clear()

        folder
            .listFiles { _, name -> name.endsWith(".yml") }
            ?.forEach {
                val config = YamlConfiguration.loadConfiguration(it)
                for (key in config.getKeys(false)) {
                    fishGroups[key] = FishGroupParser.parseFromSection(config.getConfigurationSection(key)!!)
                }
            }
    }

    fun getById(id: String): FishGroup? {
        return fishGroups[id]
    }

}