package wyl.hikers.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wyl.hikers.model.Part;
import wyl.hikers.model.RespBody;
import wyl.hikers.service.SvcParts;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/parts")
public class UsrParts {
    @Autowired
    private SvcParts service;

    @GetMapping
    public RespBody getParts() {
        List<Part> parts = service.getParts();
        return RespBody.ok(parts);
    }

    @PostMapping
    public  RespBody addPart(@RequestBody Part part) {
        service.addPart(part);
        return RespBody.ok(null);
    }

    @PutMapping
    public  RespBody updatePart(@RequestBody Part part) {
        List<Part> list = new ArrayList<>();
        list.add(part);
        service.updateParts(list);
        return RespBody.ok(null);
    }
}
