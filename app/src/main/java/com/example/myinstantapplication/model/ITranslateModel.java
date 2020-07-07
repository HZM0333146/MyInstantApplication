package com.example.myinstantapplication.model;

import android.util.Log;

import com.example.myinstantapplication.bean.TranslateBean;

import java.util.Locale;

public interface ITranslateModel {
    void loadGoogleTranslateLanguages(TranslateBean translateBean);
}
