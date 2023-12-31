package com.example.caodev.demo

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.command.WriteCommandAction

class TranslateAction : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        val editor = event.getRequiredData(CommonDataKeys.EDITOR)
        val document = editor.document
        // Work off of the primary caret to get the selection info
        val primaryCaret = editor.caretModel.primaryCaret
        val start: Int = primaryCaret.selectionStart
        val end: Int = primaryCaret.selectionEnd
        val textSelected = document.text.subSequence(start, end).toString()

        TranslateDialogWrapper(AppConfig.TITLE, textSelected){
            WriteCommandAction.runWriteCommandAction(
                event.project
            ) { document.replaceString(start, end, "${AppConfig.DEFAULT_APPLOCALIZATIONS}.$it") }
        }.showAndGet()

        primaryCaret.removeSelection()
    }

}