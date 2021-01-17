package wyl.hikers.dao.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import wyl.hikers.model.User;

@Repository
public class RdsUser {
    @Autowired
    private RedisTemplate redis;

    private final String KEY = "online";

    /**
     * 添加一名在线用户
     * @param user
     */
    @Async("asyncService")
    public void addOnline(User user) {
        // 三个键分别是：KEY, uid, pwd
        HashOperations<String, String, String> ops =  redis.opsForHash();
        ops.put(KEY, user.getUid().toString(), user.getPwd());
    }

    /**
     * 判断用户当前是否在线
     * @param user
     * @return
     */
    public boolean isOnline(User user) {
        HashOperations<String, String, String> ops =  redis.opsForHash();
        return ops.hasKey(KEY, user.getUid().toString());
    }
}
