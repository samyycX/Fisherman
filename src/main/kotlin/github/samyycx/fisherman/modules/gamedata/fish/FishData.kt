package github.samyycx.fisherman.modules.gamedata.fish

import github.samyycx.fisherman.modules.gamedata.fish.attribute.Attribute
import github.samyycx.fisherman.modules.gamedata.fish.event.FishEvents
import github.samyycx.fisherman.modules.gamedata.fish.basic.FishBaseData

data class FishData(
    val id: String,
    val base: FishBaseData,
    val cooked: FishBaseData,
    val attributes: MutableMap<String, Attribute>,
    val value: MutableMap<String, List<String>>,
    val events: FishEvents

)