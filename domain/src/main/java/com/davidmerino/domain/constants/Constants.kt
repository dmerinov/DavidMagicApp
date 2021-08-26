package com.davidmerino.domain.constants

/**
 * Constants
 */
class Constants {
    companion object {
        val EMPTY_STRING: String = ""

        val DEFAULT_LONG: Long = 0

        val DEFAULT_INT: Int = 0

        fun preferencesName(buildType: BuildType): String = when (buildType) {
            BuildType.DEBUG -> "t21_debug"
            BuildType.RELEASE -> "t21"
        }

        fun notificationChannelId(buildType: BuildType): String = when (buildType) {
            BuildType.DEBUG -> "t21_debug"
            BuildType.RELEASE -> "t21"
        }

        const val GEN_X_COORDINATES_LAT = 39.46927593708924
        const val GEN_X_COORDINATES_LONG = -6.380133059817144
        const val DADOS_FUERA_COORDINATES_LAT = 39.4730392896283
        const val DADOS_FUERA_COORDINATES_LONG = -6.380556815638426
    }

}

fun buildType(type: String): BuildType = when (type) {
    "debug" -> BuildType.DEBUG
    else -> BuildType.RELEASE
}

enum class BuildType {
    DEBUG, RELEASE
}