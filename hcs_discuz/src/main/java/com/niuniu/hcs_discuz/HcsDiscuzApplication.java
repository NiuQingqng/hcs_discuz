package com.niuniu.hcs_discuz;

import com.niuniu.hcs_discuz.config.Config;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(Config.class)
@MapperScan("com.niuniu.hcs_discuz.dao")
public class HcsDiscuzApplication {

    public static void main(String[] args) {
        SpringApplication.run(HcsDiscuzApplication.class, args);
    }

}
