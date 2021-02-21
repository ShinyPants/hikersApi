package wyl.hikers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wyl.hikers.dao.mapper.MpUser;
import wyl.hikers.dao.redis.RdsFocus;
import wyl.hikers.model.RespBody;
import wyl.hikers.model.User;

import java.util.List;
import java.util.Set;

@Service
public class SvcFocus {

    @Autowired
    private RdsFocus redis;

    @Autowired
    private MpUser mysqlUser;

    public RespBody getFocuses(Integer uid) {
        Set<String> focus = redis.getFocus(uid);
        if (focus.size() == 0) {
            return RespBody.ok(null);
        }
        List<User> list = mysqlUser.getUsers(focus);
        return RespBody.ok(list);
    }
}
