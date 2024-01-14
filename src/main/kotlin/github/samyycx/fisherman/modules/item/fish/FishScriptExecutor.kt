package github.samyycx.fisherman.modules.item.fish

import github.samyycx.fisherman.modules.gameconfig.fish.FishConfigManager
import github.samyycx.fisherman.modules.gameconfig.fish.value.FishValueCalculator
import github.samyycx.fisherman.utils.KetherUtils
import org.bukkit.entity.Player

object FishScriptExecutor {

    fun runScript(player: Player, fish: Fish, script: List<String>?) {
        if (script.isNullOrEmpty()) return

        val args = mutableMapOf<String, Any>()
        args["player"] = player
        fish.attributes?.forEach { (attributeId, value) ->
            args[attributeId] = value
        }
        KetherUtils.eval(
            player,
            script,
            "player" to player,
            *args.toList().toTypedArray()
        )
    }

}