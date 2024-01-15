package github.samyycx.fisherman.modules.gameconfig.rod

import github.samyycx.fisherman.modules.gameconfig.bait.Bait
import org.bukkit.Material
import org.bukkit.configuration.ConfigurationSection

object RodParser {

    fun parseFromSection(section: ConfigurationSection): Rod {

        val id = section.name
        val name = section.getString("name")!!
        val lores = section.getStringList("lores")
        val condition = section.getStringList("condition")
        val fishgroup = section.getString("fishgroup")!!

        return Rod(id, name, lores, condition, fishgroup)

    }

}