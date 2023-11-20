package de.gleex.kng.generators

/**
 * Settings to configure a [NameGenerator][de.gleex.kng.api.NameGenerator]
 */
data class NameGeneratorSettings(val isAutoResetting: Boolean)

/**
 * Builder for [NameGeneratorSettings].
 */
class NameGeneratorSettingsBuilder {
    var autoReset = false

    fun build(): NameGeneratorSettings = NameGeneratorSettings(autoReset)
}
