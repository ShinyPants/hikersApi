package wyl.hikers.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "sys-setting")
public class SysConfig {
    private String uploadPath;
    private Integer topicLoadNum;
}
