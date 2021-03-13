package wyl.hikers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wyl.hikers.dao.mapper.MpAdmin;
import wyl.hikers.dao.redis.RdsAdmin;
import wyl.hikers.dao.redis.RdsUser;
import wyl.hikers.model.Admin;
import wyl.hikers.model.RespBody;

import java.util.UUID;

@Service
public class SvcAdmin {
    @Autowired
    private MpAdmin mysql;

    @Autowired
    private RdsUser userRedis;

    // 注入管理员redis
    @Autowired
    private RdsAdmin adminRedis;

    public RespBody login(Admin user) {
        Admin res = null;
        // 判断登录方式
        if (user.getPhone() != null && user.getPhone().length() > 0)
            res = mysql.loginByPhone(user);
        else if (user.getMail() != null && user.getMail().length() > 0)
            res = mysql.loginByMail(user);
        // 检查登录结果
        if (res == null)
            return RespBody.failed(null);
        // 生成口令
        String pwd = UUID.randomUUID().toString().replaceAll("-", "");
        res.setPwd(pwd);
        // 保存到Redis
        userRedis.addOnline(res);
        // 保存到管理员的在线
        adminRedis.addOnline(res);
        return RespBody.ok(res);
    }
}
