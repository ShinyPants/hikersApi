package wyl.hikers.dao.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import wyl.hikers.model.Student;

@Repository
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;

    public void set(Student student) {
        redisTemplate.opsForValue().set("student", student);
    }

    public Student get(String key) {
        return (Student) redisTemplate.opsForValue().get(key);
    }
}
