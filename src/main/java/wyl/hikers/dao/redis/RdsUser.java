package wyl.hikers.dao.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import wyl.hikers.model.OnlineUser;
import wyl.hikers.model.User;

@Slf4j
@Repository
public class RdsUser {
    @Autowired
    private RedisTemplate redis;

    private final String KEY = "online";

    private final Integer TIME_OUT = 30 * 1000 * 60;

    /**
     * 添加一名在线用户
     * @param user
     */
    @Async("asyncService")
    public void addOnline(User user) {
        HashOperations<String, String, OnlineUser> ops = redis.opsForHash();
        // 设置在线信息
        OnlineUser online = new OnlineUser();
        online.setPwd(user.getPwd()); // 口令
        online.setTime(System.currentTimeMillis()); // 登入时间
        online.setTimeOut(TIME_OUT); // 过期时间
        // 添加到数据库
        ops.put(KEY, user.getUid().toString(), online);
    }

    /**
     * 判断用户当前是否在线
     * @param uid 用户id
     * @return
     */
//    public boolean isOnline(Integer uid) {
//        HashOperations<String, String, OnlineUser> ops = redis.opsForHash();
//        OnlineUser user = ops.get(KEY, uid.toString());
//        Boolean flag = user == null;
//        log.info(flag.toString());
//        return true;
//    }

    /**
     * 判断用户当前是否在线
     * @param user 需要指定uid
     * @return
     */
//    public boolean isOnline(User user) {
//        return isOnline(user.getUid());
//    }

    @Async("asyncService")
    public void updateOnline(Integer uid, OnlineUser user) {
        HashOperations<String, String, OnlineUser> ops = redis.opsForHash();
        ops.put(KEY, uid.toString(), user);
    }

    public void deleteOnline(Integer uid) {
        HashOperations<String, String, OnlineUser> ops = redis.opsForHash();
        ops.delete(KEY, uid.toString());
    }

    public OnlineUser getOnline(Integer uid) {
        HashOperations<String, String, OnlineUser> ops = redis.opsForHash();
        return ops.get(KEY, uid.toString());
    }
}
