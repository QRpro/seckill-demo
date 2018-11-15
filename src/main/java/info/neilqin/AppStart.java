package info.neilqin;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author Neil
 * @date 2018/11/14 11:03
 */
@SpringBootApplication
@MapperScan("info.neilqin.repository")
public class AppStart {
//
//    @Bean
//    public SqlSessionFactoryBean sqlSessionFactory(){
//        return new SqlSessionFactoryBean();
//    }
//    @Bean
//    public SqlSessionTemplate sqlSessionTemplate(){
//        return new SqlSessionTemplate(sqlSessionFactory());
//    }
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class,args);
    }
}
