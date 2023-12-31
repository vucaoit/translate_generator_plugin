package com.example.caodev.demo

class AppConfig {
   companion object{
       const val TITLE = "Translate Plugin"
       const val ENGLISH = "<english>"
       const val VIETNAMESE = "<vietnamese>"
       const val VARIABLE = "<variable>"
       const val LINE_DETECT = "//InsertHere"
       const val DEFAULT_APPLOCALIZATIONS = "AppLocalizations.of(context)"
       const val DEFAULT_ABSTRACT_FORMAT = """ /// No description provided for @<variable>.
  ///
  /// In en, this message translates to:
  /// **'<english>'**
  String get <variable>;"""
       const val DEFAULT_ENGLISH_FORMAT = """  @override
  String get <variable> => '<english>';
"""
       const val DEFAULT_VIETNAMESE_FORMAT = """  @override
  String get <variable> => '<vietnamese>';
"""
   }
}