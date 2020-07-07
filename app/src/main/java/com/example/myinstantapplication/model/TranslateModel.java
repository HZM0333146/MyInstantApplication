package com.example.myinstantapplication.model;

import android.os.AsyncTask;
import android.util.Log;

import com.example.myinstantapplication.R;
import com.example.myinstantapplication.bean.TranslateBean;
import com.example.myinstantapplication.api_manage.TranslationApiManage;
import com.example.myinstantapplication.api_manage.TranslationApiManageBuilder;
import com.example.myinstantapplication.presenter.ITranslatePresenter;
import com.example.myinstantapplication.util.RESTfulApiUtil;
import com.example.myinstantapplication.util.RESTfulApiUtilBuilder;

import java.io.IOException;
import java.net.MalformedURLException;

public class TranslateModel implements ITranslateModel{
    private RESTfulApiUtil apiUtil;
    private ITranslatePresenter iTranslatePresenter;
    public TranslateModel(ITranslatePresenter iTranslatePresenter){
        this.iTranslatePresenter=iTranslatePresenter;
    }

    @Override
    public void loadGoogleTranslateLanguages(TranslateBean translateBean) {
        new TranslarionApiTask().execute(translateBean);
    }
    class TranslarionApiTask extends AsyncTask<TranslateBean,Integer,String[]> {
        TranslateBean translateBean;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected String[] doInBackground(TranslateBean... tb) {
            translateBean=tb[0];
            switch (translateBean.getTranslationApiStatus()){
                case SupportedLanguages:
                    apiUtil=getSupportedLanguagesApiUtil();
                    break;
                case DetectLanguage:
                    apiUtil=getDetectLanguageApiUtil(translateBean.getTranslateInContent());

                    break;
                case TranslateLanguages:
                    apiUtil=getTranslateLanguagesApiUtil(translateBean.getTranslateInContent(),translateBean.getTranslateOutLocale().getISO3Language());
                    break;
            }
            String[] response= null;
            try {
                response = apiUtil.connectUriGetData();
            } catch (MalformedURLException e){
                Log.e("Exception:" , e.getMessage());
            } catch (IOException e) {
                Log.e("Exception:" , e.getMessage());
            }
            return response;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            iTranslatePresenter.downloadTime(values[0]);
        }
        @Override
        protected void onPostExecute(String... s) {
            super.onPostExecute(s);
            iTranslatePresenter.downloadEnd(translateBean,s);
        }
    }
    public RESTfulApiUtil getSupportedLanguagesApiUtil(){
        TranslationApiManage translationApiManage=new TranslationApiManageBuilder()
                .setTranslationApiStatus(TranslationApiManage.TranslationApiStatus.SupportedLanguages)
                .setApiKeyId(R.string.instant_API_KEY)
                .createTranslationApiManage();
        RESTfulApiUtil resTfulApiUtil=getRESTfulApiUtil(translationApiManage);
        return resTfulApiUtil;
    }
    public RESTfulApiUtil getDetectLanguageApiUtil(String q){
        TranslationApiManage translationApiManage=new TranslationApiManageBuilder()
                .setTranslationApiStatus(TranslationApiManage.TranslationApiStatus.DetectLanguage)
                .setApiKeyId(R.string.instant_API_KEY)
                .setQ(q)
                .createTranslationApiManage();
        RESTfulApiUtil resTfulApiUtil=getRESTfulApiUtil(translationApiManage);
        return resTfulApiUtil;
    }
    public RESTfulApiUtil getTranslateLanguagesApiUtil(String q,String target){
        TranslationApiManage translationApiManage=new TranslationApiManageBuilder()
                .setTranslationApiStatus(TranslationApiManage.TranslationApiStatus.TranslateLanguages)
                .setApiKeyId(R.string.instant_API_KEY)
                .setQ(q)
                .setTarget(target)
                .createTranslationApiManage();
        RESTfulApiUtil resTfulApiUtil=getRESTfulApiUtil(translationApiManage);
        return resTfulApiUtil;
    }
    public RESTfulApiUtil getRESTfulApiUtil(TranslationApiManage translationApiManage){
        RESTfulApiUtil resTfulApiUtil1 = new RESTfulApiUtilBuilder()
                .setBaseUri(translationApiManage.getURL())
                .setParameter(translationApiManage.getParameter())
                .setRequestMethod(translationApiManage.getHTTPMethod())
                .createRESTfulApiUtil();
        return resTfulApiUtil1;
    }

}
