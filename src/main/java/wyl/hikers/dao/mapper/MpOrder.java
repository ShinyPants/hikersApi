package wyl.hikers.dao.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import wyl.hikers.model.Order;

import java.util.List;

@Mapper
public interface MpOrder {

    @Insert("INSERT INTO orders(hid, uid, roomtype, sttime, edtime, personJSON) " +
            "VALUES(#{hid}, #{uid}, #{roomtype}, #{sttime}, #{edtime}, #{personJSON});")
    Integer addOrder(Order order);

    @Select("SELECT * FROM v_order_hotel WHERE uid=#{uid} ORDER BY oid DESC;")
    List<Order> getMyOrder(Integer uid);

    @Select("SELECT * FROM v_order_hotel ORDER BY oid DESC;")
    List<Order> getAllOrders();
}
