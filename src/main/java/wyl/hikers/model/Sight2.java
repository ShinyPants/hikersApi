package wyl.hikers.model;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;

@Data
@Slf4j
public class Sight2 implements Serializable {
    private Integer sid;
    private String sname;
    private String info;
    private String address;
    private String[] pics;
    private String picsJSON;

    public void setPics(String pics) {
        List<String> list = JSON.parseArray(pics, String.class);
        this.pics = list.toArray(new String[list.size()]);
    }
}
