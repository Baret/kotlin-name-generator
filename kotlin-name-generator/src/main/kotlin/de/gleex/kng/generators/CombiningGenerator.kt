package de.gleex.kng.generators

import de.gleex.kng.api.Name
import de.gleex.kng.api.NameGenerator

/**
 * TODO: docs
 */
internal class CombiningGenerator(
    private val first: NameGenerator,
    private val second: NameGenerator
): NameGenerator {
    private var secondName: Name? = null

    override val isAutoResetting: Boolean = first.isAutoResetting || second.isAutoResetting

    override val nameCount: Int = first.nameCount * second.nameCount

    override fun next(): Name {
        if(secondName == null) {
            secondName = second.next()
        }
        if(first.hasNext().not()) {
            first.reset()
            secondName = second.next()
        }
        return first.next() + secondName!!
    }

    override fun reset() {
        first.reset()
        second.reset()
        secondName = null
    }

    override fun hasNext(): Boolean {
        return if(second.hasNext().not()) {
            first.hasNext()
        } else {
            second.hasNext()
        }
    }
}