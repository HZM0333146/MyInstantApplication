package com.example.myinstantapplication.util;

import android.content.Context;
import android.os.Build;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

/**
 * Created by J on 2017/10/26.
 */

public class TtsUtil {
	private TextToSpeech textToSpeech;

	public TtsUtil(Context context){
        init(context,Locale.getDefault());
	}
	public TtsUtil(Context context, Locale locale){
        init(context,locale);
	}
	private void init(Context context, Locale locale) {
		if (textToSpeech == null) {
			textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
				@Override
				public void onInit(int i) {

				}
			});
		}
		textToSpeech.setPitch((float) 1);    //語調(1為正常語調；0.5比正常語調低一倍；2比正常語調高一倍)
		textToSpeech.setSpeechRate((float) 0.5);    //速度(1為正常速度；0.5比正常速度慢一倍；2比正常速度快一倍)
		textToSpeech.setLanguage(locale);
	}
	public void changeSpeakLanguage(Locale locale){
	    textToSpeech.setLanguage(locale);
	}
	public  void speakText(final String text){
		speakText(text,null);
	}
	public  void speakText(final String text,Locale locale){
		if(locale!=null){
			changeSpeakLanguage(locale);
		}
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH,null,"100");
		}else {
			textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
		}
	}
	public void over(){
		textToSpeech.stop();
		textToSpeech.shutdown();
	}
}
