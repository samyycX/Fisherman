package github.samyycx.fisherman.modules.gameconfig.rod

data class Rod(
    val id: String,
    val name: String,
    val lores: List<String>,
    val condition: List<String>,
    val fishgroup: String,
)
