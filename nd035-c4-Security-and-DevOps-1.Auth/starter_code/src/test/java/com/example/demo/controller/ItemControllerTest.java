package com.example.demo.controller;

import com.example.demo.TestUtils;
import com.example.demo.controllers.ItemController;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.repositories.ItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ItemControllerTest {
    private ItemController itemController;
    private final ItemRepository itemRepository = mock(ItemRepository.class);

    @Before
    public void setUp() {
        itemController = new ItemController();
        TestUtils.injectObjects(itemController, "itemRepository", itemRepository);
    }

    @Test
    public void getItems() {
        Item item = new Item();
        item.setId(0L);
        item.setName("test");
        item.setPrice(new BigDecimal("3"));
        item.setDescription("test");
        Item item1 = new Item();
        item.setId(1L);
        item.setName("test");
        item.setPrice(new BigDecimal("2"));
        item.setDescription("test");
        List<Item> items = new ArrayList<>(2);
        items.add(item);
        items.add(item1);
        when(itemRepository.findAll()).thenReturn(items);

        ResponseEntity<List<Item>> response = itemController.getItems();
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void getItemById() {
        Item item = new Item();
        item.setId(0L);
        item.setName("test");
        item.setPrice(new BigDecimal("3"));
        item.setDescription("test");
        when(itemRepository.findById(0L)).thenReturn(java.util.Optional.of(item));

        ResponseEntity<Item> response = itemController.getItemById(0L);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void getItemsByName() {
        Item item = new Item();
        item.setId(0L);
        item.setName("test");
        item.setPrice(new BigDecimal("3"));
        item.setDescription("test");
        List<Item> items = new ArrayList<>(2);
        items.add(item);
        when(itemRepository.findByName("test")).thenReturn(items);

        ResponseEntity<List<Item>> response = itemController.getItemsByName("test");

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void itemTest(){
        Item item = new Item();
        item.setId(0L);
        item.setName("test");
        item.setPrice(new BigDecimal("3"));
        item.setDescription("test");
        assertEquals(item.getPrice(),new BigDecimal("3"));
        assertEquals((long) item.getId(),0L);
        assertEquals(item.getName(),"test");
        assertEquals(item.getDescription(),"test");
        Item item1 = new Item();
        item1.setId(0L);
        assertEquals(item,item1);
    }
}
