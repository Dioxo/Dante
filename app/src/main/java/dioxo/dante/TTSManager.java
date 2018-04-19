package dioxo.dante;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.Locale;

import ai.api.AIListener;
import ai.api.android.AIConfiguration;
import ai.api.android.AIService;

/**
 * Created by Dioxo on 17/03/2018.
 */

public class TTSManager{

    private TextToSpeech textToSpeech = null;
    private boolean isLoaded = false;

    public void init(Context context){
        try {
            textToSpeech = new TextToSpeech(context, onInitListener);
        }catch (Exception e){

        }
    }

    private TextToSpeech.OnInitListener onInitListener = new TextToSpeech.OnInitListener() {
        @Override
        public void onInit(int i) {
            Locale spanish = new Locale("es", "ES");
            if(i == TextToSpeech.SUCCESS){
                int result = textToSpeech.setLanguage(spanish);
                isLoaded = true;
            }

            if(i == TextToSpeech.LANG_MISSING_DATA || i == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("error", "lenguaje no soportado");
            }
        }
    };


    public void shutDown(){
        textToSpeech.shutdown();
    }

    public void addQueue(String text){
        if(isLoaded){
            textToSpeech.speak(text, TextToSpeech.QUEUE_ADD, null);
        }else {
            Log.e("Error", "TTS no ha cargado");
        }
    }
    public void initQueue(String text){
        if(isLoaded){
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }else {
            Log.e("Error", "TTS no ha cargado");
        }
    }
}