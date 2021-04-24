import com.soywiz.korge.gradle.KorgeGradlePlugin
import com.soywiz.korge.gradle.korge
import com.soywiz.korge.gradle.kormaVersion

plugins {
	kotlin("multiplatform") version "1.4.31"
}

buildscript {
	val korgePluginVersion: String by project

	repositories {
		mavenLocal()
		maven { url = uri("https://dl.bintray.com/korlibs/korlibs") }
		maven { url = uri("https://plugins.gradle.org/m2/") }
		mavenCentral()
		google()
	}
	dependencies {
		classpath("com.soywiz.korlibs.korge.plugins:korge-gradle-plugin:$korgePluginVersion")
	}
}

dependencies {
	commonMainImplementation("com.soywiz.korlibs.korma:korma-shape:$kormaVersion")
}

apply<KorgeGradlePlugin>()

korge {
	id = "org.vodopyan.crossword-platformer"
	name = "Crossword Platformer"
	supportBox2d()

	targetJvm()
	targetJs()
	targetDesktop()
}