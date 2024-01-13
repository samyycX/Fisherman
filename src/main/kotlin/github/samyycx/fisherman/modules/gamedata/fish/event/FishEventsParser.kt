package github.samyycx.fisherman.modules.gamedata.fish.event

import taboolib.library.configuration.ConfigurationSection

object FishEventsParser {

    fun parseSection(section: ConfigurationSection) : FishEvents {

        val onFished = section.getStringList("onFished")
        val onSell = section.getStringList("onSell")
        val onEated = section.getStringList("onEated")
        val onEatCooked = section.getStringList("onEatCooked")

        val events = FishEvents(onFished, onSell, onEated, onEatCooked)

        return events
    }

}