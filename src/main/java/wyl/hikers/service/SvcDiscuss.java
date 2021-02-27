package wyl.hikers.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wyl.hikers.config.SysConfig;
import wyl.hikers.dao.mapper.MpDiscuss;
import wyl.hikers.dao.mapper.MpTopic;
import wyl.hikers.model.DiscussInfo;
import wyl.hikers.model.RespBody;

import java.util.List;

@Slf4j
@Service
public class SvcDiscuss {
    @Autowired
    private MpDiscuss mysql;

    @Autowired
    private MpTopic mysqlTopic;

    @Autowired
    private SvcPermission permission;

    @Autowired
    private SysConfig config;

    public RespBody addDiscuss(DiscussInfo info) {
        permission.checkPermission(info.getUid(), info.getPwd(), "SvcDiscuss.addDiscuss");
        if (mysql.addDiscuss(info) > 0) {
            mysqlTopic.updateDiscuss(info.getTid());
            return RespBody.ok(null);
        }
        return RespBody.failed(null);
    }

    public RespBody getDiscusses(Integer tid, Integer did) {
        if (did == 0) {
            did = Integer.MAX_VALUE;
        }
        List<DiscussInfo> list = mysql.getDiscuss(tid, did, config.getTopicLoadNum());
        return RespBody.ok(list);
    }
}
