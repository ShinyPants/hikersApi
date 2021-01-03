package wyl.hikers.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class RespBody implements Serializable {
    private Integer status;
    private Object data;

    private RespBody(Integer status, Object data) {
        this.status = status;
        this.data = data;
    }

    public static RespBody ok(Object data) {
        return new RespBody(1, data);
    }

    public static RespBody failed(Object data) {return new RespBody(0, data);}

    public static RespBody error(Object data) {
        return new RespBody(-1, data);
    }

    public static RespBody build(Integer status, Object data) {
        return new RespBody(status, data);
    }
}
