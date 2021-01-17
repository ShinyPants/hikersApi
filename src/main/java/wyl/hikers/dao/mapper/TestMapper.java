package wyl.hikers.dao.mapper;

import org.apache.ibatis.annotations.*;
import wyl.hikers.model.Test;

@Mapper
public interface TestMapper {

    @ResultType(Test.class)
    @Select("select * from test limit 1")
    public Test getOne();

    @Insert("insert into test(str) values(#{test.str})")
    public Integer addOne(@Param("test") Test obj);
}
