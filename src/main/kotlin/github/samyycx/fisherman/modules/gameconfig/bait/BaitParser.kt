package github.samyycx.fisherman.modules.gameconfig.bait

import org.bukkit.Material
import org.bukkit.configuration.ConfigurationSection

object BaitParser {

    fun parseFromSection(section: ConfigurationSection): Bait {

        val id = section.name
        val name = section.getString("name")!!
        val material = section.getString("material")?.let { Material.matchMaterial(it) } !!
        val lores = section.getStringList("lores")
        val condition = section.getStringList("condition")
        val fishgroup = section.getString("fishgroup")!!

        return Bait(id, name, material, lores, condition, fishgroup)

    }

}