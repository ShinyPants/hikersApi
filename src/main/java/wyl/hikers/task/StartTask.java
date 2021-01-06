package wyl.hikers.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import wyl.hikers.service.SvcParts;

@Component
@Order(1) // 越低优先级越高
public class StartTask implements CommandLineRunner {
    @Autowired
    private SvcParts parts;

    @Override
    public void run(String... args) throws Exception {
        // 同步parts
        parts.sync();
    }
}
