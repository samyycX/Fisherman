package github.samyycx.fisherman.modules.command

import github.samyycx.fisherman.Main
import github.samyycx.fisherman.modules.config.ConfigManager
import github.samyycx.fisherman.modules.gameconfig.fish.FishConfigManager
import github.samyycx.fisherman.modules.gameconfig.fishgroup.FishGroupExecutor
import github.samyycx.fisherman.utils.KetherUtils
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import taboolib.common.platform.ProxyCommandSender
import taboolib.common.platform.ProxyPlayer
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.mainCommand
import taboolib.common.platform.command.subCommand
import taboolib.expansion.createHelper
import taboolib.module.configuration.Config
import taboolib.module.configuration.ConfigFile

@CommandHeader("test2")
object FishCommand {

    @Config(autoReload = true)
    private lateinit var config: ConfigFile

    @CommandBody
    val helper = mainCommand {
        createHelper()
    }

    @CommandBody
    val kether = subCommand { ->
        execute<Player> { sender, _, _ ->
            config.reload()
            val r = KetherUtils.eval(sender, config.getStringList("kether"), "player" to sender).get()
            println(r)
        }
    }

    @CommandBody
    val reload = subCommand {
        execute<CommandSender> { sender, _, _ ->
            ConfigManager.setup(Main.plugin)
        }
    }

    @CommandBody
    val group = subCommand {

        dynamic(optional = false) {
            execute<Player> { player, _, argument ->
                val result = FishGroupExecutor.executeFishGroup(player, argument)
                player.sendMessage(result.toString())
            }
        }
    }


}