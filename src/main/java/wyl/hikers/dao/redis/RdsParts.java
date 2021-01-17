package wyl.hikers.dao.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;
import wyl.hikers.model.Part;
import wyl.hikers.dao.mapper.MpParts;

import java.util.List;

@Repository
@Slf4j
public class RdsParts {
    @Autowired
    private RedisTemplate redis;

    @Autowired
    private MpParts mysql;

    private final String KEY = "parts";

    /**
     * 获取分区json表
     */
    public List<Part> getParts() {
        ValueOperations<String, Part> ops = redis.opsForValue();
        List<Part> parts = (List<Part>) ops.get(KEY);
        return parts;
    }

    /**
     * 从MySQL更新parts数据
     * @return
     */
    public void refresh(List<Part> parts) {
        ValueOperations<String, List<Part>> ops = redis.opsForValue();
        ops.set(KEY, parts);
    }
}
