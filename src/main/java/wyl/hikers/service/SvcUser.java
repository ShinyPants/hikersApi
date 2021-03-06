package wyl.hikers.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wyl.hikers.dao.redis.RdsFocus;
import wyl.hikers.dao.redis.RdsUser;
import wyl.hikers.exception.MysqlException;
import wyl.hikers.model.RespBody;
import wyl.hikers.model.User;
import wyl.hikers.dao.mapper.MpUser;

import java.util.UUID;

@Service
@Slf4j
public class SvcUser {
    @Autowired
    private MpUser mysql;

    @Autowired
    private RdsUser redis;

    @Autowired
    private RdsFocus redisFocus;

    @Autowired
    private SvcPermission permission;

    /**
     * 注册
     * @param user uid字段在这里没用，mail属性可选
     * @return
     */
    public RespBody register(User user) {
        // 首先检查
        if (!(boolean) checkPhone(user.getPhone()).getData())
            return RespBody.failed("手机号码重复");
        if (!(boolean) checkNike(user.getNikeName()).getData())
            return RespBody.failed("昵称重复");
        if (!user.getMail().equals("") && !(boolean) checkMail(user.getMail()).getData())
            return RespBody.failed("邮箱重复");
        Integer res = -1;
        try {
            res = mysql.register(user);
        } catch (Exception e) {
            throw MysqlException.insert("SvcUser.register", user);
        }
        if (res > 0)
            return RespBody.ok(true);
        else
            return RespBody.ok(false);
    }

    /**
     * 登录
     * @param user
     * @return
     */
    public RespBody login(User user) {
        User res = null;
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
        redis.addOnline(res);
        return RespBody.ok(res);
    }

    /**
     * 验证手机号
     * @param number
     * @return
     */
    public RespBody checkPhone(String number) {
        Integer res = -1;
        try {
            res = mysql.checkPhone(number);
        } catch (Exception e) {
            throw MysqlException.select("SvcUser.checkPhone", number);
        }
        if (res > 0)
            return RespBody.ok(false);
        return RespBody.ok(true);
    }

    /**
     * 验证昵称
     * @param nike
     * @return
     */
    public RespBody checkNike(String nike) {
        Integer res = -1;
        try {
            res = mysql.checkNike(nike);
        } catch (Exception e) {
            throw MysqlException.select("SvcUser.checkNike", nike);
        }
        if (res > 0)
            return RespBody.ok(false);
        return RespBody.ok(true);
    }

    /**
     * 验证邮箱
     * @param mail
     * @return
     */
    public RespBody checkMail(String mail) {
        Integer res = -1;
        try {
            res = mysql.checkMail(mail);
        } catch (Exception e) {
            throw MysqlException.select("SvcUser.checkMail", mail);
        }
        if (res > 0)
            return RespBody.ok(false);
        return RespBody.ok(true);
    }

    /**
     * 检查是否关注了
     * @return
     */
    public RespBody isFocus(Integer uid, Integer tuid) {
        return RespBody.ok(redisFocus.isFocus(uid, tuid));
    }

    /**
     * 添加关注
     * @return
     */
    public RespBody addFocus(Integer uid, Integer tuid, String pwd) {
        permission.checkPermission(uid, pwd, "SvcUser.addFocus");
        redisFocus.addFocus(uid, tuid);
        mysql.updateFocus(uid, 1);
        mysql.updateFans(tuid, 1);
        return RespBody.ok(null);
    }

    /**
     * 不再关注
     * @return
     */
    public RespBody delFocus(Integer uid, Integer tuid, String pwd) {
        permission.checkPermission(uid, pwd, "SvcUser.delFocus");
        redisFocus.delFocus(uid, tuid);
        mysql.updateFocus(uid, -1);
        mysql.updateFans(tuid, -1);
        return RespBody.ok(null);
    }

    public RespBody getUser(Integer uid) {
        User user = mysql.getUser(uid);
        return RespBody.ok(user);
    }

    public RespBody getUserInfo(Integer uid) {
        User user = mysql.getUserInfo(uid);
        return RespBody.ok(user);
    }

    public RespBody updatePhone(Integer uid, String pwd, String phone) {
        permission.checkPermission(uid, pwd, "SvcUser.updatePhone");
        if (mysql.checkPhone(phone) > 0)
            return RespBody.failed(null);
        mysql.updatePhone(uid, phone);
        return RespBody.ok(null);
    }

    public RespBody updateMail(Integer uid, String pwd, String mail) {
        permission.checkPermission(uid, pwd, "SvcUser.updateMail");
        if (mysql.checkMail(mail) > 0)
            return RespBody.failed(null);
        mysql.updateMail(uid, mail);
        return RespBody.ok(null);
    }

    public RespBody updatePhoto(Integer uid, String pwd, String photo) {
        permission.checkPermission(uid, pwd, "SvcUser.updatePhoto");
        mysql.updatePhoto(uid, photo);
        return RespBody.ok(null);
    }
}
