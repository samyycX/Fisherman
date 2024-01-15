package github.samyycx.fisherman.modules.gameconfig.condition

import github.samyycx.fisherman.modules.exception.InvalidConditionException
import github.samyycx.fisherman.utils.KetherUtils
import org.bukkit.entity.Player

object ConditionExecutor {

    fun ifSatisfied(player: Player, identifier: String, conditions: List<String>, vararg variables: Pair<String, Any>): Boolean {

        return KetherUtils.eval(player, conditions, "player" to player, *variables).get().toString().toBooleanStrictOrNull()
            ?: throw InvalidConditionException(identifier, conditions.last())

    }
}