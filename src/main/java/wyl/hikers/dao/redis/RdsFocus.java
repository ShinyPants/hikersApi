package wyl.hikers.dao.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class RdsFocus {
    @Autowired
    private RedisTemplate redis;

    private final String KEY = "USER_FOCUS";

    private final String PRE = "FOCUS_";

    public boolean isFocus(Integer uid, Integer tuid) {
        SetOperations<String, String> ops = redis.opsForSet();
        String value = uid.toString() + ":" + tuid.toString();
        return ops.isMember(KEY, value);
    }

    @Async("asyncService")
    public void addFocus(Integer uid, Integer tuid) {
        SetOperations<String, String> ops = redis.opsForSet();
        String value = uid.toString() + ":" + tuid.toString();
        // 在总表中添加关注关系
        ops.add(KEY, value);
        // 在个人表中添加关注对象
        ops.add(PRE+uid.toString(), tuid.toString());
    }

    @Async("asyncService")
    public void delFocus(Integer uid, Integer tuid) {
        SetOperations<String, String> ops = redis.opsForSet();
        String value = uid.toString() + ":" + tuid.toString();
        // 在总表中删除关注关系
        ops.remove(KEY, value);
        // 在个人表中删除关注对象
        ops.remove(PRE+uid.toString(), tuid.toString());
    }

    /**
     * 获取用户的关注列表
     * @param uid
     */
    public Set<String> getFocus(Integer uid) {
        SetOperations<String, String> ops = redis.opsForSet();
        String key = PRE + uid.toString();
//        return ops.distinctRandomMembers(key, 20);
        return ops.members(key);
    }
}
