package github.samyycx.fisherman.modules.listener

import github.samyycx.fisherman.api.event.FPlayerFishEvent
import github.samyycx.fisherman.modules.gameconfig.bait.BaitManager
import github.samyycx.fisherman.modules.gameconfig.rod.RodManager
import org.bukkit.event.player.PlayerFishEvent
import taboolib.common.platform.event.SubscribeEvent


object BukkitListener {

    @SubscribeEvent
    fun onFishEvent(e: PlayerFishEvent) {
        val player = e.player
        if (player.inventory.itemInMainHand.itemMeta == null && RodManager.vanillaOverrided()) {
            val bait = BaitManager.getByName(player.inventory.itemInOffHand.itemMeta?.displayName)
            FPlayerFishEvent(e, player, RodManager.getVanillaOverrideRod(), bait).call()
            return
        }
        val rod = RodManager.getByName(player.inventory.itemInMainHand.itemMeta?.displayName)
        if (rod != null) {
            val bait = BaitManager.getByName(player.inventory.itemInOffHand.itemMeta?.displayName)
            FPlayerFishEvent(e, player, rod, bait).call()
        }
    }





}