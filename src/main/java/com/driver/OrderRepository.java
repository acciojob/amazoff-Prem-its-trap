package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class OrderRepository {
    HashMap<String,Order> orderMap = new HashMap<>();
    HashMap<String,DeliveryPartner> DeliveryPartnerMap = new HashMap<>();
    HashMap<String,String> orderPartnerPair = new HashMap<>();
    HashMap<String, List<String>> ordersByPartnerId = new HashMap<>();
    //     partnerId,list of order
    public String addOrder(Order order){
        String id = order.getId();
//        int time = order.getDeliveryTime();
        orderMap.put(id,order);
        return "New order added successfully";
    }
    public String addPartner(String id){
        DeliveryPartner deliveryPartner = new DeliveryPartner(id);
        DeliveryPartnerMap.put(id,deliveryPartner);
        return "New delivery partner added successfully";

    }
    public String addOrderPartnerPair(String orderId, String partnerId){
        List<String> partnerOrder =  ordersByPartnerId.getOrDefault(partnerId, new ArrayList<>());
        partnerOrder.add(orderId);
        ordersByPartnerId.put(partnerId,partnerOrder);
        orderPartnerPair.put(orderId,partnerId);
        DeliveryPartner partner = DeliveryPartnerMap.get(partnerId);
        partner.setNumberOfOrders(partnerOrder.size());
        return "New order-partner pair added successfully";
    }
    public Order getOrderById(String orderId){
        if(orderMap.containsKey(orderId)){
            return orderMap.get(orderId);
        }
        return null;
    }
    public DeliveryPartner getPartnerById(String partnerId){
        if(DeliveryPartnerMap.containsKey(partnerId)){
            return DeliveryPartnerMap.get(partnerId);
        }
        return null;
    }
    public Integer getOrderCountByPartnerId(String partnerId){

        int orderCount = ordersByPartnerId.getOrDefault(partnerId,new ArrayList<>()).size();
        return orderCount;
    }
    public List<String> getOrdersByPartnerId(String partnerId){
        return ordersByPartnerId.getOrDefault(partnerId, new ArrayList<>());
    }
    public List<String> getAllOrders(){
        List<String> orderList = new ArrayList<>();
        for(String order: orderMap.keySet()){
            orderList.add(order);
        }
        return  orderList;
    }
    public Integer getCountOfUnassignedOrders(){
        return  orderMap.size() - orderPartnerPair.size();
    }
    public String getLastDeliveryTimeByPartnerId(String partnerId){
        String time="";
        List<String> orderList = ordersByPartnerId.get(partnerId);
        int deliverTime = 0;
        for(String orderId : orderList){
            Order order = orderMap.get(orderId);
            deliverTime = Math.max(deliverTime,order.getDeliveryTime());
        }
        int hour = deliverTime / 60;
        String sHour="";
        if(hour < 10){
            sHour = "0"+String.valueOf(hour);
        } else {
            sHour = String.valueOf(hour);
        }
        int min = deliverTime % 60;
        String smin="";
        if(min < 10){
            smin = "0"+ String.valueOf(min);
        } else {
            smin=String.valueOf(min);
        }
        time = sHour+smin;
        return time;
    }
    public void deletePartnerById(String partnerId){
        DeliveryPartnerMap.remove(partnerId);
        List<String> list = ordersByPartnerId.getOrDefault(partnerId,new ArrayList<>());
        for(String s : list){
            orderPartnerPair.remove(s);
        }
        ordersByPartnerId.remove(partnerId);
    }
    public void deleteOrderById(String orderId){
        orderMap.remove(orderId);
        String partnerId = orderPartnerPair.get(orderId);
        orderPartnerPair.remove(orderId);
        List<String> orderList = ordersByPartnerId.getOrDefault(partnerId,new ArrayList<>());

        for(String s : orderList){
            if(s.equals(orderId)){
                orderList.remove(s);
            }
        }
        ordersByPartnerId.put(partnerId,orderList);

    }

}
