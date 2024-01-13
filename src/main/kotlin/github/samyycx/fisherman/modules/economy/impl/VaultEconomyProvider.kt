package github.samyycx.fisherman.modules.economy.impl

import github.samyycx.fisherman.modules.economy.EconomyProvider
import github.samyycx.fisherman.modules.exception.DependencyException
import github.samyycx.fisherman.modules.lang.LangUtils.parseForConsole
import net.milkbowl.vault.economy.Economy
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import taboolib.module.lang.Level
import taboolib.platform.BukkitPlugin
import taboolib.platform.util.asLangText

class VaultEconomyProvider : EconomyProvider {

    override val serviceName: String
        get() = "vault"

    private var instance: VaultEconomyProvider? = null

    private var econ: Economy? = null
    private var failedPlugin: String

    constructor() {
        val plugin = BukkitPlugin.getInstance().server.pluginManager.getPlugin("Vault")
        if (plugin == null) {
            failedPlugin = "Noun.Vault"
            return
        }
        val rsp = BukkitPlugin.getInstance().server.servicesManager.getRegistration(Economy::class.java)
        if (rsp == null) {
            failedPlugin = "Noun.Vault-Economy-Provider"
            return
        }
        failedPlugin = ""
        econ = rsp.provider
    }

    private fun checkAvailable() {
        if (econ == null) {
            val message = "Console.Error.Economy-Service-NotFound".parseForConsole(Level.ERROR, failedPlugin.parseForConsole())
            throw DependencyException(message)
        }
    }

    override fun playerHasEconomy(player: Player, economy: Double): Boolean {
        checkAvailable()
        return econ!!.has(player, economy)
    }

    override fun takePlayerEconomy(player: Player, economy: Double) {
        checkAvailable()
        econ!!.withdrawPlayer(player, economy)
    }

    override fun givePlayerEconomy(player: Player, economy: Double) {
        checkAvailable()
        econ!!.depositPlayer(player, economy)
    }

}