package github.samyycx.fisherman.api.event

import github.samyycx.fisherman.modules.gameconfig.bait.Bait
import github.samyycx.fisherman.modules.gameconfig.rod.Rod
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerFishEvent
import taboolib.platform.type.BukkitProxyEvent

class FPlayerFishEvent(
    val originalEvent: PlayerFishEvent,
    val player: Player,
    val rod: Rod,
    val bait: Bait?,
): BukkitProxyEvent() {



}