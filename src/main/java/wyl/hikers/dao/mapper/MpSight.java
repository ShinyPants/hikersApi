package wyl.hikers.dao.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import wyl.hikers.model.Sight;
import wyl.hikers.model.Sight2;

import java.util.List;

@Mapper
public interface MpSight {
    @Insert("insert into sight(sname, info, address, pics) values(#{sname}, #{info}, #{address}, #{picsJSON})")
    Integer addSight(Sight sight);

    @Select("select * from sight")
    List<Sight2> getSights();

    @Update("update sight set sname=#{sname}, info=#{info} , address=#{address} where sid=#{sid}")
    Integer editSight(Sight sight);
}
