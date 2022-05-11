package com.niuniu.hcs_discuz.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Import;

/**
 * @author niuniu
 * @date 2022/3/18
 */
@Configurable
@Import({RedisConfig.class, WebMvcConfiguration.class})
public class Config {

}
