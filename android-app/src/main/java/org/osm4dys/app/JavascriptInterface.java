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
        String l1 = parts[0];
        String l2 = parts[1].toUpperCase();
        
        Locale theLocale = new Locale(l1,l2);
        tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(theLocale);
                }
            }
        });
    }
    
}