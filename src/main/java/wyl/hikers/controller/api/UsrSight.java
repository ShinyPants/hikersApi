package wyl.hikers.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wyl.hikers.model.RespBody;
import wyl.hikers.model.Sight;
import wyl.hikers.service.SvcSight;

@RestController
@RequestMapping("/api/sight")
public class UsrSight {
    @Autowired
    private SvcSight service;

    @GetMapping
    public RespBody getSights() {
        return service.getSights();
    }
}
