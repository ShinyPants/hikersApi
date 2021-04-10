package wyl.hikers.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ReportList implements Serializable {
    private Integer tid;
    private Integer num;
    private Integer uid;
    private String uname;
}
