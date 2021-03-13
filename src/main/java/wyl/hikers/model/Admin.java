package wyl.hikers.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Admin extends User implements Serializable {
    private Integer admid;
    private String realname;
}
