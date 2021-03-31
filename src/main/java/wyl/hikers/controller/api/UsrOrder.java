package wyl.hikers.controller.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wyl.hikers.model.Order;
import wyl.hikers.model.RespBody;
import wyl.hikers.service.SvcOrder;

@RestController
@RequestMapping("/api/order")
@Slf4j
public class UsrOrder {

    @Autowired
    private SvcOrder service;

    @PostMapping
    public RespBody addOrder(@RequestBody Order order) {
        return service.addOrder(order);
    }

    @GetMapping("/mine/{uid}")
    public RespBody getMyOrder(@PathVariable Integer uid) {
        return service.getMyOrder(uid);
    }

    @GetMapping
    public RespBody getAllOrders() {
        return service.getAllOrders();
    }
}
