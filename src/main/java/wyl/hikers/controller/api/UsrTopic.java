package wyl.hikers.controller.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
