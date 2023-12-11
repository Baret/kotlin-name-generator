package de.gleex.kng.archtests

import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.members
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses
import de.gleex.kng.api.NameGenerator

@AnalyzeClasses(packages = ["de.gleex.kng"])
class VisibilityRules {
    @ArchTest
    val noGeneratorsShouldBePublic = noClasses().that().implement(NameGenerator::class.java).should().bePublic()

    @ArchTest
    val noPublicMembersOnGenerators =
        members().that().areDeclaredInClassesThat().implement(NameGenerator::class.java).and()
            .areNotDeclaredIn(NameGenerator::class.java).should().notBePublic()
}