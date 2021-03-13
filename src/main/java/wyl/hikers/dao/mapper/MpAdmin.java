package wyl.hikers.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import wyl.hikers.model.Admin;

@Mapper
public interface MpAdmin {
    // TODO 实现
    @Select("SELECT * FROM v_adm WHERE phone = #{phone} AND pwd = #{pwd};")
    Admin loginByPhone(Admin user);

    // TODO 实现
    @Select("SELECT * FROM v_adm WHERE mail = #{mail} AND pwd = #{pwd};")
    Admin loginByMail(Admin user);
}
