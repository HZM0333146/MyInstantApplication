package com.example.myinstantapplication.api_manage;

public class TranslationApiManageBuilder {
    private TranslationApiManage.TranslationApiStatus translationApiStatus;
    private String q, target;
    private int apiKeyId;
    public TranslationApiManageBuilder setTranslationApiStatus(TranslationApiManage.TranslationApiStatus translationApiStatus) {
        this.translationApiStatus = translationApiStatus;
        return this;
    }
    public TranslationApiManageBuilder setApiKeyId(int apiKeyId) {
        this.apiKeyId = apiKeyId;
        return this;
    }
    public TranslationApiManageBuilder setQ(String q) {
        this.q = q;
        return this;
    }

    public TranslationApiManageBuilder setTarget(String target) {
        this.target = target;
        return this;
    }
    public TranslationApiManage createTranslationApiManage() {
        if(target!=null){
            return new TranslationApiManage(translationApiStatus,apiKeyId, q, target);
        }
        if(q!=null){
            return new TranslationApiManage(translationApiStatus,apiKeyId, q);
        }
        return new TranslationApiManage(translationApiStatus,apiKeyId);
    }

}
