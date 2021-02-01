package wyl.hikers.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Topic implements Serializable {
    private Integer tid;
    private Integer uid;
    private String title;
    private String info;
    private String[] pics;
    private String pwd;
    private String picsJSON;
}
