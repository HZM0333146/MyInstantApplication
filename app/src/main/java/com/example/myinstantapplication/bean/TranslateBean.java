package com.example.myinstantapplication.bean;

import com.example.myinstantapplication.api_manage.TranslationApiManage;

import java.util.Locale;

public class TranslateBean {
    private TranslateSpeekUser translateSpeekUser;
    private TranslationApiManage.TranslationApiStatus translationApiStatus;
    private String translateInContent,translateOutContent;
    private Locale translateInLocale,translateOutLocale;
    public  TranslateBean(TranslateSpeekUser translateSpeekUser,TranslationApiManage.TranslationApiStatus translationApiStatus){
        this(translateSpeekUser,translationApiStatus,null,null);
    }
    public  TranslateBean(TranslateSpeekUser translateSpeekUser,TranslationApiManage.TranslationApiStatus translationApiStatus,String translateInContent){
        this(translateSpeekUser,translationApiStatus,translateInContent,null,null,null);
    }
    public  TranslateBean(TranslateSpeekUser translateSpeekUser,TranslationApiManage.TranslationApiStatus translationApiStatus,String translateInContent,Locale translateOutLocale){
        this(translateSpeekUser,translationApiStatus,translateInContent,null,null,translateOutLocale);
    }
    public  TranslateBean(TranslateSpeekUser translateSpeekUser,TranslationApiManage.TranslationApiStatus translationApiStatus,String translateInContent,Locale translateInLocale,String translateOutContent,Locale translateOutLocale){
        setTranslateSpeekUser(translateSpeekUser);
        setTranslationApiStatus(translationApiStatus);
        setTranslateInContent(translateInContent);
        setTranslateInLocale(translateInLocale);
        setTranslateOutContent(translateOutContent);
        setTranslateOutLocale(translateOutLocale);
    }
    public void setTranslateSpeekUser(TranslateSpeekUser translateSpeekUser){
        this.translateSpeekUser=translateSpeekUser;
    }
    public TranslateSpeekUser getTranslateSpeekUser(){
        return translateSpeekUser;
    }
    public void setTranslateInContent(String translateInContent){
        this.translateInContent = translateInContent;
    }
    public void setTranslationApiStatus(TranslationApiManage.TranslationApiStatus translationApiStatus){
        this.translationApiStatus=translationApiStatus;
    }
    public TranslationApiManage.TranslationApiStatus getTranslationApiStatus(){
        return translationApiStatus;
    }
    public String getTranslateInContent(){
        return translateInContent;
    }
    public void setTranslateInLocale(Locale translateInLocale){
        this.translateInLocale = translateInLocale;
    }
    public Locale getTranslateInLocale(){
        return translateInLocale;
    }
    public void setTranslateOutContent(String translateOutContent){
        this.translateOutContent = translateOutContent;
    }
    public String getTranslateOutContent(){
        return translateOutContent;
    }
    public void setTranslateOutLocale(Locale translateOutLocale){
        this.translateOutLocale = translateOutLocale;
    }
    public Locale getTranslateOutLocale(){
        return translateOutLocale;
    }

}
