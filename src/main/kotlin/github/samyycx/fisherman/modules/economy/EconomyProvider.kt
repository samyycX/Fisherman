package github.samyycx.fisherman.modules.economy

import org.bukkit.entity.Player

/**
 * 经济插件的通用接口
 * serviceName 应该和配置文件里的key相同 (全小写插件名)
 */
interface EconomyProvider {

    val serviceName: String

    fun playerHasEconomy(player: Player, economy: Double): Boolean

    fun takePlayerEconomy(player: Player, economy: Double)

    fun givePlayerEconomy(player: Player, economy: Double)
}