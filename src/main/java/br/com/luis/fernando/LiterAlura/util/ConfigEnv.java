package br.com.luis.fernando.LiterAlura.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Predicate;

public class ConfigEnv {
    public static void load() {
        init();
    }
    private ConfigEnv(){}
    public static void init() {
        File file = new File(".env");

        if (!file.exists()){
            throw  new RuntimeException("File .env not exists");
        }

        try(BufferedReader reader = new BufferedReader(new FileReader(".env"))) {
            reader.lines().forEach(ConfigEnv::setProperty);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static boolean isSkippedLine(String line){
        final Predicate<String> isComment = value -> value.trim().startsWith("#");
        final Predicate<String> isEmptyOrNull = value -> value.trim().isEmpty() || value.equals(null);
        return  isComment.test(line) && !isEmptyOrNull.test(line);
    }
    private static void  setProperty(String environmentVariable){
        var getProperty = environmentVariable.split("=");
        if(isSkippedLine(environmentVariable)) return;
        if (getProperty.length<2){
            System.out.println("Invalid variable, see value and try again");
            return;
        }
        String key = getProperty[0].trim();
        String value = getProperty[1].trim().replaceAll("^['\"]|['\"]$", "");
        System.setProperty(key,value);
    }
    public static String get(String key){
        final var value = System.getenv(key);
        return  value != null? value: System.getProperty(key);
    }

}
