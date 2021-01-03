package wyl.hikers.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wyl.hikers.model.Part;
import wyl.hikers.model.RespBody;
import wyl.hikers.service.SvcParts;

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
}
