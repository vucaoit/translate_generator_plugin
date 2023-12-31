package com.example.caodev.demo

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil

/**
 * Supports storing the application settings in a persistent way.
 * The [State] and [Storage] annotations define the name of the data and the file name where
 * these persistent application settings are stored.
 */
@State(name = "com.example.caodev.demo.AppSettingsState", storages = [Storage("SdkSettingsPlugin.xml")])
internal class AppSettingsState : PersistentStateComponent<AppSettingsState?> {
    var appLocalizationAbstractPath: String = ""
    var appLocalizationEnglishPath: String = ""
    var appLocalizationVietnamesePath: String = ""

    var appLocalizationAbstractFormat: String = AppConfig.DEFAULT_ABSTRACT_FORMAT
    var appLocalizationEnglishFormat: String = AppConfig.DEFAULT_ENGLISH_FORMAT
    var appLocalizationVietnameseFormat: String = AppConfig.DEFAULT_VIETNAMESE_FORMAT

    var appLocalization: String = AppConfig.DEFAULT_APPLOCALIZATIONS

    override fun getState(): AppSettingsState {
        return this
    }

    override fun loadState(state: AppSettingsState) {
        XmlSerializerUtil.copyBean(state, this)
    }

    companion object {
        val instance: AppSettingsState
            get() = ApplicationManager.getApplication().getService(
                AppSettingsState::class.java
            )
    }
}