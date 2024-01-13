package github.samyycx.fisherman.modules.economy.impl

import github.samyycx.fisherman.modules.economy.EconomyProvider
import github.samyycx.fisherman.modules.exception.DependencyException
import github.samyycx.fisherman.modules.lang.LangUtils.parseForConsole
import org.black_ixx.playerpoints.PlayerPoints
import org.bukkit.entity.Player
import taboolib.module.lang.Level
import taboolib.platform.BukkitPlugin

class PlayerPointsEconomyProvider : EconomyProvider {

    override val serviceName: String
        get() = "playerpoints"

    private var instance: PlayerPointsEconomyProvider? = null

    private lateinit var failedPlugin: String
    private var playerPoints: PlayerPoints? = null

    constructor() {
        val plugin = BukkitPlugin.getInstance().server.pluginManager.getPlugin("PlayerPoints")
        if (plugin == null) {
            failedPlugin = serviceName
            return
        }
        playerPoints = plugin as PlayerPoints
    }

    private fun checkAvailable() {
        if (playerPoints == null) {
            val message = "Console.Error.Economy-Service-NotFound".parseForConsole(Level.ERROR, failedPlugin.parseForConsole())
            throw DependencyException(message)
        }
    }

    override fun playerHasEconomy(player: Player, economy: Double): Boolean {
        checkAvailable()
        return playerPoints!!.api.look(player.uniqueId) >= economy.toInt()
    }

    override fun givePlayerEconomy(player: Player, economy: Double) {
        checkAvailable()
        playerPoints!!.api.give(player.uniqueId, economy.toInt())
    }

    override fun takePlayerEconomy(player: Player, economy: Double) {
        checkAvailable()
        playerPoints!!.api.take(player.uniqueId, economy.toInt())
    }
}