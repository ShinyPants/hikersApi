package wyl.hikers.repository.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import wyl.hikers.model.User;

@Mapper
public interface MpUser {
    @Insert({"<script>" +
            "INSERT INTO users(phone, pwd, question, answer, nikeName, uname, birthday <if test='mail!=null and mail.length>0'>,mail</if>)" +
            "values(#{phone},#{pwd},#{question},#{answer},#{nikeName},#{uname},#{birthday} <if test='mail!=null and mail.length>0'>,#{mail}</if>)" +
            "</script>"})
    public Integer register(User user);

    @Select("SELECT COUNT(uid) FROM users WHERE phone=#{0}")
    @ResultType(Integer.class)
    public Integer checkPhone(String number);

    @Select("SELECT COUNT(uid) FROM users WHERE nikeName=#{0}")
    @ResultType(Integer.class)
    public Integer checkNike(String nike);

    @Select("SELECT COUNT(uid) FROM users WHERE mail=#{0}")
    @ResultType(Integer.class)
    public Integer checkMail(String mail);
}
