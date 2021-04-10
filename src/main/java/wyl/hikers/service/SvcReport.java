package wyl.hikers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wyl.hikers.dao.mapper.MpReport;
import wyl.hikers.model.Report;
import wyl.hikers.model.ReportList;
import wyl.hikers.model.RespBody;

import java.util.List;

@Service
public class SvcReport {
    @Autowired
    private MpReport mysql;

    public RespBody addReport(Report report) {
        mysql.addReport(report);
        return null;
    }

    public RespBody getList() {
        List<ReportList> list = mysql.getList();
        return RespBody.ok(list);
    }

    public RespBody delReports(Integer tid) {
        mysql.delReports(tid);
        return null;
    }
}
