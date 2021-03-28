package wyl.hikers.service;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wyl.hikers.dao.mapper.MpHotel;
import wyl.hikers.model.Hotel;
import wyl.hikers.model.Hotel2;
import wyl.hikers.model.RespBody;

import java.util.List;

@Service
public class SvcHotel {
    @Autowired
    private MpHotel mysql;

    public RespBody addHotel(Hotel hotel) {
        if ((hotel.getRooms() != null ? hotel.getRooms().length : 0) > 0)
            hotel.setRoomsjson(JSON.toJSONString(hotel.getRooms()));
        mysql.addHotel(hotel);
        return RespBody.ok(null);
    }

    public RespBody getHotels() {
        List<Hotel2> list = mysql.getHotels();
        return RespBody.ok(list);
    }

    public RespBody updateHotel(Hotel hotel) {
        if ((hotel.getRooms() != null ? hotel.getRooms().length : 0) > 0)
            hotel.setRoomsjson(JSON.toJSONString(hotel.getRooms()));
        mysql.updateHotel(hotel);
        return RespBody.ok(null);
    }
}
