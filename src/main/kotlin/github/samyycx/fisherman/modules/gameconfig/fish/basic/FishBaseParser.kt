package github.samyycx.fisherman.modules.gameconfig.fish.basic

import org.bukkit.Material
import org.bukkit.configuration.ConfigurationSection

object FishBaseParser {

    fun parseSection(section: ConfigurationSection) : FishBaseConfig {

        val name = section.getString("name")
        val material = section.getString("material")?.let { Material.matchMaterial(it) }
        val description = section.getStringList("description")

        return FishBaseConfig(name, material, description)

    }

}