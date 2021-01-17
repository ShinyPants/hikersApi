package wyl.hikers.dao.mapper;

import org.apache.ibatis.annotations.*;
import wyl.hikers.model.Part;

import java.util.List;

@Mapper
public interface MpParts {
    @Insert({"<script>" +
            "insert into parts(picUrl, name<if test='part.score!=null'>, score</if>) " +
            "values(#{part.picUrl}, #{part.name}<if test='part.score!=null'>, #{part.score}</if>)</script>"})
    Integer addPart(@Param("part") Part part);

    @Select("select id, picUrl, name, score from parts")
    @ResultType(Part.class)
    List<Part> getParts();


    @Update({"<script>" +
            "<foreach collection='parts' item='part' separator=';'>" +
            "update parts" +
            "<set>" +
            "<if test='part.picUrl!=null'>picUrl=#{part.picUrl},</if>" +
            "<if test='part.name!=null'>name=#{part.name},</if>" +
            "<if test='part.score!=null'>score=#{part.score},</if>" +
            "</set>" +
            "where id=#{part.id}" +
            "</foreach>" +
            "</script>"})
    Integer updateParts(@Param("parts") List<Part> parts);
}
