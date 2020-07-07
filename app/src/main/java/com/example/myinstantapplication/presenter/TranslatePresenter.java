package com.example.myinstantapplication.presenter;

import com.example.myinstantapplication.bean.TranslateBean;
import com.example.myinstantapplication.model.ITranslateModel;
import com.example.myinstantapplication.model.TranslateModel;
import com.example.myinstantapplication.view.ITranslationView;

public class TranslatePresenter implements ITranslatePresenter{
    ITranslationView iTranslationView;
    ITranslateModel iTranslateModel;
    TranslateBean translateBean;
    public TranslatePresenter(ITranslationView iTranslationView){
        this.iTranslationView=iTranslationView;
        this.iTranslateModel=new TranslateModel(this);
    }
    public void setTranslateLanguagesData(TranslateBean translateBean){
        iTranslateModel.loadGoogleTranslateLanguages(translateBean);
    }

    @Override
    public void downloadStar() {

    }

    @Override
    public void downloadTime(int time) {
        iTranslationView.showLoadingView(time);
    }
    @Override
    public void downloadEnd(TranslateBean translateBean,String... re) {

        switch (re[0]){
            case "200":
                switch (translateBean.getTranslationApiStatus()){
                    case SupportedLanguages:
                        String[]del=new String[2];
                        //del==re[1];
                        iTranslationView.showSupportedLanguages(del);
                        break;
                    case DetectLanguage:
                        //translateBean.setTranslateOutLocale(re[1]);
                        iTranslationView.showDetectLanguageMsg(translateBean);
                        break;
                    case TranslateLanguages:
                        //translateBean.setTranslateOutContent(re[1]);
                        iTranslationView.showTranslateLanguagesMsg(translateBean);
                        break;
                }

                break;
            default:
                downloadError("翻譯失敗");
                break;
        }
    }
    @Override
    public void downloadError(String error) {
        iTranslationView.errorMsgView(error);
    }


}
