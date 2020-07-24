package com.my.ansicon.aaaserver.database.config;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
@ConditionalOnProperty(
	    value="wrapper_db", 
	    havingValue = "mysql", 
	    matchIfMissing = false)
public class MySQLDbConfig {
	
	private String flywayDBUrl;
	private String flywayDBUser;
	private String flywayDBPwd;
	
	@Bean("datasource")
    public DataSource dataSource(
                          @Value("${spring.datasource.username:AnsiCon}") String datasourceUsername,
                          @Value("${spring.datasource.password:AnsiCon!23$}") String datasourcePassword,
                          @Value("${spring.datasource.url:jdbc:mysql://localhost:3306/oauth?createDatabaseIfNotExist=true&autoReconnect=true&verifyServerCertificate=false&useSSL=false}") String datasourceUrl,
                          @Value("${spring.datasource.driver-class-name:com.mysql.cj.jdbc.Driver}") String datasourceDriver){
        
		System.setProperty("flyway.validate-on-migrate", "false");
	    System.setProperty("flyway.baseline-on-migrate", "false");
	    System.setProperty("flyway.ignore-missing-migrations", "true");
	    
	    this.flywayDBUrl = datasourceUrl;
	    this.flywayDBUser = datasourceUsername;
	    this.flywayDBPwd = datasourcePassword;
		
		return DataSourceBuilder
                .create()
                .username(datasourceUsername)
                .password(datasourcePassword)
                .url(datasourceUrl)
                .driverClassName(datasourceDriver)
                .build();
    }
    
    @Bean(initMethod = "migrate")
    @DependsOn("datasource")
    Flyway flyway() {
        Flyway flyway = new Flyway();
        flyway.setBaselineOnMigrate(true);
        flyway.setDataSource(this.flywayDBUrl, this.flywayDBUser, this.flywayDBPwd);
        return flyway;
    }
}
