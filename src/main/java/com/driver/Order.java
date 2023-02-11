package com.driver;

import java.util.Arrays;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        String time[] = deliveryTime.split(":");
        int arr[] = new int[2];
        for(int i=0;i<2;i++){
            arr[i]=Integer.parseInt(time[i]);
        }
        this.deliveryTime = (arr[0]*60)+arr[1];
        this.id = id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }


    public int getDeliveryTime() {return deliveryTime;}
}
