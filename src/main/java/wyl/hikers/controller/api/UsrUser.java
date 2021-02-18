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

    /**
     * 检查是否关注了
     * @return
     */
    @GetMapping("/focus")
    public RespBody isFocus(@RequestParam Integer uid, @RequestParam Integer tuid) {
        return service.isFocus(uid, tuid);
    }

    /**
     * 添加关注
     * @return
     */
    @PutMapping("/focus/{uid}/{tuid}/{pwd}")
    public RespBody addFocus(@PathVariable Integer uid, @PathVariable Integer tuid, @PathVariable String pwd) {
        return service.addFocus(uid, tuid, pwd);
    }

    /**
     * 不再关注
     * @return
     */
    @DeleteMapping("/focus")
    public RespBody delFocus(@RequestParam Integer uid, @RequestParam Integer tuid, @RequestParam String pwd) {
        return service.delFocus(uid, tuid, pwd);
    }
}
