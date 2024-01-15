package github.samyycx.fisherman.modules.gameconfig.fish

import github.samyycx.fisherman.modules.config.ConfigManager
import github.samyycx.fisherman.modules.gameconfig.fish.attribute.Attribute
import github.samyycx.fisherman.modules.gameconfig.fish.attribute.AttributeParser
import github.samyycx.fisherman.modules.gameconfig.fish.basic.FishBaseParser
import github.samyycx.fisherman.modules.gameconfig.fish.event.FishEventsParser
import github.samyycx.fisherman.modules.lang.OtherUtils.maskObject
import org.bukkit.Bukkit
import org.bukkit.configuration.ConfigurationSection
import taboolib.library.reflex.Reflex.Companion.unsafeInstance
import taboolib.module.lang.Level
import taboolib.platform.util.sendLang

object FishConfigParser {

    /**
     * section : 配置文件的section
     * map : 已经解析好的fishconfig，用于处理层级关系
     *
     * 返回值:
     * Pair的第一个值只有找不到父模板的时候才不是null，值为父节点的id
     * Pair的第二个值是解析的结果，如果找不到父模板就是null
     */
    fun parseFromSection(section: ConfigurationSection, map: MutableMap<String, FishConfig>): Pair<String?, FishConfig?> {

        var fishConfig = FishConfig()

        if (section.contains("template")) {
            val requiredTemplate = section.getString("template")
            if (requiredTemplate !in map) {
                return requiredTemplate to null
            } else {
                fishConfig = map[requiredTemplate]!!.copy()
            }
        }

        fishConfig.id = section.name

        section.getConfigurationSection("base").let {

            val base = it?.let { FishBaseParser.parseSection(it) }
            fishConfig.base = maskObject(fishConfig.base, base)
            if (fishConfig.base == null) {
                Bukkit.getConsoleSender().sendLang(Level.ERROR, "Console.Error.Fish-Config-Field-NotFound",
                    section.name,
                    "base"
                )
                return null to null
            }

        }

        section.getConfigurationSection("cooked").let {
            val cooked = it?.let { FishBaseParser.parseSection(it) }
            fishConfig.cooked = maskObject(fishConfig.cooked, cooked)

            fishConfig.cooked = maskObject(fishConfig.base, fishConfig.cooked)

        }

        section.getConfigurationSection("attribute")?.let {

            val newAttributeMap = mutableMapOf<String, Attribute>()

            for (attributeId in it.getKeys(false)) {
                var attribute = AttributeParser.parseSection(it.getConfigurationSection(attributeId)!!)
                val defaultAttribute = ConfigManager.default.getConfigurationSection("FishAttributes")
                    ?.let { it1 -> AttributeParser.parseSection(it1) }
                attribute = maskObject(defaultAttribute, attribute)!!
                newAttributeMap[attributeId] = if (fishConfig.attributes != null) {
                    maskObject(fishConfig.attributes!![attributeId], attribute)!!
                } else {
                    attribute
                }
            }

            fishConfig.attributes = newAttributeMap
        }

        section.getConfigurationSection("value")?.let {

            val newValueMap = mutableMapOf<String, List<String>>()

            for (service in it.getKeys(false)) {
                newValueMap[service] = it.getStringList(service)
            }

            if (fishConfig.value != null ) {
                fishConfig.value = (fishConfig.value!! + newValueMap) as MutableMap
            } else {
                fishConfig.value = newValueMap
            }
        }

        section.getConfigurationSection("event")?.let {
            val events = FishEventsParser.parseSection(it)
            fishConfig.events = maskObject(fishConfig.events, events)
        }

        return null to fishConfig
    }

    /**
     * new对象的属性 遮蔽 original对象的属性
     * new和original应该为同类
     * 特别的，如果这个类中的属性有List，则判断List不为空的时候才遮蔽
     */



}