package dioxo.dante.Features;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.Locale;

/**
 * Created by Dioxo on 17/03/2018.
 */

public class Hablar {

    private TextToSpeech texto_voz = null;
    private boolean isLoaded = false;

    public void init(Context context){
        texto_voz = new TextToSpeech(context, onInitListener);
    }

    private TextToSpeech.OnInitListener onInitListener = new TextToSpeech.OnInitListener() {
        @Override
        public void onInit(int i) {

            //cargar voz que el usuario defina por defecto en su android con Locale.getDefault()
            Locale spanish = Locale.getDefault();
            if(i == TextToSpeech.SUCCESS){
                int result = texto_voz.setLanguage(spanish);
                isLoaded = true;
            }

            if(i == TextToSpeech.LANG_MISSING_DATA || i == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("error", "lenguaje no soportado");
            }
        }
    };

    //Apagar la voz de dante cuando el ciclo de vida de la app se termina
    public void shutDown(){
        texto_voz.shutdown();
    }

    //agregar texto a reproducir, a la cola de reproducción
    public void agregarACola(String text){
        if(isLoaded){
            texto_voz.speak(text, TextToSpeech.QUEUE_ADD, null);
        }else {
            Log.e("Error", "TTS no ha cargado");
        }
    }

    //limpia la cola de reproducción para reproducir el siguiente texto
    public void iniciarReproduccion(String text){
        if(isLoaded){
            texto_voz.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }else {
            Log.e("Error", "TTS no ha cargado");
        }
    }
}