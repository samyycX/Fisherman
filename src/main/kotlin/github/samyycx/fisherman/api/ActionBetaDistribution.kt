package github.samyycx.fisherman.api

import org.apache.commons.math3.distribution.BetaDistribution
import taboolib.common.env.RuntimeDependency
import taboolib.module.kether.*

@RuntimeDependency("org.apache.commons:commons-math3:3.6.1")
object ActionBetaDistribution {

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
}