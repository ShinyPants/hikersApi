package wyl.hikers.model;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class TopicInfo implements Serializable {
    private Integer tid;
    private String title;
    private String info;
    private List<String> pics;
    private Integer pid;
    private String pname;
    private Integer uid;
    private String nikeName;
    private String photo;
    private Date time;

    private Integer collect;
    private Integer discuss;
    private Integer agree;
    private Integer hotpoint;

    public void setPics(String picsJSON) {
        this.pics = JSON.parseArray(picsJSON, String.class);
    }
}
