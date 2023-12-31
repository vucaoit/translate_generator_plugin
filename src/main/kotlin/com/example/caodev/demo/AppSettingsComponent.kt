package com.example.caodev.demo

import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBTextArea
import com.intellij.ui.components.JBTextField
import com.intellij.util.ui.FormBuilder
import javax.swing.JComponent
import javax.swing.JPanel

/**
 * Supports creating and managing a [JPanel] for the Settings Dialog.
 */
class AppSettingsComponent {
    val panel: JPanel
    private val appLocalizations = JBTextField()
    private val appLocalizationAbstract = JBTextField()
    private val appLocalizationEnglish = JBTextField()
    private val appLocalizationVietnamese = JBTextField()
    private val appLocalizationAbstractFormat = JBTextArea(5,5)
    private val appLocalizationEnglishFormat = JBTextArea(5,5)
    private val appLocalizationVietnameseFormat = JBTextArea(5,5)

    init {
        panel = FormBuilder.createFormBuilder()
            .addLabeledComponent(JBLabel("AppLocalization : "), appLocalizations, 1, false)
            .addLabeledComponent(JBLabel("AppLocalization path: "), appLocalizationAbstract, 2, false)
            .addLabeledComponent(JBLabel("AppLocalizationEn path: "), appLocalizationEnglish, 3, false)
            .addLabeledComponent(JBLabel("AppLocalizationVi path: "), appLocalizationVietnamese, 4, false)
            .addLabeledComponent(JBLabel("AppLocalizationAbstract format: "), appLocalizationAbstractFormat, 5, false)
            .addLabeledComponent(JBLabel("AppLocalizationEn format: "), appLocalizationEnglishFormat, 6, false)
            .addLabeledComponent(JBLabel("AppLocalizationVi format: "), appLocalizationVietnameseFormat, 7, false)
            .addComponentFillVertically(JPanel(), 0)
            .panel
    }

    val preferredFocusedComponent: JComponent
        get() = appLocalizationAbstract

    var appLocalizationAbstractPath: String
        get() = appLocalizationAbstract.text
        set(newText) {
            appLocalizationAbstract.text = newText
        }
    var appLocalizationEnglishPath: String
        get() = appLocalizationEnglish.text
        set(newText) {
            appLocalizationEnglish.text = newText
        }
    var appLocalizationVietnamesePath: String
        get() = appLocalizationVietnamese.text
        set(newText) {
            appLocalizationVietnamese.text = newText
        }
    var appLocalizationAbstractFormatText: String
        get() = appLocalizationAbstractFormat.text
        set(newText) {
            appLocalizationAbstractFormat.text = newText
        }
    var appLocalizationEnglishFormatText: String
        get() = appLocalizationEnglishFormat.text
        set(newText) {
            appLocalizationEnglishFormat.text = newText
        }
    var appLocalizationVietnameseFormatText: String
        get() = appLocalizationVietnameseFormat.text
        set(newText) {
            appLocalizationVietnameseFormat.text = newText
        }
    var appLocalizationsText: String
        get() = appLocalizations.text
        set(newText) {
            appLocalizations.text = newText
        }
}