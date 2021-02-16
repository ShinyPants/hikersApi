package wyl.hikers.dao.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

@Repository
public class RdsTopic {
    @Autowired
    private RedisTemplate redis;

    private final String KEY_COLLECT = "TOP_COLLECT";

    private final String KEY_AGREE = "TOP_AGREE";

    public boolean isCollect(Integer uid, Integer tid) {
        String value = uid.toString() + ":" + tid.toString();
        SetOperations<String, String> ops = redis.opsForSet();
        return ops.isMember(KEY_COLLECT, value);
    }

    @Async("asyncService")
    public void addCollect(Integer uid, Integer tid) {
        String value = uid.toString() + ":" + tid.toString();
        SetOperations<String, String> ops = redis.opsForSet();
        ops.add(KEY_COLLECT, value);
    }

    @Async("asyncService")
    public void delCollect(Integer uid, Integer tid) {
        String value = uid.toString() + ":" + tid.toString();
        SetOperations<String, String> ops = redis.opsForSet();
        ops.remove(KEY_COLLECT, value);
    }

    public boolean isAgree(Integer uid, Integer tid) {
        String value = uid.toString() + ":" + tid.toString();
        SetOperations<String, String> ops = redis.opsForSet();
        return ops.isMember(KEY_AGREE, value);
    }

    @Async("asyncService")
    public void addAgree(Integer uid, Integer tid) {
        String value = uid.toString() + ":" + tid.toString();
        SetOperations<String, String> ops = redis.opsForSet();
        ops.add(KEY_AGREE, value);
    }

    @Async("asyncService")
    public void delAgree(Integer uid, Integer tid) {
        String value = uid.toString() + ":" + tid.toString();
        SetOperations<String, String> ops = redis.opsForSet();
        ops.remove(KEY_AGREE, value);
    }
}
