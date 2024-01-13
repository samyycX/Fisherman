package github.samyycx.fisherman.modules.gamedata.fish.basic

import org.bukkit.Material
import taboolib.library.configuration.ConfigurationSection

object FishBaseParser {

    fun parseSection(section: ConfigurationSection) : FishBaseData {

        val name = section.getString("name")
        val material = section.getString("material")?.let { Material.matchMaterial(it) }
        val description = section.getStringList("description")

        return FishBaseData(name, material, description)

    }

}