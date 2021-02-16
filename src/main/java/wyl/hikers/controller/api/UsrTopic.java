package wyl.hikers.controller.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wyl.hikers.model.RespBody;
import wyl.hikers.model.Topic;
import wyl.hikers.service.SvcTopic;

@Slf4j
@RestController
@RequestMapping("/api/topic")
public class UsrTopic {
    @Autowired
    private SvcTopic service;

    @PostMapping
    public RespBody addTopic(@RequestBody Topic topic) {
        return service.addTopic(topic);
    }

    @GetMapping("/topics")
    public RespBody getTopics(@RequestParam Integer pid, @RequestParam Integer tid) {
        return service.getTopics(pid, tid);
    }

    @GetMapping("/collect")
    public RespBody isCollect(@RequestParam Integer uid, @RequestParam Integer tid) {
        log.info("isCollect " + uid + " " + tid);
        return service.isCollect(uid, tid);
    }

    @PutMapping("/collect/{uid}/{tid}")
    public RespBody addCollect(@PathVariable Integer uid, @PathVariable Integer tid) {
        return service.addCollect(uid, tid);
    }

    @DeleteMapping("/collect")
    public RespBody delCollect(@RequestParam Integer uid, @RequestParam Integer tid) {
        return service.delCollect(uid, tid);
    }

    @GetMapping("/agree")
    public RespBody isAgree(@RequestParam Integer uid, @RequestParam Integer tid) {
        log.info("isCollect " + uid + " " + tid);
        return service.isAgree(uid, tid);
    }

    @PutMapping("/agree/{uid}/{tid}")
    public RespBody addAgree(@PathVariable Integer uid, @PathVariable Integer tid) {
        return service.addAgree(uid, tid);
    }

    @DeleteMapping("/agree")
    public RespBody delAgree(@RequestParam Integer uid, @RequestParam Integer tid) {
        return service.delAgree(uid, tid);
    }
}
