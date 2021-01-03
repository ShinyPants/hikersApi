package wyl.hikers.model;

import java.io.Serializable;

public class Test implements Serializable {
    private Integer id;
    private String str;

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", str='" + str + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
