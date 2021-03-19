package wyl.hikers.controller.adm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wyl.hikers.model.Part;
import wyl.hikers.model.RespBody;
import wyl.hikers.service.SvcParts;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/adm/parts")
public class AdmParts {
    @Autowired
    private SvcParts service;

    @PostMapping
    public RespBody addPart(@RequestBody Part part) {
        return RespBody.ok(service.addPart(part));
    }

    @PutMapping
    public RespBody updateParts(@RequestBody Part part) {
        List<Part> list = new ArrayList<>();
        list.add(part);
        return RespBody.ok(service.updateParts(list));
    }
}
