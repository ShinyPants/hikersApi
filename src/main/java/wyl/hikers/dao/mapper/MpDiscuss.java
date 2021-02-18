package wyl.hikers.dao.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import wyl.hikers.model.DiscussInfo;

import java.util.List;

@Mapper
public interface MpDiscuss {
    @Insert({"<script>" +
            "INSERT INTO discuss(uid, tid, info) VALUES(#{uid},#{tid},#{info})" +
            "</script>"})
    Integer addDiscuss(DiscussInfo info);

    @Select({"<script>" +
            "SELECT * FROM v_discuss_info WHERE tid=#{tid} AND did > #{did} ORDER BY did DESC LIMIT #{num};" +
            "</script>"})
    List<DiscussInfo> getDiscuss(Integer tid, Integer did, Integer num);
}
