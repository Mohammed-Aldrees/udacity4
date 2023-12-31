package com.example.demo.controller;

import com.example.demo.controllers.OrderController;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.UserOrder;
import com.example.demo.model.persistence.repositories.OrderRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.longThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderControllerTest {
    private OrderController orderController;
    private final UserRepository userRepository = mock(UserRepository.class);
    private final OrderRepository orderRepository = mock(OrderRepository.class);

    @Before
    public void setUp() {
        orderController = new OrderController();
        com.example.demo.TestUtils.injectObjects(orderController, "userRepository", userRepository);
        com.example.demo.TestUtils.injectObjects(orderController, "orderRepository", orderRepository);
    }

    @Test
    public void Submit() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("password");
        user.setId(0L);
        Item item = new Item();
        item.setId(0L);
        item.setName("test");
        item.setPrice(new BigDecimal("3"));
        item.setDescription("test");
        Cart cart = new Cart();
        cart.setId(0L);
        List<Item> itemList = new ArrayList<>();
        itemList.add(item);
        cart.setItems(itemList);
        cart.setTotal(new BigDecimal("3"));
        cart.setUser(user);
        user.setCart(cart);
        when(userRepository.findByUsername("test")).thenReturn(user);

        ResponseEntity<UserOrder> response = orderController.submit("test");
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void SubmitNullUser() {
        String username = "test";
        User user = new User();
        user.setUsername("test");
        user.setPassword("password");
        user.setId(0L);
        Item item = new Item();
        item.setId(0L);
        item.setName("test");
        item.setPrice(new BigDecimal("3"));
        item.setDescription("test");
        Cart cart = new Cart();
        cart.setId(0L);
        List<Item> itemList = new ArrayList<>();
        itemList.add(item);
        cart.setItems(itemList);
        cart.setTotal(new BigDecimal("3"));
        cart.setUser(user);
        user.setCart(cart);
        when(userRepository.findByUsername("test")).thenReturn(null);

        ResponseEntity<UserOrder> response = orderController.submit("test");
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void getOrdersForUser() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("password");
        user.setId(0L);
        Item item = new Item();
        item.setId(0L);
        item.setName("test");
        item.setPrice(new BigDecimal("3"));
        item.setDescription("test");
        Cart cart = new Cart();
        cart.setId(0L);
        List<Item> itemList = new ArrayList<>();
        itemList.add(item);
        cart.setItems(itemList);
        cart.setTotal(new BigDecimal("3"));
        cart.setUser(user);
        user.setCart(cart);
        when(userRepository.findByUsername("test")).thenReturn(user);

        orderController.submit("test");

        ResponseEntity<List<UserOrder>> responseEntity = orderController.getOrdersForUser("test");
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void getOrdersForUserNullUser() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("password");
        user.setId(0L);
        Item item = new Item();
        item.setId(0L);
        item.setName("test");
        item.setPrice(new BigDecimal("3"));
        item.setDescription("test");
        Cart cart = new Cart();
        cart.setId(0L);
        List<Item> itemList = new ArrayList<>();
        itemList.add(item);
        cart.setItems(itemList);
        cart.setTotal(new BigDecimal("3"));
        cart.setUser(user);
        user.setCart(cart);
        when(userRepository.findByUsername("test")).thenReturn(null);

        orderController.submit("test");

        ResponseEntity<List<UserOrder>> responseEntity = orderController.getOrdersForUser("test");
        assertEquals(404, responseEntity.getStatusCodeValue());
    }

    @Test
    public void userOrderTest() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("password");
        user.setId(0L);
        Item item = new Item();
        item.setId(0L);
        item.setName("test");
        item.setPrice(new BigDecimal("3"));
        item.setDescription("test");
        Cart cart = new Cart();
        cart.setId(0L);
        List<Item> itemList = new ArrayList<>();
        itemList.add(item);
        cart.setItems(itemList);
        cart.setTotal(new BigDecimal("3"));
        cart.setUser(user);
        user.setCart(cart);
        when(userRepository.findByUsername("test")).thenReturn(user);

        ResponseEntity<UserOrder> response = orderController.submit("test");
        assertEquals(200, response.getStatusCodeValue());
    }
}
