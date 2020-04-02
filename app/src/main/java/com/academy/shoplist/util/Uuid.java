package com.academy.shoplist.util;

import java.util.UUID;

public class Uuid {

    public static String generaUUID(){

         return java.util.UUID.randomUUID().toString()+System.currentTimeMillis();
    }
}
