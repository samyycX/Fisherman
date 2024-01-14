package github.samyycx.fisherman.modules.gameconfig.fish.attribute

import org.bukkit.configuration.ConfigurationSection


object AttributeParser {

    fun parseSection(section: ConfigurationSection) : Attribute {
        val attr = Attribute(section.name, section.getStringList("value"), section.getString("lore"))
        return attr
    }

}