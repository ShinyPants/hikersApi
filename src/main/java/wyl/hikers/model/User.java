package wyl.hikers.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    private Integer uid;
    private String phone;
    private String mail;
    private String nikeName;
    private String pwd;
    private String question;
    private String answer;
    private Date birthday;
    private String uname;
}
