package wyl.hikers.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Message implements Serializable {
    private Integer mid;
    private Integer fuid;
    private Integer tuid;
    private Date time;
    private String info;
    private Integer state;
    private String pwd;
}
