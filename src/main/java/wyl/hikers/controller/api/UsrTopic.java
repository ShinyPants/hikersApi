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

    @GetMapping
    public RespBody getOneTopic(@RequestParam Integer tid) {
        return service.getOneTopic(tid);
    }

    @GetMapping("/topics")
    public RespBody getTopics(@RequestParam Integer pid, @RequestParam Integer tid) {
        return service.getTopics(pid, tid);
    }

    @GetMapping("/topics/{uid}/{tid}")
    public RespBody getTopicsByUser(@PathVariable Integer uid, @PathVariable Integer tid) {
        return service.getTopicsByUser(uid, tid);
    }

    @GetMapping("/collect")
    public RespBody isCollect(@RequestParam Integer uid, @RequestParam Integer tid) {
        return service.isCollect(uid, tid);
    }

    @PutMapping("/collect/{uid}/{tid}/{pwd}")
    public RespBody addCollect(@PathVariable Integer uid, @PathVariable Integer tid, @PathVariable String pwd) {
        return service.addCollect(uid, tid, pwd);
    }

    @DeleteMapping("/collect")
    public RespBody delCollect(@RequestParam Integer uid, @RequestParam Integer tid, @RequestParam String pwd) {
        return service.delCollect(uid, tid, pwd);
    }

    @GetMapping("/agree")
    public RespBody isAgree(@RequestParam Integer uid, @RequestParam Integer tid) {
        return service.isAgree(uid, tid);
    }

    @PutMapping("/agree/{uid}/{tid}/{pwd}")
    public RespBody addAgree(@PathVariable Integer uid, @PathVariable Integer tid, @PathVariable String pwd) {
        return service.addAgree(uid, tid, pwd);
    }

    @DeleteMapping("/agree")
    public RespBody delAgree(@RequestParam Integer uid, @RequestParam Integer tid, @RequestParam String pwd) {
        return service.delAgree(uid, tid, pwd);
    }
}
