package wyl.hikers.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wyl.hikers.model.DiscussInfo;
import wyl.hikers.model.RespBody;
import wyl.hikers.service.SvcDiscuss;

@RestController
@RequestMapping("/api/discuss")
public class UsrDiscuss {
    @Autowired
    private SvcDiscuss service;

    @PostMapping
    public RespBody addDiscuss(@RequestBody DiscussInfo info) {
        return service.addDiscuss(info);
    }

    @GetMapping
    public RespBody getDiscusses(@RequestParam Integer tid, @RequestParam Integer did) {
        return service.getDiscusses(tid, did);
    }
}
