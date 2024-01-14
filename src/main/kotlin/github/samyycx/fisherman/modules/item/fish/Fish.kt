package github.samyycx.fisherman.modules.item.fish

import github.samyycx.fisherman.modules.gameconfig.fish.FishConfigManager
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import taboolib.common5.util.replace
import taboolib.module.chat.colored
import taboolib.platform.util.modifyLore

data class Fish(
    val attributes: MutableMap<String, Double>?,
    val isCooked: Boolean,
    val fishId: String
) {
    fun toItemStack(): ItemStack {
        val fishConfig = FishConfigManager.getById(fishId)!!
        val basicData = if (isCooked) { fishConfig.cooked!! } else { fishConfig.base } !!

        var itemStack = ItemStack(basicData.material!!)
        itemStack = itemStack.modifyLore {
            this.addAll(basicData.description)

            fishConfig.attributes?.forEach { (id, attribute) ->
                this.add(attribute.lore!!.replace("{value}" to attributes!![id]!!))
            }
        }
        val meta = itemStack.itemMeta
        meta!!.setDisplayName(basicData.name?.colored())
        itemStack.setItemMeta(meta)

        return itemStack

    }
}
