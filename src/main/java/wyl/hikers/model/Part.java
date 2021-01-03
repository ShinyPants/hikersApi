package wyl.hikers.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 保存分区信息的类<br>
 * 包含以下内容：
 * <li>id：主键</li>
 * <li>picUrl：分区图片的url</li>
 * <li>name：分区名称</li>
 */
@Data
public class Part implements Serializable {
    private Integer id;
    private String picUrl;
    private String name;
    private Integer score;
}
