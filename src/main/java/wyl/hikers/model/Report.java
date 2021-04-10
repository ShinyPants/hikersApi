package wyl.hikers.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Report implements Serializable {
    private Integer tid;
    private Integer uid;
    private String reason;
    private String info;
}
