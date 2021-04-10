package wyl.hikers.dao.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import wyl.hikers.model.Report;
import wyl.hikers.model.ReportList;

import java.util.List;

@Mapper
public interface MpReport {

    @Insert("INSERT INTO reports(tid, uid, reason, info) VALUES(#{tid}, #{uid}, #{reason}, #{info});")
    Integer addReport(Report report);

    @Select("SELECT tid, COUNT(*) AS num, uid, uname FROM v_report GROUP BY tid;")
    List<ReportList> getList();

    @Delete("DELETE FROM reports WHERE tid=#{tid};")
    void delReports(Integer tid);
}
