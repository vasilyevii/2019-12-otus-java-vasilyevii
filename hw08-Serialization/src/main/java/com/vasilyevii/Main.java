package com.vasilyevii;

import com.google.gson.Gson;

public class Main {

    public static void main(String[] args) {


        BagOfPrimitives obj = new BagOfPrimitives();
        System.out.println(obj);

        DIYGson diyGson = new DIYGson();
        String diyJson = diyGson.toJson(obj);
        System.out.println("DIY: " + diyJson);

        Gson gson = new Gson();
        String json = gson.toJson(obj);
        System.out.println("Ril: " + json);

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


//        BagOfPrimitives obj2 = gson.fromJson(diyJson, BagOfPrimitives.class);
//        BagOfPrimitives obj3 = gson.fromJson(json, BagOfPrimitives.class);
//        System.out.println(obj.equals(obj2));
//        System.out.println(obj2);

    }

}
