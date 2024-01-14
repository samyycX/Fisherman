package github.samyycx.fisherman.modules.gameconfig.fish

import github.samyycx.fisherman.modules.gameconfig.fish.attribute.Attribute
import github.samyycx.fisherman.modules.gameconfig.fish.event.FishEvents
import github.samyycx.fisherman.modules.gameconfig.fish.basic.FishBaseConfig

data class FishConfig(

    var id: String = "",
    var base: FishBaseConfig? = null,
    var cooked: FishBaseConfig? = null,
    var attributes: MutableMap<String, Attribute>? = null,
    var value: MutableMap<String, List<String>>? =  null,
    var events: FishEvents? = null

)