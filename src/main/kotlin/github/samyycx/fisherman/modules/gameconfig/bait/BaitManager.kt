package github.samyycx.fisherman.modules.gameconfig.bait

import org.bukkit.configuration.file.YamlConfiguration
import taboolib.module.chat.colored
import java.io.File

object BaitManager {

    private val baits = mutableMapOf<String, Bait>()

    fun setup(folder: File) {

        baits.clear()

        folder
            .listFiles { _, name -> name.endsWith(".yml") }
            ?.forEach {
                val config = YamlConfiguration.loadConfiguration(it)
                config.getKeys(false).forEach { it2 ->
                    baits[it2] = BaitParser.parseFromSection(config.getConfigurationSection(it2)!!)
                }
            }
    }

    fun getById(id: String): Bait? {
        return baits[id]
    }

    fun getByName(name: String?): Bait? {
        if (name == null) return null
        return baits.values.firstOrNull { bait -> bait.name.colored() == name }
    }


}