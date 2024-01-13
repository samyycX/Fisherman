package github.samyycx.fisherman.modules.gamedata.fish.attribute

import github.samyycx.fisherman.utils.KetherUtils
import org.bukkit.entity.Player

object AttributeCalculator {

    fun calculateAttributes(player: Player, attributes: Map<String, Attribute>) : Map<String, Double> {
        val result = mutableMapOf<String, Double>()

        attributes.forEach { (id, attribute) ->
            val value = KetherUtils.eval(player, attribute.valueFormula, *result.toList().toTypedArray()).get()
            result[id] = value as Double
        }

        return result
    }

}