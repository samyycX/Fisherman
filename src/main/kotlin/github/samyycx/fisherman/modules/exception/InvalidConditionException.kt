package github.samyycx.fisherman.modules.exception

import github.samyycx.fisherman.modules.lang.LangUtils
import github.samyycx.fisherman.modules.lang.LangUtils.parseForConsole
import taboolib.module.lang.Level

class InvalidConditionException(
    private val identifier: String,
    private val lastCondition: String
): Exception() {

    override val message: String
        get() = "Console.Error.Condition-Invalid".parseForConsole(Level.ERROR, identifier, lastCondition)
}