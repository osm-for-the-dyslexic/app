package org.osm4dys.app;

import android.speech.tts.TextToSpeech;
import java.util.Locale;


public class JavascriptInterface{
    private MainOsmForDysActivity mContext;
    private TextToSpeech tts = null;

    public JavascriptInterface(MainOsmForDysActivity context){
		mContext = context;
	}

    public void speak(String textToSay){
        if (tts != null){
            tts.speak(textToSay, TextToSpeech.QUEUE_FLUSH, null);
        }
    }
    
    public void setSpeachLanguage(String language){
        String[] parts = language.split("-");
        final String l1 = parts[0];
        final String l2 = parts[1].toUpperCase();

        tts = new TextToSpeech(mContext, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    tts.setLanguage(new Locale(l1,l2););
                }
            }
        });
    }
    
}