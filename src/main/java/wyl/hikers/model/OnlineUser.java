package wyl.hikers.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class OnlineUser implements Serializable {
    private String pwd; // 口令
    private Long time; // 添加时间
    private Integer timeOut; // 过期时间
}
