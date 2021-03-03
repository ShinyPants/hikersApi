package wyl.hikers.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MessageShot implements Serializable {
    private Integer mid;
    private Integer fuid;
    private Integer tuid;
    private Date time;
    private String info;
    private Integer state;
    private String fnike;
    private String fphoto;
    private String tnike;
    private String tphoto;
}
