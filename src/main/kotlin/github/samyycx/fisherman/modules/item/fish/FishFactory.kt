package github.samyycx.fisherman.modules.item.fish

import github.samyycx.fisherman.modules.gameconfig.fish.FishConfig
import github.samyycx.fisherman.modules.gameconfig.fish.FishConfigManager
import github.samyycx.fisherman.modules.gameconfig.fish.attribute.AttributeCalculator
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import taboolib.common5.util.replace
import taboolib.library.reflex.Reflex.Companion.unsafeInstance
import taboolib.module.chat.colored

object FishFactory {

    fun createFish(player: Player, fishConfig: FishConfig): Fish {

        val attributes = AttributeCalculator.calculateAttributes(player, fishConfig.attributes)

        return Fish(attributes, false, fishConfig.id)
    }

    fun resolveFishByItemStack(itemStack: ItemStack): Fish? {
        val (config, isCooked) = FishConfigManager.getByName(itemStack.itemMeta?.displayName) ?: return null

        val storedAttributes = mutableMapOf<String, Double>()
        itemStack.itemMeta?.lore?.forEach {
            config.attributes?.forEach { (id, attribute) ->
                val lore = attribute.lore!!.colored()
                val split = lore.split("{value}")
                val resultStr = it.replace(split[0] to "", split[1] to "")
                val result = resultStr.toDoubleOrNull()

                if (result != null) {
                    storedAttributes[id] = result
                }
            }
        }

        return Fish(storedAttributes, isCooked, config.id)

    }

}