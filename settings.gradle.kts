pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "CashWise"
include(":app")
include(":designsystem")
include(":feature:onboarding:presentation")
include(":feature:onboarding:domain")
include(":feature:onboarding:data")
include(":feature:main:domain")
include(":feature:main:data")
include(":feature:main:presentation")
include(":common:model")
include(":storage:datastore")
include(":network:firebase-auth")
include(":feature:records:data")
include(":feature:records:domain")
include(":feature:records:presentation")
include(":storage:database")
include(":feature:dashboard:data")
include(":feature:dashboard:domain")
include(":feature:dashboard:presentation")
