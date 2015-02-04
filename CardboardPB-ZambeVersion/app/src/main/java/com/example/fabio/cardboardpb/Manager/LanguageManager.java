package com.example.fabio.cardboardpb.Manager;

import com.example.fabio.cardboardpb.Exception.MyException;
import com.example.fabio.cardboardpb.Manager.Enum.Language;

/**
 * Created by matteo on 04/02/2015.
 */
public class LanguageManager implements LanguageManagerInterface{

    private Language language;
    //SettingsActivity
    private String eyeLeft,eyeRight,settingsTitle,settingsStart;


    public LanguageManager(Language language) {
        this.language = language;
    }

    public void suitable(){
        if(language.equals(Language.ENGLISH)){
            eyeLeft="Left";
            eyeRight="Right";
            settingsTitle="SELECT THE EYE";
            settingsStart="PRESS HOME TO START";
        }else if(language.equals(Language.ITALIANO)){
            eyeLeft="Sinistro";
            eyeRight="Destro";
            settingsTitle="SELEZIONA L'OCCHIO";
            settingsStart="PREMI HOME PER INIZIARE";
        }else{
            try {
                throw new MyException("No language selected");
            } catch (MyException e) {
                e.printStackTrace();
            }
        }
    }

    public String getEyeLeft() {
        return eyeLeft;
    }

    public String getEyeRight() {
        return eyeRight;
    }

    public String getSettingsTitle() {
        return settingsTitle;
    }

    public String getSettingsStart() {
        return settingsStart;
    }
}
