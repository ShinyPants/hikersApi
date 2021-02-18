package wyl.hikers.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class DiscussInfo implements Serializable {
    private Integer did;
    private Date time;
    private String info;
    private Integer uid;
    private String nikeName;
    private String photo;
    private Integer tid;
    private String pwd;
    private Integer reply;
    private String replyNick;
}
