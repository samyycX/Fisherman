package github.samyycx.fisherman.modules.logic

import github.samyycx.fisherman.modules.gameconfig.bait.Bait
import github.samyycx.fisherman.modules.gameconfig.condition.ConditionExecutor
import github.samyycx.fisherman.modules.gameconfig.fishgroup.FishGroupExecutor
import github.samyycx.fisherman.modules.gameconfig.fishgroup.FishGroupManager
import github.samyycx.fisherman.modules.gameconfig.rod.Rod
import github.samyycx.fisherman.modules.item.fish.Fish
import github.samyycx.fisherman.modules.lang.LangUtils.sendPrefixedLang
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import taboolib.module.chat.colored
import taboolib.module.lang.Level
import taboolib.platform.util.sendLang

object GameLogic {

    fun randomFish(player: Player, rod: Rod, bait: Bait?): Fish? {

        val rodAvailable = ConditionExecutor.ifSatisfied(player, "rod -> ${rod.id}", rod.condition)

        if (!rodAvailable) {
            player.sendPrefixedLang("Player.Error.Condition-Unsatisfied", rod.name.colored())
            return null
        }

        val baitAvailable = bait?.let {
            ConditionExecutor.ifSatisfied(player, "bait -> ${bait.id}", bait.condition) } == true

        val group = if (baitAvailable) { bait!!.fishgroup } else { rod.fishgroup }

        val fishConfig = FishGroupExecutor.executeFishGroup(player, group)

    }

}