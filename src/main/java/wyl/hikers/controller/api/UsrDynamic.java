package wyl.hikers.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wyl.hikers.model.RespBody;
import wyl.hikers.service.SvcDynamic;

@RestController
@RequestMapping("/api/dynamic")
public class UsrDynamic {
    @Autowired
    private SvcDynamic service;

    @GetMapping
    public RespBody getDynamics(Integer uid, Integer tid) {
        return service.getDynamics(uid, tid);
    }
}
