package wyl.hikers.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wyl.hikers.model.RespBody;
import wyl.hikers.service.SvcHotel;

@RestController
@RequestMapping("/api/hotel")
public class UsrHotel {
    @Autowired
    private SvcHotel service;

    @GetMapping
    public RespBody getHotels() { return  service.getHotels(); }
}
