package github.samyycx.fisherman.modules.gameconfig.fishgroup

import org.bukkit.configuration.ConfigurationSection

object FishGroupParser {

    fun parseFromSection(section: ConfigurationSection): FishGroup {

        val id = section.name
        val condition = section.getStringList("condition")
        val items = section.getStringList("items")

        return FishGroup(id, condition, items)

    }

}