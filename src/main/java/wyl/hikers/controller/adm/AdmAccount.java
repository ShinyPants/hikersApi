package wyl.hikers.controller.adm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wyl.hikers.model.Admin;
import wyl.hikers.model.RespBody;
import wyl.hikers.service.SvcAdmin;

@RestController
@RequestMapping("/adm/login")
public class AdmAccount {
    @Autowired
    private SvcAdmin service;

    @GetMapping
    public RespBody login(Admin usr) {
        return service.login(usr);
    }
}
