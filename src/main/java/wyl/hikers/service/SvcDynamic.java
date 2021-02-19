package wyl.hikers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wyl.hikers.config.SysConfig;
import wyl.hikers.dao.mapper.MpTopic;
import wyl.hikers.dao.redis.RdsFocus;
import wyl.hikers.model.RespBody;
import wyl.hikers.model.TopicInfo;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
public class SvcDynamic {
    @Autowired
    private RdsFocus redisFocus;

    @Autowired
    private MpTopic mysql;

    @Autowired
    private SysConfig config;

    public RespBody getDynamics(Integer uid, Integer tid) {
        // 获取关注列表
        Set<String> focusList = redisFocus.getFocus(uid);
        // 获取动态列表
        List<TopicInfo> list = new LinkedList<>();
        if (focusList.size() > 0)
            list = mysql.getTopicsByFocus(focusList, tid, config.getTopicLoadNum());
        return RespBody.ok(list);
    }
}
