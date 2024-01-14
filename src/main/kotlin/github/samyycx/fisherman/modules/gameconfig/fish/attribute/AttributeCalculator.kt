package github.samyycx.fisherman.modules.gameconfig.fish.attribute

import github.samyycx.fisherman.utils.KetherUtils
import org.bukkit.entity.Player

object AttributeCalculator {

    fun calculateAttributes(player: Player, attributes: Map<String, Attribute>?) : MutableMap<String, Double> {

        val result = mutableMapOf<String, Double>()

        attributes?.forEach { (id, attribute) ->
            val value = KetherUtils.eval(player, attribute.valueFormula, *result.toList().toTypedArray()).get()
            result[id] = value.toString().toDouble()
        }

        return result
    }

}