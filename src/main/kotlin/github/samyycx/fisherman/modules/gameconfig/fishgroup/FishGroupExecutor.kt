package github.samyycx.fisherman.modules.gameconfig.fishgroup

import github.samyycx.fisherman.modules.gameconfig.fish.FishConfig
import github.samyycx.fisherman.modules.gameconfig.fish.FishConfigManager
import github.samyycx.fisherman.utils.KetherUtils
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import taboolib.common5.RandomList
import taboolib.module.lang.Level
import taboolib.platform.util.sendLang

object FishGroupExecutor {

    private fun generateWeightMap(player: Player, id: String): MutableMap<String, Int>? {

        val items = mutableMapOf<String, Int>()

        val group = FishGroupManager.getById(id)
        if (group == null) {
            Bukkit.getConsoleSender().sendLang(
                Level.ERROR,
                "Console.Error.FishGroup-NotFound",
                id
            )
            return null
        }

        if (group.condition.isNotEmpty()) {
            var available = KetherUtils.eval(player,
                group.condition
            ).get()
            println(available)
            available = available.toString().toBooleanStrict()
            if (!available) { return null }

            player.world.hasStorm()
        }

        group.items.forEach {
            val splited = it.split(" ")
            if (splited.size == 1) {
                generateWeightMap(player, it)?.let { it1 -> items.putAll(it1) }
            } else {
                items[splited[0]] = splited[1].toInt()
            }
        }

        return items

    }

     fun executeFishGroup(player: Player, id: String): FishConfig? {

        var result: FishConfig? = null

        var map = generateWeightMap(player, id) ?: return null
        var random = RandomList(*map.toList().toTypedArray())

        while (result == null) {
            val itemString = random.random()!!

            if (!FishConfigManager.contains(itemString)) {
                map = generateWeightMap(player, itemString) ?: continue
                random = RandomList(*map.toList().toTypedArray())
            } else {
                result = FishConfigManager.getById(itemString)
            }
        }

        return result
    }

}