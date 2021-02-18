package wyl.hikers.dao.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

@Repository
public class RdsFocus {
    @Autowired
    private RedisTemplate redis;

    private final String KEY = "USER_FOCUS";

    public boolean isFocus(Integer uid, Integer tuid) {
        SetOperations<String, String> ops = redis.opsForSet();
        String value = uid.toString() + ":" + tuid.toString();
        return ops.isMember(KEY, value);
    }

    @Async("asyncService")
    public void addFocus(Integer uid, Integer tuid) {
        SetOperations<String, String> ops = redis.opsForSet();
        String value = uid.toString() + ":" + tuid.toString();
        ops.add(KEY, value);
    }

    @Async("asyncService")
    public void delFocus(Integer uid, Integer tuid) {
        SetOperations<String, String> ops = redis.opsForSet();
        String value = uid.toString() + ":" + tuid.toString();
        ops.remove(KEY, value);
    }
}
