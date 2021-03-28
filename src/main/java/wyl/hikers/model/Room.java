package wyl.hikers.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Room implements Serializable {
    private String roomtype;
    private Integer num;
    private Double price;
}
