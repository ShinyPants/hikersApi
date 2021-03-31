package wyl.hikers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wyl.hikers.dao.mapper.MpOrder;
import wyl.hikers.model.Order;
import wyl.hikers.model.RespBody;

import java.util.List;

@Service
public class SvcOrder {
    @Autowired
    private MpOrder mysql;

    public RespBody addOrder(Order order) {
        mysql.addOrder(order);
        return RespBody.ok(null);
    }

    public RespBody getMyOrder(Integer uid) {
        List<Order> list = mysql.getMyOrder(uid);
        return RespBody.ok(list);
    }

    public RespBody getAllOrders() {
        List<Order> list = mysql.getAllOrders();
        return RespBody.ok(list);
    }
}
