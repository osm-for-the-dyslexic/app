package org.osm4dys.app;

import android.speech.tts.TextToSpeech;
import java.util.Locale;
import android.widget.Toast;


public class JavascriptInterface{
    private MainOsmForDysActivity mContext;
    private TextToSpeech tts = null;

    public JavascriptInterface(MainOsmForDysActivity context){
		mContext = context;
	}

    public void speak(String textToSay){
        if (tts != null){
            int retval = tts.speak(textToSay, TextToSpeech.QUEUE_FLUSH, null);
            if (retval != TextToSpeech.SUCCESS){
                Toast.makeText(mContext, "ERROR IN TEXT TO SPEECH" , Toast.LENGTH_SHORT).show();    
            }
        }
    }
    
    public void setSpeachSpeed(String speed){
        //Toast.makeText(mContext, "SetSpeachSpeed: " + speed, Toast.LENGTH_SHORT).show();
        float spechRate = 1.0;
        int speedInt = Integer.parseInt(speed);
        
        switch (speedInt){
            case -10: spechRate = 0.5; break;
            case -5: spechRate = 0.8; break;
            case 0: spechRate = 1.0; break;
            case 5: spechRate = 1.2; break;
            case 10: spechRate = 1.5; break;
            default: break;
        }
        if (tts != null){
            tts.setSpeechRate (spechRate);
            Toast.makeText(mContext, "SET VOICE SPEED: " + speedInt, Toast.LENGTH_SHORT).show();
        }
    }
    
    public void setSpeachLanguage(String language){
        //Toast.makeText(mContext, "SetSpeachLanguage: " + language, Toast.LENGTH_SHORT).show();
        String[] parts = language.split("-");
        final String l1 = parts[0];
        final String l2 = parts[1].toUpperCase();

        tts = new TextToSpeech(mContext, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    tts.setLanguage(new Locale(l1,l2));
                }
            }
        });
        Toast.makeText(mContext, "SET VOICE LANGUAGE: " + language.toUpperCase(), Toast.LENGTH_SHORT).show();
    }
    
}