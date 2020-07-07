package com.example.myinstantapplication.view;

import com.example.myinstantapplication.bean.TranslateBean;
import com.example.myinstantapplication.bean.TranslateSpeekUser;

import java.util.Locale;

public interface ITranslationView {
    void showSupportedLanguages(String[] supportedLanguages);
    void showDetectLanguageMsg(TranslateBean translateBean);
    void showTranslateLanguagesMsg(TranslateBean translateBean);
    void showLoadingView(int p);
    void showErrorMsgView(String e);
}
