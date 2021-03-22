package wyl.hikers.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Sight implements Serializable {
    private Integer sid;
    private String sname;
    private String info;
    private String address;
    private String[] pics;
    private String picsJSON;
}
