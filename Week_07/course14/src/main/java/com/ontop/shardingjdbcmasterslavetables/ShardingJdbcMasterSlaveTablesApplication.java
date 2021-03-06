package com.ontop.shardingjdbcmasterslavetables;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableConfigurationProperties
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class ShardingJdbcMasterSlaveTablesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShardingJdbcMasterSlaveTablesApplication.class, args);
	}

}
