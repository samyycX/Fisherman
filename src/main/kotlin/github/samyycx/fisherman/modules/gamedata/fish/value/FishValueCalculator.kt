package github.samyycx.fisherman.modules.gamedata.fish.value

import github.samyycx.fisherman.utils.KetherUtils
import org.bukkit.entity.Player

object FishValueCalculator {

    fun calculateValue(player: Player, map: Map<String, List<String>>) : Map<String, Double> {

        map.forEach { ( service, script ) ->
            KetherUtils.eval(player, script)
        }

        return mutableMapOf()

    }

}