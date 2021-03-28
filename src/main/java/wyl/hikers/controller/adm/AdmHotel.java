package wyl.hikers.controller.adm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wyl.hikers.model.Hotel;
import wyl.hikers.model.RespBody;
import wyl.hikers.service.SvcHotel;

@RestController
@RequestMapping("/adm/hotel")
public class AdmHotel {
    @Autowired
    private SvcHotel service;

    @PostMapping
    public RespBody addHotel(@RequestBody Hotel hotel) {
        return service.addHotel(hotel);
    }

    @PutMapping
    public RespBody updateHotel(@RequestBody Hotel hotel) {
        return service.updateHotel(hotel);
    }
}
