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
        maven("https://oss.sonatype.org/content/repositories/snapshots")
        mavenLocal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://oss.sonatype.org/content/repositories/snapshots")
        mavenLocal()
    }
}
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
rootProject.name = "Ver_Pelis"
include(":app")
include(":core")
include(":core:design")
include(":core:data")
include(":core:domain")
include(":core:model")
include(":core:common")
include(":core:database")
include(":core:network")
include(":core:preferences")
include(":core:firebase")
include(":feature")
include(":feature:home")

include(":feature:detail-character")
