package wyl.hikers.controller.adm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wyl.hikers.model.RespBody;
import wyl.hikers.model.Sight;
import wyl.hikers.service.SvcSight;

@RestController
@RequestMapping("/adm/sight")
public class AdmSight {
    @Autowired
    private SvcSight service;

    @PostMapping
    public RespBody addSight(@RequestBody Sight sight) {
        return service.addSight(sight);
    }

    @PutMapping
    public RespBody editSight(@RequestBody Sight sight) {
        return service.editSight(sight);
    }
}
