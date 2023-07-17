package com.trans.tms.authority.config;

import com.trans.tms.database.datasource.BaseMybatisConfiguration;
import com.trans.tms.database.properties.DatabaseProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * @author huajieli
 * @create 2021-10-05 20:19
 */
@Configuration
@Slf4j
public class AuthorityMybatisAutoConfiguration extends BaseMybatisConfiguration {
    public AuthorityMybatisAutoConfiguration(DatabaseProperties databaseProperties) {
        super(databaseProperties);
    }
}