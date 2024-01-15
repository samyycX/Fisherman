package github.samyycx.fisherman.modules.gameconfig.bait

import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import taboolib.module.chat.colored
import taboolib.platform.util.modifyMeta

data class Bait(
    val id: String,
    val name: String,
    val material: Material,
    val lores: List<String>,
    val condition: List<String>,
    val fishgroup: String,
) {

    fun toItemStack(): ItemStack {

        val item = ItemStack(this.material)

        item.modifyMeta<ItemMeta> {
            this.lore = lores
            this.setDisplayName(name.colored())
        }

        return item

    }
}
