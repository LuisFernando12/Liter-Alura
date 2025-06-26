package br.com.luis.fernando.LiterAlura.enums;

import java.util.Arrays;
import java.util.List;

public enum Language {
    PT("portuguese", "potuguês"),
    EN("english", "inglês"),
    ES("spanish", "espanhol"),
    FR("french", "francês");

    private String language;
    private String languageInPortuguese;
    Language(String language, String languageInPortuguese) {
        this.language = language;
        this.languageInPortuguese = languageInPortuguese;
    }

    public static Language languageFromString(String lang){
        for (Language language: Language.values()){
            if (language.language.toLowerCase().contains(lang.toLowerCase()) || language.name().toUpperCase().contains(lang.toUpperCase())){
                return language;
            }
        }
        throw new IllegalArgumentException("Incorrect Language");
    }
    public static List<String> showLanguage(){
       return Arrays.stream(Language.values()).map(lang-> lang.name()+"-"+ lang.language).toList();
    }
}
