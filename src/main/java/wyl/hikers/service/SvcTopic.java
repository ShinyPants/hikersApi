package wyl.hikers.service;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wyl.hikers.dao.mapper.MpTopic;
import wyl.hikers.exception.MysqlException;
import wyl.hikers.model.RespBody;
import wyl.hikers.model.Topic;

@Service
public class SvcTopic {
    @Autowired
    private SvcPermission permission;

    @Autowired
    private MpTopic mysql;

    public RespBody addTopic(Topic topic) {
        // 检查身份
        permission.checkPermission(topic.getUid(), topic.getPwd(), "SvcTopic.addTopic");
        // pics转JSON
        if (topic.getPics() == null || topic.getPics().length == 0)
            topic.setPicsJSON(JSON.toJSONString(topic.getPics()));
        // 保存
        Integer res = -1;
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
}
