package wyl.hikers.service;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wyl.hikers.config.SysConfig;
import wyl.hikers.dao.mapper.MpDiscuss;
import wyl.hikers.dao.mapper.MpTopic;
import wyl.hikers.dao.redis.RdsTopic;
import wyl.hikers.exception.MysqlException;
import wyl.hikers.model.RespBody;
import wyl.hikers.model.Topic;
import wyl.hikers.model.TopicInfo;

import java.util.List;

@Slf4j
@Service
public class SvcTopic {
    @Autowired
    private SvcPermission permission;

    @Autowired
    private MpTopic mysql;

    @Autowired
    private MpDiscuss mysqlDiscuss;

    @Autowired
    private RdsTopic redis;

    @Autowired
    private SysConfig config;

    public RespBody addTopic(Topic topic) {
        // 检查身份
        permission.checkPermission(topic.getUid(), topic.getPwd(), "SvcTopic.addTopic");
        // pics转JSON
        if ((topic.getPics() != null ? topic.getPics().length : 0) > 0)
            topic.setPicsJSON(JSON.toJSONString(topic.getPics()));
        // 保存
        Integer res;
        try {
            res = mysql.addTopic(topic);
        } catch (Exception e) {
            throw MysqlException.insert("SvcTopic.addTopic", topic);
        }
        if (res > 0)
            return RespBody.ok(true);
        else
            return RespBody.failed(false);
    }

    public  RespBody getOneTopic(Integer tid) {
        TopicInfo res = mysql.getOneTopic(tid);
        return RespBody.ok(res);
    }

    public RespBody getTopics(Integer pid, Integer tid) {
        if (tid == 0) {
            tid = Integer.MAX_VALUE;
        }
        List<TopicInfo> list = mysql.getTopics(pid, tid, config.getTopicLoadNum());
        return RespBody.ok(list);
    }

    public RespBody getTopicsByUser(Integer uid, Integer tid) {
        if (tid == 0) {
            tid = Integer.MAX_VALUE;
        }
        List<TopicInfo> list = mysql.getTopicsByUser(uid, tid, config.getTopicLoadNum());
        return RespBody.ok(list);
    }

    public RespBody delTopic(Integer tid, Integer uid, String pwd) {
        permission.checkPermission(uid, pwd, "SvcTopic.delTopic");
        // 先删除评论
        mysqlDiscuss.delDiscuss(tid);
        // 再删除帖子
        mysql.delTopic(tid);
        return RespBody.ok(null);
    }

    public RespBody delTopicByAdm(Integer tid) {
        // 先删除评论
        mysqlDiscuss.delDiscuss(tid);
        // 再删除帖子
        mysql.delTopic(tid);
        return RespBody.ok(null);
    }



    public RespBody isCollect(Integer uid, Integer tid) {
        boolean flag = redis.isCollect(uid, tid);
        return RespBody.ok(flag);
    }

    public RespBody addCollect(Integer uid, Integer tid, String pwd) {
        permission.checkPermission(uid, pwd, "SvcTopic.addCollect");
        redis.addCollect(uid, tid);
        mysql.addCollect(tid);
        mysql.addHotpoint(tid, 5);
        return RespBody.ok(null);
    }

    public RespBody delCollect(Integer uid, Integer tid, String pwd) {
        permission.checkPermission(uid, pwd, "SvcTopic.addCollect");
        redis.delCollect(uid, tid);
        mysql.delCollect(tid);
        mysql.delHotpoint(tid, 5);
        return RespBody.ok(null);
    }

    public RespBody isAgree(Integer uid, Integer tid) {
        boolean flag = redis.isAgree(uid, tid);
        return RespBody.ok(flag);
    }

    public RespBody addAgree(Integer uid, Integer tid, String pwd) {
        permission.checkPermission(uid, pwd, "SvcTopic.addCollect");
        redis.addAgree(uid, tid);
        mysql.addAgree(tid);
        mysql.addHotpoint(tid, 3);
        return RespBody.ok(null);
    }

    public RespBody delAgree(Integer uid, Integer tid, String pwd) {
        permission.checkPermission(uid, pwd, "SvcTopic.addCollect");
        redis.delAgree(uid, tid);
        mysql.delAgree(tid);
        mysql.delHotpoint(tid, 3);
        return RespBody.ok(null);
    }

    public RespBody searchTopics(Integer pid, String keyWord) {
        List<TopicInfo> list = mysql.searchTopics(pid, keyWord);
        return RespBody.ok(list);
    }

    public RespBody getByHp() {
        List<TopicInfo> list = mysql.getByHp();
        return RespBody.ok(list);
    }
}
