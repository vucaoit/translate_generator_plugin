package com.example.caodev.demo

import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.ui.Messages
import com.intellij.util.ui.JBUI
import java.awt.*
import java.io.*
import javax.swing.*
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener


class TranslateDialogWrapper(projectTitle: String, content: String,onCallBack: (variable:String) -> Unit) : DialogWrapper(true) {
    private var contentString: String
    private val callback: (variable:String) -> Unit
    private val variableField = JTextField(15)
    private val englishField = JTextField(15)
    private val vietnameseField = JTextField(15)

    init {
        title = projectTitle
        isOKActionEnabled = false
        contentString = removeQuotes(content)
        callback = onCallBack
        if(content.trim().isEmpty()){
            Messages.showMessageDialog("Please select text!",AppConfig.TITLE,Messages.getWarningIcon())
            close(0)
        }
        variableField.document.addDocumentListener(object : DocumentListener {
            override fun changedUpdate(e: DocumentEvent?) {
                validate()
            }

            override fun removeUpdate(e: DocumentEvent?) {
                validate()
            }

            override fun insertUpdate(e: DocumentEvent?) {
                validate()
            }
        })
        vietnameseField.document.addDocumentListener(object : DocumentListener {
            override fun changedUpdate(e: DocumentEvent?) {
                validate()
            }

            override fun removeUpdate(e: DocumentEvent?) {
                validate()
            }

            override fun insertUpdate(e: DocumentEvent?) {
                validate()
            }
        })

        init()
    }

    override fun validate() {
        isOKActionEnabled = variableField.text.trim().isNotEmpty() && vietnameseField.text.trim().isNotEmpty()
    }

    override fun doOKAction() {
        super.doOKAction()
        val abstractCodeContent =
            AppSettingsState.instance.appLocalizationAbstractFormat.replace(AppConfig.ENGLISH, englishField.text)
                .replace(AppConfig.VARIABLE, variableField.text)
        val englishCodeContent =
            AppSettingsState.instance.appLocalizationEnglishFormat.replace(AppConfig.ENGLISH, englishField.text)
                .replace(AppConfig.VARIABLE, variableField.text)
        val vietnameseCodeContent =
            AppSettingsState.instance.appLocalizationVietnameseFormat.replace(AppConfig.VIETNAMESE, vietnameseField.text)
                .replace(AppConfig.VARIABLE, variableField.text)
        readFile(AppSettingsState.instance.appLocalizationAbstractPath,"$abstractCodeContent\n")
        readFile(AppSettingsState.instance.appLocalizationEnglishPath,"$englishCodeContent\n")
        readFile(AppSettingsState.instance.appLocalizationVietnamesePath,"$vietnameseCodeContent\n")
        callback(variableField.text)
    }

    private fun removeQuotes(input: String): String {
        var modifiedString = input
        if (modifiedString.startsWith("'") && modifiedString.endsWith("'")) {
            modifiedString = modifiedString.removeSurrounding("'", "'")
        }else if (modifiedString.startsWith("\"") && modifiedString.endsWith("\"")) {
            modifiedString = modifiedString.removeSurrounding("\"", "\"")
        }
        return modifiedString
    }
    private fun readFile(filePath: String,content:String) {
        val file = File(filePath)
        if (!file.exists() || !file.isFile) {
            Messages.showMessageDialog("File does not exist.\n$filePath",AppConfig.TITLE,Messages.getErrorIcon())
            throw IllegalArgumentException("File does not exist or is not a regular file")
        }

        var index = 0
        var isContinue = true
        file.forEachLine { line ->
            if (isContinue) {
                index++
            }
            if (line.contains(AppConfig.LINE_DETECT)) {
                isContinue = false
            }
        }
        if(isContinue){
            Messages.showMessageDialog("Can't detect '//InsertHere' string at File \n$filePath",AppConfig.TITLE,Messages.getErrorIcon())
        }
        insertTextAtLine(filePath, index, content)
    }

    private fun insertTextAtLine(filePath: String, lineNumber: Int, textToInsert: String) {
        val file = File(filePath)
        require(file.exists() && file.isFile) { "File does not exist or is not a regular file" }

        val lines = file.readLines().toMutableList()

        require(lineNumber in 1..lines.size + 1) { "Line number is out of bounds" }

        lines.add(lineNumber, textToInsert)

        BufferedWriter(FileWriter(file, Charsets.UTF_8)).use { writer ->
            for (line in lines) {
                writer.write("$line\n")
            }
        }
    }

    override fun createCenterPanel(): JComponent {
        val gridPanel = JPanel(GridBagLayout())
        val layout = GridBagConstraints()
        // JLabels
        val variable = JLabel("Variable")
        val english = JLabel("English")
        val vietnamese = JLabel("Vietnamese")

        //set up
        englishField.text = removeQuotes(contentString)
        englishField.isEnabled = false
        layout.anchor = GridBagConstraints.LINE_START
        layout.insets = JBUI.insetsLeft(10)
        // variableField
        layout.gridx = 0
        layout.gridy = 0
        gridPanel.add(variable, layout)
        layout.gridx = 2
        layout.gridy = 0
        gridPanel.add(variableField, layout)

        // englishField
        layout.gridx = 0
        layout.gridy = 1
        gridPanel.add(english, layout)
        layout.gridx = 2
        layout.gridy = 1
        gridPanel.add(englishField, layout)


        // vietnameseField
        layout.gridx = 0
        layout.gridy = 2
        gridPanel.add(vietnamese, layout)
        layout.gridx = 2
        layout.gridy = 2
        gridPanel.add(vietnameseField, layout)

        return gridPanel
    }
}