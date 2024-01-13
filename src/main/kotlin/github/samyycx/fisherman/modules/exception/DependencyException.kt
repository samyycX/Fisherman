package github.samyycx.fisherman.modules.exception

class DependencyException(private val failedMessage: String) : Exception() {

    override val message: String
        get() = failedMessage

}