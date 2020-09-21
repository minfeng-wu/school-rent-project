package com.ascending.training.dao;

import com.ascending.training.model.Item;

import java.util.List;

public interface ItemDao {
    Item save(Item item) ;
    Item update(Item item);
    boolean deleteByName(String itemName);
    boolean delete(Item item);
    List<Item> getItems();
    Item getItemById(Long id);
    // List<School> getDepartmentsWithChildren();
    Item getItemByName(String itemName);

}
