package github.samyycx.fisherman.modules.economy

import github.samyycx.fisherman.Main
import github.samyycx.fisherman.modules.economy.impl.PlayerPointsEconomyProvider
import github.samyycx.fisherman.modules.economy.impl.VaultEconomyProvider
import github.samyycx.fisherman.modules.lang.LangUtils.sendPrefixedLang
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import taboolib.platform.util.sendLang

object EconomyProviderManager {

    private lateinit var providers: MutableMap<String, EconomyProvider>

    // 注册默认的vault和playerpoints
    private fun registerProviders() {

        val vault = VaultEconomyProvider()
        val playerpoints = PlayerPointsEconomyProvider()

        providers = mutableMapOf(
            vault.serviceName to vault,
            playerpoints.serviceName to playerpoints
        )

    }

    fun setup() {
        registerProviders()
    }

    // 给API预留的一个方法
    @JvmStatic
    fun addEconomyProvider(provider: EconomyProvider) {
        providers[provider.serviceName] = provider
    }

    fun takePlayerEconomy(player: Player, map: Map<String, Double>): Boolean {
        // 标记玩家是否满足所有条件，一旦不满足就回滚
        var pass = true
        map.forEach { (service, amount) ->
            if (service !in providers) {
                Bukkit.getConsoleSender().sendLang("Console.Unknown-Economy-Service", service)
                player.sendPrefixedLang("Player.Error.Internal", Main.prefix)
                pass = false
                return@forEach
            }

            if (!providers[service]!!.playerHasEconomy(player, amount)) {
                player.sendPrefixedLang("Player.Error.Insufficient-Economy.$service")
                pass = false
            }
        }

        if (!pass) return false

        // 如果通过就开始take
        map.forEach { (service, amount) ->
            providers[service]!!.takePlayerEconomy(player, amount)
        }
        return true
    }

    fun givePlayerEconomy(player: Player, map: Map<String, Double>) {
        map.forEach { (service, amount) ->
            if (service !in providers) {
                Bukkit.getConsoleSender().sendLang("Console.Unknown-Economy-Service", service)
            } else {
                providers[service]!!.givePlayerEconomy(player, amount)
                player.sendPrefixedLang("Player.Success.Economy-Acquire", Main.prefix, amount)
            }
        }
    }
}