package com.example.myinstantapplication.presenter;

import com.example.myinstantapplication.bean.TranslateBean;

public interface ITranslatePresenter {
    public void downloadStar();
    public void downloadTime(int time);
    public void downloadEnd(TranslateBean translateBean,String[] text);
    public void downloadError(String error);
}
