package com.ruanorz.sdos.utils;

/**
 * Created by ruano on 09/02/2018.
 */

public class Constants {

    public static String IS_ADMINISTRATOR = "IS_ADMINISTRATOR";
    private static String BASE_URL = "https://data.ct.gov/resource/";
    private static Integer CACHE_TIME = 6000;

    public static String getBASE_URL() {
        return BASE_URL;
    }

    public static Integer getCACHE_TIME() {
        return CACHE_TIME;
    }
}
