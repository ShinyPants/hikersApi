package wyl.hikers.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wyl.hikers.model.RespBody;
import wyl.hikers.service.SvcFocus;

@RestController
@RequestMapping("/api/myfocus")
public class UsrFocus {

    @Autowired
    private SvcFocus service;

    @GetMapping
    public RespBody getFocuses(Integer uid) {
        return service.getFocuses(uid);
    }
}
