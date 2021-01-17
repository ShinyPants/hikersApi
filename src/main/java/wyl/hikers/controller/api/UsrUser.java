package wyl.hikers.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wyl.hikers.model.RespBody;
import wyl.hikers.model.User;
import wyl.hikers.service.SvcUser;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api/user")
public class UsrUser {
    @Autowired
    private SvcUser service;

    @PostMapping("/regist")
    private RespBody register(@RequestBody User user) {
        return service.register(user);
    }

    @GetMapping("/login")
    private RespBody login(User user) {
        return service.login(user);
    }

    /**
     * 验证手机号
     * @param number
     * @return
     */
    @GetMapping("/phone/{number}")
    public RespBody checkPhone(@PathVariable("number") String number) {
        return service.checkPhone(number);
    }

    /**
     * 验证昵称
     * @param nike
     * @return
     */
    @GetMapping("/nike/{nike}")
    public RespBody checkNike(@PathVariable("nike") String nike) {
        return service.checkNike(nike);
    }

    /**
     * 验证邮箱
     * @param mail
     * @return
     */
    @GetMapping("/mail/{mail}")
    public RespBody checkMail(@PathVariable("mail") String mail) {
        return service.checkMail(mail);
    }
}
