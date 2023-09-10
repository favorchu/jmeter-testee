package com.example.testee.controller;

import com.example.testee.MockUtils;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class OrderController {

    /**
     * Dummy API returning the list of the IDs of the order
     *
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/orders")
    public List<Order> getOrders() throws InterruptedException {
        MockUtils.sleep(100, 100);
          return getUserOrders().entrySet().stream().map(k -> new Order(k.getKey(), k.getValue())).collect(Collectors.toList());
    }

    /**
     * Get the current order detail
     *
     * @param id
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/orders/{id}")
    public Order getOrder(@PathVariable String id) throws InterruptedException {
        MockUtils.sleep(200, 200);
        String orderName = getUserOrders().getOrDefault(id, null);
        if (StringUtils.isNotBlank(orderName))
            return new Order(id, orderName);
        return null;
    }


    /**
     * Add a new order and return the id
     *
     * @param orderName
     * @return
     * @throws InterruptedException
     */
    @PostMapping("/orders")
    public String addOrder(@RequestParam("orderName") String orderName) throws InterruptedException {
        MockUtils.sleep(200, 200);
        String uuid = UUID.randomUUID().toString();
        getUserOrders().put(uuid, orderName);
        return uuid;
    }


    /**
     * Delete order
     *
     * @param id
     * @return
     * @throws InterruptedException
     */
    @DeleteMapping("/orders/{id}")
    public void deleteOrder(@PathVariable String id) throws InterruptedException {
        MockUtils.sleep(200, 200);
        getUserOrders().remove(id);
    }

    /**
     * Return a session based orders list
     *
     * @return
     */
    protected Map<String, String> getUserOrders() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession httpSession = attr.getRequest().getSession(true);
        Map<String, String> orders = (HashMap) httpSession.getAttribute("orders");
        if (orders == null) {
            orders = new HashMap<>();
            httpSession.setAttribute("orders", orders);
        }
        return orders;
    }

}
