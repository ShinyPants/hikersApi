package wyl.hikers.service;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wyl.hikers.config.SysConfig;
import wyl.hikers.dao.mapper.MpTopic;
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

    public RespBody getTopics(Integer pid) {
        List<TopicInfo> list = mysql.getTopics(pid, config.getTopicLoadNum());
        return RespBody.ok(list);
    }

    public RespBody getTopics(Integer pid, Integer tid) {
        List<TopicInfo> list = mysql.getTopicsBottom(pid, tid, config.getTopicLoadNum());
        return RespBody.ok(list);
    }
}
