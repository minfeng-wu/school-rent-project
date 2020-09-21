package com.ascending.training.dao;

import com.ascending.training.model.Order;

import java.util.List;

public interface OrderDao {
    Order save(Order order) ;
    Order update(Order order);
    boolean deleteById(Long id);
    boolean delete(Order order);
    List<ItemDao> getOrders();
    Order getOrderById(Long id);
    // List<School> getDepartmentsWithChildren();
//    Order getItemByName(String orderName);
}
