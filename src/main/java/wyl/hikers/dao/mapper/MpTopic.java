package wyl.hikers.dao.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.scheduling.annotation.Async;
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
            "SELECT * FROM v_topic_info WHERE tid = #{tid} LIMIT 1" +
            "</script>"})
    TopicInfo getOneTopic(Integer tid);

    @Select({"<script>" +
            "SELECT * FROM v_topic_info WHERE pid = #{pid} AND tid > #{tid} ORDER BY tid DESC LIMIT #{num}" +
            "</script>"})
    List<TopicInfo> getTopicsBottom(Integer pid, Integer tid, Integer num);

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
}
