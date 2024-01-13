package github.samyycx.fisherman.modules.gamedata.fish.attribute

import taboolib.library.configuration.ConfigurationSection
import taboolib.module.configuration.util.mapSection

object AttributeParser {

    fun parseSection(section: ConfigurationSection) : MutableMap<String, Attribute> {
        val map = mutableMapOf<String, Attribute>()
        section.toMap().forEach { (key, value) ->
            val attrSection = value as ConfigurationSection
            val attr = Attribute(key, attrSection.getStringList("value")!!, attrSection.getString("lore")!!)
            map[key] = attr
        }
        return map
    }

}