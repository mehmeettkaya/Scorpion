allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.0.3")
        classpath(kotlin("gradle-plugin", version = Versions.kotlin))
        classpath(kotlin("android-extensions", version = Versions.kotlin))
        classpath(Deps.hiltGradlePlugin)
    }
}

task("clean", Delete::class) {
    delete = setOf(rootProject.buildDir)
}
