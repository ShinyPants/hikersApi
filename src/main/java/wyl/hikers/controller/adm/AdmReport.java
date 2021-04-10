package wyl.hikers.controller.adm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wyl.hikers.model.RespBody;
import wyl.hikers.service.SvcReport;

@RestController
@RequestMapping("/adm/report")
public class AdmReport {
    @Autowired
    private SvcReport service;

    @GetMapping("/list")
    public RespBody getList() {
        return service.getList();
    }

    @DeleteMapping("/{tid}")
    public RespBody delReports(@PathVariable Integer tid) {
        return service.delReports(tid);
    }
}
