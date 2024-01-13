package github.samyycx.fisherman

import github.samyycx.fisherman.modules.economy.EconomyProviderManager
import taboolib.common.platform.Plugin
import taboolib.module.chat.colored

object Main : Plugin() {

    val prefix = "&e[&bFisherman&e]".colored()

    override fun onLoad() {

        EconomyProviderManager.setup()

    }

}