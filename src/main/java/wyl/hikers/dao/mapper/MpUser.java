package wyl.hikers.dao.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.scheduling.annotation.Async;
import wyl.hikers.model.User;

import java.util.List;
import java.util.Set;

@Mapper
public interface MpUser {
    @Insert({"<script>" +
            "INSERT INTO users(phone, pwd, question, answer, nikeName, uname, birthday <if test='mail!=null and mail.length>0'>,mail</if>)" +
            "values(#{phone},#{pwd},#{question},#{answer},#{nikeName},#{uname},#{birthday} <if test='mail!=null and mail.length>0'>,#{mail}</if>)" +
            "</script>"})
    Integer register(User user);

    @Select("SELECT uid, phone, nikeName, uname, mail, birthday, photo " +
            "FROM users " +
            "WHERE phone=#{phone} AND pwd=#{pwd} LIMIT 1")
    User loginByPhone(User user);

    @Select("SELECT uid, phone, nikeName, uname, mail, birthday, photo " +
            "FROM users " +
            "WHERE mail=#{mail} AND pwd=#{pwd} LIMIT 1")
    User loginByMail(User user);

    @Select("SELECT COUNT(uid) FROM users WHERE phone=#{0}")
    @ResultType(Integer.class)
    Integer checkPhone(String number);

    @Select("SELECT COUNT(uid) FROM users WHERE nikeName=#{0}")
    @ResultType(Integer.class)
    Integer checkNike(String nike);

    @Select("SELECT COUNT(uid) FROM users WHERE mail=#{0}")
    @ResultType(Integer.class)
    Integer checkMail(String mail);

    @Async("asyncService")
    @Update("UPDATE users SET focus = focus + #{num} WHERE uid = #{uid};")
    Integer updateFocus(Integer uid, Integer num);

    @Async("asyncService")
    @Update("UPDATE users SET fans = fans + #{num} WHERE uid = #{uid};")
    Integer updateFans(Integer uid, Integer num);

    @Select("SELECT uid, nikeName, photo, focus, fans FROM users WHERE uid = #{uid} LIMIT 1")
    User getUser(Integer uid);

    @Select("SELECT uid, phone, nikeName, mail, photo FROM users WHERE uid = #{uid} LIMIT 1")
    User getUserInfo(Integer uid);

    @Select({"<script>" +
            "SELECT uid, nikeName, photo, focus, fans FROM users WHERE uid in " +
            "<foreach collection='list' item='uid' open='(' close=')' separator=', '>" +
            "#{uid}" +
            "</foreach>" +
            "</script>"})
    List<User> getUsers(Set<String> list);

    @Update("UPDATE users SET phone = #{phone} WHERE uid = #{uid};")
    Integer updatePhone(Integer uid, String phone);

    @Update("UPDATE users SET mail = #{mail} WHERE uid = #{uid};")
    Integer updateMail(Integer uid, String mail);

    @Update("UPDATE users SET photo = #{photo} WHERE uid = #{uid};")
    Integer updatePhoto(Integer uid, String photo);
}
