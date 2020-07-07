package com.example.myinstantapplication.api_manage;

public class TranslationApiManage extends BaseApiManage{
    public enum TranslationApiStatus {
        SupportedLanguages,//不需要家標籤
        DetectLanguage,//需要加標籤(q=翻譯文字)
        TranslateLanguages//需要加標籤(q=翻譯文字,target=要翻譯的語系)
    }
    private TranslationApiStatus self;
    private String q, target;
    private String apiKey;
    public TranslationApiManage(TranslationApiStatus self, int apiKey){
        this(self,apiKey,null);
    }
    public TranslationApiManage(TranslationApiStatus self, int apiKey,String q){
        this(self,apiKey,q,null);
    }
    public TranslationApiManage(TranslationApiStatus self, int apiKey,String q,String target){
        this.self=self;
        this.apiKey=getApiKey(apiKey);
        this.q=q;
        this.target=target;
    }
    @Override
    public String getURL(){
        switch(self){
            case DetectLanguage:
                url = "https://translation.googleapis.com/language/translate/v2/detect";
            break;
            case TranslateLanguages:
                url = "https://translation.googleapis.com/language/translate/v2";
            break;
            case SupportedLanguages:
                url = "https://translation.googleapis.com/language/translate/v2/languages";
            break;
        }
        return url;
    }
    @Override
    public String getHTTPMethod() {
        if (TranslationApiStatus.SupportedLanguages.equals(self)){
            httpMethod="GET";
        } else {
            httpMethod="POST";
        }
        return httpMethod;
    }

    @Override
    public String getParameter() {
        switch(self){
            case SupportedLanguages:
                parameter=getSupportedLanguagesParameter();
                break;
            case DetectLanguage:
                parameter=getDetectLanguageParameter();
                break;
            case TranslateLanguages:
                parameter=getTranslateLanguageParameter();
                break;
        }
        return parameter;
    }
    private String getSupportedLanguagesParameter(){
        String supportedLanguagesParameter=new ParameterBuilder()
                .append("key",apiKey)
                .toParameterString();
        return supportedLanguagesParameter;
    }
    private String getDetectLanguageParameter(){
        String detectLanguageParamete=new ParameterBuilder()
                .append("key",apiKey)
                .append("q",q)
                .toParameterString();
        return detectLanguageParamete;
    }
    private String getTranslateLanguageParameter(){
        String translateLanguageParameter=new ParameterBuilder()
                .append("key",apiKey)
                .append("q",q)
                .append("target",target)
                .toParameterString();
        return translateLanguageParameter;
    }
}
