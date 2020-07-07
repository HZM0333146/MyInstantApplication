package com.example.myinstantapplication.view;

import android.content.Intent;
import android.os.Bundle;

import com.example.myinstantapplication.BasicActivity;
import com.example.myinstantapplication.bean.TranslateSpeekUser;
import com.example.myinstantapplication.api_manage.TranslationApiManage;
import com.example.myinstantapplication.bean.TranslateBean;
import com.example.myinstantapplication.presenter.TranslatePresenter;
import com.example.myinstantapplication.ui.InstantAdapter;
import com.example.myinstantapplication.ui.ListViewTouchListener;
import com.example.myinstantapplication.util.TtsUtil;

import androidx.appcompat.widget.Toolbar;

import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.myinstantapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TranslationActivity extends BasicActivity implements ITranslationView {
    private TtsUtil ttsPeach;
    private Locale leftLocal,rightLocal;

    private ImageButton leftTranslateSpeek, rightTranslateSpeek;
    private ListView instant_list;
    private InstantAdapter instant_adapter;
    private List<TranslateBean> msgList;
    public static final int LEFT_TRANSLATE_SPEEK_NUMBER=0,RIGHT_TRANSLATE_SPEEK_NUMBER=1;
    public String[] trans_select_language=new String[]{
            "English",
            "中文"
    };
    public String[][] trans_language_text=new String[][]{
            {"Your translation is","Select what you want to say","Please use","Say what you are saying","Please enter your content"},
            {"您的翻譯結果為","選擇您想說出的話吧","請用","說出您要說的話吧","請輸入您的內容"}
    };

    TranslatePresenter translatePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        leftTranslateSpeek = (ImageButton) findViewById(R.id.left_translate_speek);
        leftTranslateSpeek.setOnClickListener(btt);
        rightTranslateSpeek = (ImageButton) findViewById(R.id.right_translate_speek);
        rightTranslateSpeek.setOnClickListener(btt);
        msgList = new ArrayList<TranslateBean>();
        instant_adapter=new InstantAdapter(this,R.layout.instant_view,msgList);
        instant_list = (ListView)findViewById(R.id.instant_list);
        instant_list.setAdapter(instant_adapter);
        instant_list.setOnItemClickListener(instant_click);
        instant_list.setOnTouchListener(swipeDismissListViewTouchListener);//左右滑動
        //設定語言
        leftLocal=Locale.ENGLISH;
        rightLocal=Locale.TAIWAN;
        //設定語音
        ttsPeach=new TtsUtil(this);
        //
        translatePresenter=new TranslatePresenter(this);
    }
    private View.OnClickListener btt=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // TODO Auto-generated method stub
            Intent it = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            it.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            it.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS,"android.speech.extras.SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS");
            switch (view.getId()) {
                case R.id.left_translate_speek:
                    it.putExtra(RecognizerIntent.EXTRA_LANGUAGE, leftLocal.getLanguage());
                    it.putExtra(RecognizerIntent.EXTRA_PROMPT, trans_language_text[1][2] + trans_select_language[1] + trans_language_text[1][3]);
                    startActivityForResult(it, LEFT_TRANSLATE_SPEEK_NUMBER);
                    break;
                case R.id.right_translate_speek:
                    it.putExtra(RecognizerIntent.EXTRA_LANGUAGE, rightLocal.getLanguage());
                    it.putExtra(RecognizerIntent.EXTRA_PROMPT, trans_language_text[0][2] + trans_select_language[0] + trans_language_text[0][3]);
                    startActivityForResult(it, RIGHT_TRANSLATE_SPEEK_NUMBER);
                    break;
            }
        }
    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        ArrayList<String> lists = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
        TranslateBean translateBean;
        switch (requestCode){
            case LEFT_TRANSLATE_SPEEK_NUMBER:
                translateBean=new TranslateBean(
                        TranslateSpeekUser.LEFT_TRANSLATE_SPEEK,
                        TranslationApiManage.TranslationApiStatus.TranslateLanguages,
                        lists.get(0),
                        rightLocal);//翻譯
                translatePresenter.setTranslateLanguagesData(translateBean);
                break;
            case RIGHT_TRANSLATE_SPEEK_NUMBER:
                translateBean=new TranslateBean(
                        TranslateSpeekUser.RIGHT_TRANSLATE_SPEEK,
                        TranslationApiManage.TranslationApiStatus.TranslateLanguages,
                        lists.get(0),
                        leftLocal);//翻譯
                translatePresenter.setTranslateLanguagesData(translateBean);
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ttsPeach.over();
    }
    //點擊聲音
    private AdapterView.OnItemClickListener instant_click=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            TranslateBean userSelectMsg=msgList.get(position);
            if(TranslateSpeekUser.LEFT_TRANSLATE_SPEEK.equals(userSelectMsg.getTranslateSpeekUser())){
                ttsPeach.speakText(userSelectMsg.getTranslateOutContent(),rightLocal);
            }else if(TranslateSpeekUser.RIGHT_TRANSLATE_SPEEK.equals(userSelectMsg.getTranslateSpeekUser())){
                ttsPeach.speakText(userSelectMsg.getTranslateOutContent(),leftLocal);
            }
        }
    };
    private ListViewTouchListener swipeDismissListViewTouchListener=new ListViewTouchListener(instant_list, new ListViewTouchListener.DismissCallbacks() {
        @Override
        public boolean canDismiss(int position) {
            return true;
        }

        @Override
        public void onDismiss(ListView listView, int[] reverseSortedPositions) {
            for (int pos : reverseSortedPositions) {
                msgList.remove(pos);
            }
            instant_adapter.notifyDataSetChanged();
        }
    });
    @Override
    public void showLoadingView(int p) {

    }

    @Override
    public void showSupportedLanguages(String[] supportedLanguages) {

    }

    @Override
    public void showDetectLanguageMsg(TranslateBean translateBean) {

    }

    @Override
    public void showTranslateLanguagesMsg(TranslateBean translateBean) {
        if(translateBean!=null){
            if(TranslateSpeekUser.LEFT_TRANSLATE_SPEEK.equals(translateBean.getTranslateSpeekUser())){
                msgList.add(translateBean);
            }else if(TranslateSpeekUser.RIGHT_TRANSLATE_SPEEK.equals(translateBean.getTranslateSpeekUser())){
                msgList.add(translateBean);
            }
            instant_adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showErrorMsgView(String e) {

    }
}
