package wyl.hikers.controller.adm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wyl.hikers.model.RespBody;
import wyl.hikers.service.SvcTopic;

@RestController
@RequestMapping("/adm/topic")
public class AdmTopic {
    @Autowired
    private SvcTopic service;

    @DeleteMapping("/{tid}")
    public RespBody deleteTopic(@PathVariable Integer tid) {
        return service.delTopicByAdm(tid);
    }
}
