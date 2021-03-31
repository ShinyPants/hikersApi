package wyl.hikers.model;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;

@Data
@Slf4j
public class Order implements Serializable {
    private Integer oid;
    private Integer hid;
    private Integer uid;
    private String roomtype;
    private String sttime;
    private String edtime;
    private Person[] person;
    private String personJSON;
    private String hname;

    public void setLivetime(String[] livetime) {
        this.sttime = livetime[0];
        this.edtime = livetime[1];
    }

    public void setPerson(Person[] p) {
        this.personJSON = JSON.toJSONString(p);
    }

    public void setPersonJSON(String json) {
        List<Person> list = JSON.parseArray(json, Person.class);
        this.person = list.toArray(new Person[list.size()]);
    }
}
