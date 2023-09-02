package com.example.testee.controller;

import com.example.testee.MockUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class OrderController {

    /**
     * Dummy API returning the list of the IDs of the order
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/orders")
    public List<Integer> getOrders() throws InterruptedException {
        MockUtils.sleep(100, 100);
        return Arrays.asList(1, 2, 3, 4);
    }

    /**
     * Return a order as dummy object. Random length of the payload are included
     * @param id
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/orders/{id}")
    public Order getOrder(@PathVariable int id) throws InterruptedException {
        MockUtils.sleep(200, 200);
        return new Order(id, "Order-" + id, RandomStringUtils.randomAlphabetic(100,1024));
    }

}
