package github.samyycx.fisherman.modules.gameconfig.fish.value

import github.samyycx.fisherman.modules.gameconfig.fish.FishConfig
import github.samyycx.fisherman.utils.KetherUtils
import github.samyycx.fisherman.utils.KetherUtils.ketherVars
import org.bukkit.entity.Player

object FishValueCalculator {

    fun calculateValue(player: Player, fish: FishConfig, map: Map<String, List<String>>) : Map<String, Double> {

        val result = mutableMapOf<String, Double>()

        map.forEach { ( service, script ) ->
            val value = KetherUtils.eval(player, script, player.ketherVars(), "fish" to fish).get() as Double
            result[service] = value
        }

        return result

    }

}