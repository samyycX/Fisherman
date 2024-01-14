package github.samyycx.fisherman

import github.samyycx.fisherman.api.ActionBetaDistribution
import github.samyycx.fisherman.modules.config.ConfigManager
import github.samyycx.fisherman.modules.economy.EconomyProviderManager
import taboolib.common.env.RuntimeDependency
import taboolib.common.platform.Plugin
import taboolib.module.chat.colored
import taboolib.module.configuration.Local
import taboolib.module.kether.KetherLoader
import taboolib.module.kether.KetherScriptLoader
import taboolib.platform.BukkitPlugin


object Main : Plugin() {

    val prefix = "&e[&bFisherman&e]".colored()
    val plugin by lazy { BukkitPlugin.getInstance() }

    override fun onLoad() {

        KetherLoader.registerParser(ActionBetaDistribution.betaDistribution(), arrayOf("betadistribution"))

        ConfigManager.setup(plugin)
        EconomyProviderManager.setup()

    }

}