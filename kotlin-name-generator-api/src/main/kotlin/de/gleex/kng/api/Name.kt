package de.gleex.kng.api

/**
 * Names may be generated and combined with other names. When used you most probably just need it [asString].
 */
@JvmInline
value class Name(private val name: String) {
    /**
     * Returns this [Name] as classic [String].
     */
    fun asString(): String = name

    /**
     * Combines this name with [otherName] by simple concatenating them.
     *
     * @param otherName to add to this name
     * @return a new [Name] concatenating this and [otherName]
     */
    operator fun plus(otherName: Name): Name {
        return Name(name + otherName.name)
    }
}