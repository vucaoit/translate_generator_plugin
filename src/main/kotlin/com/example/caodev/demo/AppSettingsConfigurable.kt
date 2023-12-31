package com.example.caodev.demo

import com.intellij.openapi.options.Configurable
import org.jetbrains.annotations.Nls
import javax.swing.JComponent

/**
 * Provides controller functionality for application settings.
 */
internal class AppSettingsConfigurable : Configurable {
    private var mySettingsComponent: AppSettingsComponent? = null

    // A default constructor with no arguments is required because this implementation
    // is registered in an applicationConfigurable EP
    override fun getDisplayName(): @Nls(capitalization = Nls.Capitalization.Title) String {
        return "Translate Plugin"
    }

    override fun getPreferredFocusedComponent(): JComponent {
        return mySettingsComponent!!.preferredFocusedComponent
    }

    override fun createComponent(): JComponent {
        mySettingsComponent = AppSettingsComponent()
        return mySettingsComponent!!.panel
    }

    override fun isModified(): Boolean {
        val settings = AppSettingsState.instance
        val isAppLocalizationChange:Boolean = mySettingsComponent!!.appLocalizationsText != settings.appLocalization

        val isAbstractChange:Boolean = mySettingsComponent!!.appLocalizationAbstractPath != settings.appLocalizationAbstractPath
        val isEnglishChange:Boolean = mySettingsComponent!!.appLocalizationEnglishPath != settings.appLocalizationEnglishPath
        val isVietnameseChange:Boolean = mySettingsComponent!!.appLocalizationVietnamesePath != settings.appLocalizationVietnamesePath

        val isAbstractFormatChange:Boolean = mySettingsComponent!!.appLocalizationAbstractFormatText != settings.appLocalizationAbstractFormat
        val isEnglishFormatChange:Boolean = mySettingsComponent!!.appLocalizationEnglishFormatText != settings.appLocalizationEnglishFormat
        val isVietnameseFormatChange:Boolean = mySettingsComponent!!.appLocalizationVietnameseFormatText != settings.appLocalizationVietnameseFormat

        var modified = isAppLocalizationChange || isAbstractChange || isEnglishChange || isVietnameseChange || isAbstractFormatChange || isEnglishFormatChange || isVietnameseFormatChange
        return modified
    }

    override fun apply() {
        val settings = AppSettingsState.instance
        settings.appLocalization = mySettingsComponent!!.appLocalizationsText

        settings.appLocalizationAbstractPath = mySettingsComponent!!.appLocalizationAbstractPath
        settings.appLocalizationEnglishPath = mySettingsComponent!!.appLocalizationEnglishPath
        settings.appLocalizationVietnamesePath = mySettingsComponent!!.appLocalizationVietnamesePath

        settings.appLocalizationAbstractFormat = mySettingsComponent!!.appLocalizationAbstractFormatText
        settings.appLocalizationEnglishFormat = mySettingsComponent!!.appLocalizationEnglishFormatText
        settings.appLocalizationVietnameseFormat = mySettingsComponent!!.appLocalizationVietnameseFormatText
    }

    override fun reset() {
        val settings = AppSettingsState.instance
        mySettingsComponent!!.appLocalizationsText = settings.appLocalization

        mySettingsComponent!!.appLocalizationAbstractPath = settings.appLocalizationAbstractPath
        mySettingsComponent!!.appLocalizationEnglishPath = settings.appLocalizationEnglishPath
        mySettingsComponent!!.appLocalizationVietnamesePath = settings.appLocalizationVietnamesePath

        mySettingsComponent!!.appLocalizationAbstractFormatText = settings.appLocalizationAbstractFormat
        mySettingsComponent!!.appLocalizationEnglishFormatText = settings.appLocalizationEnglishFormat
        mySettingsComponent!!.appLocalizationVietnameseFormatText = settings.appLocalizationVietnameseFormat
    }

    override fun disposeUIResources() {
        mySettingsComponent = null
    }
}