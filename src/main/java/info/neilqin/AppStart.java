package info.neilqin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Neil
 * @date 2018/11/14 11:03
 */
@SpringBootApplication
@MapperScan("info.neilqin.repository")
public class AppStart {

    public static void main(String[] args) {
        SpringApplication.run(AppStart.class,args);
    }
}
