package wyl.hikers.dao.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import wyl.hikers.model.Hotel;
import wyl.hikers.model.Hotel2;

import java.util.List;

@Mapper
public interface MpHotel {

    @Insert("INSERT INTO hotels(sid, hname, address, info, rooms) " +
            "VALUES(#{sid}, #{hname}, #{address}, #{info}, #{roomsjson});")
    Integer addHotel(Hotel hotel);

    @Select("select * from hotels;")
    List<Hotel2> getHotels();

    @Update("update hotels set sid=#{sid}, hname=#{hname},address=#{address},info=#{info},rooms=#{roomsjson} where hid=#{hid};")
    Integer updateHotel(Hotel hotel);

    @Select("select * from hotels where sid=#{sid}")
    List<Hotel2> getHotelsBySid(Integer sid);

    @Select("select * from hotels where hid=#{hid}")
    Hotel2 getHotelByHid(Integer hid);
}
