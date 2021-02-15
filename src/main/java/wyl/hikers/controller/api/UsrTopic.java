package wyl.hikers.controller.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wyl.hikers.config.SysConfig;
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

    @GetMapping("/new")
    public RespBody getTopics(@RequestParam Integer pid) {
        return service.getTopics(pid);
    }

    @GetMapping("/bottom")
    public RespBody getTopics(@RequestParam Integer pid, @RequestParam Integer tid) {
        return service.getTopics(pid, tid);
    }
}
