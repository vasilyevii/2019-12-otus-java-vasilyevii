package com.vasilyevii;

import com.google.gson.Gson;

public class Main {

    public static void main(String[] args) {

        BagOfPrimitives obj = new BagOfPrimitives();

        DIYGson diyGson = new DIYGson();
        Gson gson = new Gson();

        String diyJson = diyGson.toJson(obj);
        System.out.println("DIY   : " + diyJson);

        String json = gson.toJson(obj);
        System.out.println("Origin: " + json);

        try {
            BagOfPrimitives objFromDiyJson = gson.fromJson(diyJson, BagOfPrimitives.class);
            System.out.println(objFromDiyJson);
        } catch (Exception e) {
            System.out.println("DIY fall");
        }

        try {
            BagOfPrimitives objFromOriginJson = gson.fromJson(json, BagOfPrimitives.class);
            System.out.println(objFromOriginJson);
        } catch (Exception e) {
            System.out.println("Origin fall");
        }

        System.out.println("DIY   : " + diyGson.toJson(null));
        System.out.println("Origin: " + gson.toJson(null));

        System.out.println("DIY   : " + diyGson.toJson('a'));
        System.out.println("Origin: " + gson.toJson('a'));

        System.out.println("DIY   : " + diyGson.toJson(new int[] {7, 8, 9}));
        System.out.println("Origin: " + gson.toJson(new int[] {7, 8, 9}));

    }

}
