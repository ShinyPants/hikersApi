package wyl.hikers.dao.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import wyl.hikers.model.Topic;

@Mapper
public interface MpTopic {
    @Insert("<script>" +
            "INSERT INTO topics(uid, title, info, pics) " +
            "VALUES(#{uid}, #{title}, #{info}, #{picsJSON})" +
            "</script>")
    Integer addTopic(Topic topic);
}
