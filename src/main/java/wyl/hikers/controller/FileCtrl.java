package wyl.hikers.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import wyl.hikers.model.RespBody;
import wyl.hikers.service.SvcFile;

@RestController
@RequestMapping("/file")
@Slf4j
public class FileCtrl {
    @Autowired
    private SvcFile service;

    @PostMapping("/pic")
    public RespBody uploadPics(@RequestParam("file")MultipartFile file,
                               @RequestParam("uid") Integer uid,
                               @RequestParam("pwd") String pwd) {
        return service.savePic(file, uid, pwd);
    }
}
