package wyl.hikers.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wyl.hikers.model.Report;
import wyl.hikers.model.RespBody;
import wyl.hikers.service.SvcReport;

@RestController
@RequestMapping("/api/report")
public class UsrReport {
    @Autowired
    private SvcReport service;

    @PostMapping
    public RespBody addReport(@RequestBody Report report) {
        return service.addReport(report);
    }
}
