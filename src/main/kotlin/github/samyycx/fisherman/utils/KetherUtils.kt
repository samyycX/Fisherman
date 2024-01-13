package github.samyycx.fisherman.utils

import org.bukkit.entity.Player
import taboolib.common.platform.function.adaptPlayer
import taboolib.library.kether.LocalizedException
import taboolib.module.kether.KetherShell
import java.util.concurrent.CompletableFuture

object KetherUtils {

    fun eval(player: Player, script: List<String>, vararg variables: Pair<String, Any>) : CompletableFuture<Any?> {
        return try {
            KetherShell.eval(script, namespace = listOf("fisherman")) {
                sender = adaptPlayer(player)
                variables.forEach {
                    set(it.first, it.second)
                }
            }
        } catch (e: LocalizedException) {
            e.localizedMessage.split("\n").forEach {
                println(it)
            }
            CompletableFuture.completedFuture(false)
        }
    }
}