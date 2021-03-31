package wyl.hikers.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Person implements Serializable {
    private String pname;
    private String cardid;
}
