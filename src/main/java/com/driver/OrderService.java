package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

//    @Autowired
    OrderRepository orderRepository = new OrderRepository();
    // 1
    public String addOrder(Order order){
        return orderRepository.addOrder(order);

    }
    public String addPartner(String id){
        return  orderRepository.addPartner(id);
    }

    // 3
    public String addOrderPartnerPair(String orderId, String partnerId){
        return orderRepository.addOrderPartnerPair(orderId,partnerId);
    }

    // 4 get order by id
    public Order getOrderById(String orderId){
        return orderRepository.getOrderById(orderId);
    }

    // 5 get partner id
    public DeliveryPartner getPartnerById(String partnerId){
        return orderRepository.getPartnerById(partnerId);
    }

    // 6 getOrderCountByPartnerId
    public Integer getOrderCountByPartnerId(String partnerId){
        return orderRepository.getOrderCountByPartnerId(partnerId);
    }
    // 7 getOrdersByPartnerId
    public List<String> getOrdersByPartnerId(String partnerId){
        return orderRepository.getOrdersByPartnerId(partnerId);
    }
    // 8 getAllOrders
    public List<String> getAllOrders(){
        return orderRepository.getAllOrders();
    }

    public Integer getCountOfUnassignedOrders(){
        return orderRepository.getCountOfUnassignedOrders();
    }
    public String getLastDeliveryTimeByPartnerId(String partnerId){
        return orderRepository.getLastDeliveryTimeByPartnerId(partnerId);
    }
    public void deletePartnerById(String partnerId){
        orderRepository.deletePartnerById(partnerId);
    }

    public void deleteOrderById(String orderId){
        orderRepository.deleteOrderById(orderId);
    }
}
