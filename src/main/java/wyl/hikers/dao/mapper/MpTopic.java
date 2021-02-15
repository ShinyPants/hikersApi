package wyl.hikers.dao.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import wyl.hikers.model.Topic;
import wyl.hikers.model.TopicInfo;

import java.util.List;

@Mapper
public interface MpTopic {
    @Insert({"<script>" +
            "INSERT INTO topics(pid, uid, title, info, pics) " +
            "VALUES(#{t.pid}, #{t.uid}, #{t.title}, #{t.info}, #{t.picsJSON})" +
            "</script>"})
    Integer addTopic(@Param("t") Topic topic);

    @Select({"<script>" +
            "SELECT * FROM v_topic_info WHERE pid = #{pid} ORDER BY tid DESC LIMIT #{num}" +
            "</script>"})
    List<TopicInfo> getTopics(Integer pid, Integer num);

    @Select({"<script>" +
            "SELECT * FROM v_topic_info WHERE pid = #{pid} AND tid > #{tid} ORDER BY tid DESC LIMIT #{num}" +
            "</script>"})
    List<TopicInfo> getTopicsBottom(Integer pid, Integer tid, Integer num);
}
