package wyl.hikers.dao.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import wyl.hikers.model.Admin;
import wyl.hikers.model.OnlineUser;

@Repository
public class RdsAdmin {
    @Autowired
    private RedisTemplate redis;

    private final String KEY = "admOnline";

    private final Integer TIME_OUT = 30 * 1000 * 60;

    // TODO 实现并设置为多线程方法
    @Async("asyncService")
    public void addOnline(Admin user) {
        HashOperations<String, String, OnlineUser> ops = redis.opsForHash();
        // 设置在线信息
        OnlineUser online = new OnlineUser();
        online.setPwd(user.getPwd()); // 口令
        online.setTime(System.currentTimeMillis()); // 登入时间
        online.setTimeOut(TIME_OUT); // 过期时间
        // 添加到数据库
        ops.put(KEY, user.getAdmid().toString(), online);
    }
}
