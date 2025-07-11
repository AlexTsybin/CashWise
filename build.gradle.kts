import com.diffplug.gradle.spotless.SpotlessExtension
import com.diffplug.gradle.spotless.SpotlessPlugin
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.spotless) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.google.gms.google.services) apply false
    alias(libs.plugins.ksp) apply false
}

subprojects {
    apply<SpotlessPlugin>()
    configure<SpotlessExtension> {
        kotlin {
            target("src/**/*.kt")
            targetExclude("build/**/*.kt")
            ktlint()
                .editorConfigOverride(
                    mapOf(
                        "max_line_length" to "140",
                        "ktlint_standard_filename" to "disabled",
                        "ktlint_standard_final-newline" to "disabled",
                        "ktlint_standard_indent" to "disabled",
                        "ktlint_standard_no-semi" to "disabled",
                        "ktlint_standard_package-name" to "disabled",
                        "ktlint_standard_no-wildcard-imports" to "disabled",
                        "ij_kotlin_line_break_after_multiline_when_entry" to "false",
                        "ktlint_standard_no-empty-first-line-in-method-block" to "disabled",
                        "ktlint_function_signature_body_expression_wrapping" to "multiline",
                        "ktlint_function_signature_rule_force_multiline_when_parameter_count_greater_or_equal_than" to "2",
                        "ktlint_function_naming_ignore_when_annotated_with" to "Composable, Test",
                    )
                )
        }

        kotlinGradle {
            target("*.kts")
            ktlint()
        }
    }

    afterEvaluate {
        tasks.withType<KotlinCompile> {
            finalizedBy("spotlessApply")
        }
    }
}