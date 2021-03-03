package wyl.hikers.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wyl.hikers.model.Message;
import wyl.hikers.model.RespBody;
import wyl.hikers.service.SvcMsg;

@RestController
@RequestMapping("/api/msg")
public class UsrMsg {

    @Autowired
    private SvcMsg service;

    @PostMapping
    public RespBody addMsg(@RequestBody Message msg) {
        return service.addMsg(msg);
    }

    @GetMapping("/info")
    public RespBody getTalkingMsg(@RequestParam Integer fuid, @RequestParam Integer tuid) {
        return service.getTalkingMsg(fuid, tuid);
    }

    @GetMapping("/list")
    public RespBody getTalkList(@RequestParam Integer uid) {
        return service.getTalkList(uid);
    }
}
