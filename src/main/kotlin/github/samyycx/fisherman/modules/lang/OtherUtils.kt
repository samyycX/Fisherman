package github.samyycx.fisherman.modules.lang

import taboolib.library.reflex.Reflex.Companion.unsafeInstance

object OtherUtils {

    @Suppress("UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION")
    fun <T> maskObject(original: T?, new: T?): T? {

        if (original == null) {
            return new
        }
        if (new == null) {
            return null
        }
        val result = new!!::class.java.unsafeInstance() as T

        fun <T> replacement(originalValue: T?, newValue: T): T? {
            if (newValue is List<*>) {
                return newValue.ifEmpty { originalValue }
            }
            return newValue ?: originalValue
        }
        new!!::class.java.declaredFields
            .forEach {
                it.isAccessible = true
                val originalValue = it.get(original)
                val newValue = it.get(new)
                val f = result!!::class.java.getDeclaredField(it.name)
                f.isAccessible = true
                f.set(result, replacement(originalValue, newValue))
            }

        return result
    }

}