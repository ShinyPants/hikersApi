package wyl.hikers.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{sid}")
    public RespBody getHotelsBySid(@PathVariable Integer sid) {
        return service.getHotelsBySid(sid);
    }

    @GetMapping("/one/{hid}")
    public RespBody getHotelByHid(@PathVariable Integer hid) {
        return service.getHotelByHid(hid);
    }
}
