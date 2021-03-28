package wyl.hikers.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Hotel2 implements Serializable {
    private Integer hid;
    private Integer sid;
    private String hname;
    private String address;
    private String info;
    private String rooms;
    private String roomsjson;
}
