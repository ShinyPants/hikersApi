package wyl.hikers.dao.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.scheduling.annotation.Async;
import wyl.hikers.model.Topic;
import wyl.hikers.model.TopicInfo;

import java.util.List;
import java.util.Set;

@Mapper
public interface MpTopic {
    @Insert({"<script>" +
            "INSERT INTO topics(pid, uid, title, info, pics) " +
            "VALUES(#{t.pid}, #{t.uid}, #{t.title}, #{t.info}, #{t.picsJSON})" +
            "</script>"})
    Integer addTopic(@Param("t") Topic topic);

    @Select({"<script>" +
            "SELECT * FROM v_topic_info WHERE tid = #{tid} LIMIT 1" +
            "</script>"})
    TopicInfo getOneTopic(Integer tid);

    @Select({"<script>" +
            "SELECT * FROM v_topic_info WHERE pid = #{pid} AND tid > #{tid} ORDER BY tid DESC LIMIT #{num}" +
            "</script>"})
    List<TopicInfo> getTopics(Integer pid, Integer tid, Integer num);

    @Select("SELECT * FROM v_topic_info WHERE uid = #{uid} AND tid > #{tid} ORDER BY tid DESC LIMIT #{num}")
    List<TopicInfo> getTopicsByUser(Integer uid, Integer tid, Integer num);

    @Select({"<script>" +
            "SELECT * FROM v_topic_info WHERE uid in " +
            "<foreach collection='list' item='uid' open='(' close=')' separator=', '>" +
            "#{uid}" +
            "</foreach>" +
            " AND tid > #{tid} ORDER BY tid DESC LIMIT #{num}" +
            "</script>"})
    List<TopicInfo> getTopicsByFocus(Set<String> list, Integer tid, Integer num);

    @Delete("DELETE FROM topics WHERE tid = #{tid}")
    void delTopic(Integer tid);

    @Async("asyncService")
    @Update({"<script>" +
            "UPDATE topics SET collect = collect + 1 WHERE tid = #{tid};" +
            "</script>"})
    void addCollect(Integer tid);

    @Async("asyncService")
    @Update({"<script>" +
            "UPDATE topics SET collect = collect - 1 WHERE tid = #{tid};" +
            "</script>"})
    void delCollect(Integer tid);

    @Async("asyncService")
    @Update({"<script>" +
            "UPDATE topics SET agree = agree + 1 WHERE tid = #{tid};" +
            "</script>"})
    void addAgree(Integer tid);

    @Async("asyncService")
    @Update({"<script>" +
            "UPDATE topics SET agree = agree - 1 WHERE tid = #{tid};" +
            "</script>"})
    void delAgree(Integer tid);

    @Update("UPDATE topics SET discuss = discuss + 1 WHERE tid = #{tid};")
    void updateDiscuss(Integer tid);
}
