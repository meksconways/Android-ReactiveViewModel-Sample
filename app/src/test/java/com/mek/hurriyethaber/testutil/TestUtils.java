package com.mek.hurriyethaber.testutil;

import com.mek.hurriyethaber.articlenews.model.AdapterFactory;
import com.squareup.moshi.Moshi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

public class TestUtils {


    private static TestUtils INSTANCE = new TestUtils();
    private static final Moshi TEST_MOSHI = initializeMoshi();

    public TestUtils() {

    }

    private static Moshi initializeMoshi() {
        Moshi.Builder builder = new Moshi.Builder();
        builder.add(AdapterFactory.create());

        return builder.build();

    }

    public static <T> T loadJson(String path, Type type){

        try {
            String json = getStringFile(path);
            //noinspection unchecked
            return (T) TEST_MOSHI.adapter(type).fromJson(json);
        } catch (IOException e) {
            throw new IllegalArgumentException("deserialize edilemedi");
        }


    }

    public static <T> T loadJson(String path, Class<T> tClass){
        try {

            String json = getStringFile(path);
            return TEST_MOSHI.adapter(tClass).fromJson(json);

        } catch (IOException e) {
            throw new IllegalArgumentException("deserialize edilemedi");
        }
    }


    private static String getStringFile(String path) {

        try {
            StringBuilder sb = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    INSTANCE.getClass().getClassLoader().getResourceAsStream(path)
            ));
            String line;

            while ((line = bufferedReader.readLine()) != null){

                sb.append(line);

            }

            return sb.toString();

        } catch (IOException e) {
           throw new IllegalArgumentException("resource okunamadı");
        }


    }




}
