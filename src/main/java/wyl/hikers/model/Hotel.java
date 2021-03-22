package wyl.hikers.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Hotel implements Serializable {
    private Integer hid;
    private Integer sid;
    private String hname;
    private String address;
    private String info;
}
