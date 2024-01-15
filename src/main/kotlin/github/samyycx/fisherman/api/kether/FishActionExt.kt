package github.samyycx.fisherman.api.kether

import github.samyycx.fisherman.modules.gameconfig.bait.BaitManager
import org.apache.commons.math3.distribution.BetaDistribution
import org.bukkit.Bukkit
import taboolib.common.env.RuntimeDependency
import taboolib.module.kether.*

@RuntimeDependency("org.apache.commons:commons-math3:3.6.1")
object FishActionExt {

    fun setup() {
        KetherLoader.registerParser(betaDistribution(), arrayOf("betadistribution"))
        KetherLoader.registerParser(hasBait(), arrayOf("bait", "hasbait"))
    }

    @KetherParser(["betadistribution"])
    fun betaDistribution() = combinationParser {
        it.group(
            double(),
            command("to", then = double()),
            command("alpha", then = double()),
            command("beta", then = double())
        ).apply(it) { from, to, alpha, beta ->
            now {
                val betaDistribution = BetaDistribution(alpha!!, beta!!)
                val result = from + (to - from) * betaDistribution.sample()
                result
            }
        }
    }

    @KetherParser(["bait"])
    fun hasBait() = combinationParser { it2 ->
        it2.group(
            anyAsList()
        ).apply(it2) { list ->
            now {
                // unsafe
                val player = Bukkit.getPlayerExact(this.player().name)!!
                val inventory = player.inventory

                list.forEach {
                    val target = it.toString()
                    val bait = BaitManager.getById(target) ?: return@now false

                    if (!inventory.itemInOffHand.isSimilar(bait.toItemStack())) {
                        return@now false
                    }
                }

                return@now true

            }
        }
    }

}