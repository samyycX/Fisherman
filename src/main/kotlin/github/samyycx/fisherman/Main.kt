package github.samyycx.fisherman

import github.samyycx.fisherman.api.kether.FishActionExt
import github.samyycx.fisherman.modules.config.ConfigManager
import github.samyycx.fisherman.modules.economy.EconomyProviderManager
import taboolib.common.platform.Plugin
import taboolib.module.chat.colored
import taboolib.module.kether.KetherLoader
import taboolib.platform.BukkitPlugin


object Main : Plugin() {

    lateinit var prefix: String
    val plugin by lazy { BukkitPlugin.getInstance() }

    override fun onLoad() {

        FishActionExt.setup()
        ConfigManager.setup(plugin)
        EconomyProviderManager.setup()

    }

}