package de.gleex.kng.generators

import de.gleex.kng.api.Name
import de.gleex.kng.api.NameGenerator

/**
 * A name generator combining the names of two other generators. This means
 * it picks a name from one of them and then combines this name with all names of the other.
 */
internal class CombiningGenerator(
    private val first: NameGenerator,
    private val second: NameGenerator
): NameGenerator {
    private var firstInvocations = 0

    private var secondName: Name? = null

    override val isAutoResetting: Boolean by lazy { first.isAutoResetting || second.isAutoResetting }

    override val nameCount: Int by lazy { first.nameCount * second.nameCount }

    override fun next(): Name {
        if(secondName == null) {
            secondName = second.next()
        }
        if(firstInvocations == first.nameCount) {
            if(first.isAutoResetting.not()) {
                first.reset()
            }
            firstInvocations = 0
            secondName = second.next()
        }
        val next = first.next()
        firstInvocations++
        return next + secondName!!
    }

    override fun reset() {
        firstInvocations = 0
        first.reset()
        second.reset()
        secondName = null
    }

    override fun hasNext(): Boolean {
        return if(firstInvocations == first.nameCount) {
            first.hasNext() || second.hasNext()
        } else {
            first.hasNext()
        }
    }
}