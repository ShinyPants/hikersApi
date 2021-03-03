package wyl.hikers.dao.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import wyl.hikers.model.Message;
import wyl.hikers.model.MessageShot;

import java.util.List;

@Mapper
public interface MpMsg {

    @Insert("INSERT INTO message(fuid, tuid, time, info) VALUES(#{fuid}, #{tuid}, NOW(), #{info});")
    Integer addMsg(Message msg);

    @Select("SELECT * FROM message WHERE (fuid = #{fuid} AND tuid = #{tuid}) OR (tuid = #{fuid} AND fuid = #{tuid});")
    List<Message> getTalkingMsg(Integer fuid, Integer tuid);

    @Select("SELECT * FROM (SELECT * FROM v_message_user WHERE fuid=#{uid} OR tuid=#{uid} ORDER BY `mid` DESC LIMIT 1000) tb GROUP BY fuid, tuid;")
    List<MessageShot> getTalkList(Integer uid);
}
