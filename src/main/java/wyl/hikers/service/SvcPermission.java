package wyl.hikers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wyl.hikers.dao.redis.RdsUser;
import wyl.hikers.exception.PermissionException;
import wyl.hikers.model.OnlineUser;

@Service
public class SvcPermission {
    @Autowired
    private RdsUser redis;

    public void checkPermission(Integer uid, String pwd, String position) {
        OnlineUser user = redis.getOnline(uid);
        // 不存在
        if (user == null)
            throw PermissionException.notOnline(position, uid, pwd);
        Long now = System.currentTimeMillis();
        // 口令不对
        if (!pwd.equals(user.getPwd()))
            throw PermissionException.codeError(position, uid, pwd);
        // 超时
        if (user.getTime() + user.getTimeOut() < now) {
            redis.deleteOnline(uid); // 删除过期的
            throw PermissionException.timeOut(position, uid, pwd);
        }
        user.setTime(now);
        redis.updateOnline(uid, user);
    }
}
