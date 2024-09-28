import org.example.PubApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * @program:
 * @ClassName:
 * @description:
 * @author: zgc
 * @date:
 * @Version 1.0
 **/

@SpringBootTest(classes = PubApplication.class)
public class test {
    @Test
    public void fun2() {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("D:\\test.sql"))) {
            for (int i = 1; i <= 10000000; i++) {
                // writer.append("insert into t_test(id,`name`) values (" + i + ",'" + UUID.randomUUID().toString() + "');\n");
                // 这是利用工具导入，只输出列值
                writer.append(i + "," + UUID.randomUUID().toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
