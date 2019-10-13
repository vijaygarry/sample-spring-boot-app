/*
 * Copyright 2019 neasaa.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at
 * 
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package com.neasaa.app;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;

import com.neasaa.app.service.SampleService;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan(basePackages = {"com.neasaa.app.operation", "${scan.security.package}"})
//Pass main config name from command line like --main.config=myApp.config 
// As we are looking up the property from classpath file myApp.config should exists in classpath.
@PropertySource("classpath:${main.config}")
@PropertySource(value= "file:${ssl.file.path}", ignoreResourceNotFound = true)
public class AppConfig {

	//Sample where bean class is base on config name.
	//Read class name from config.
	@Value("${app.serviceClass}")
    private String serviceClassName;

	//Create bean base on config class name
    @Bean (name="dummyService")
    public SampleService myService()
    {
         try {
			return Class.forName(serviceClassName).asSubclass(SampleService.class).newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Failed to create instance of class " + serviceClassName, e);
		}
    }
    
	@Bean (name = "springUtil")
	public SpringUtil SpringUtil () {
		return new SpringUtil();
	}
	
	@Bean (name = "dataSourceConfig")
	@Scope("singleton")
	public DataSourceConfig dataSourceConfig () {
		System.out.println("******** Creating instance of data source config. **********");
		new Exception("Caller stack trace for DS config").printStackTrace();
		return new DataSourceConfig();
	}
	
	@Bean(name = "dataSource")
	@Scope("singleton")
	public DataSource getDataSource() {
		//compile("com.zaxxer:HikariCP:2.6.1")
		DataSourceConfig dsConfig = dataSourceConfig ();
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl( dsConfig.getDbUrl() );
        config.setUsername( dsConfig.getDbUser() );
        config.setPassword( dsConfig.getDbPwd() );
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        
//        autoCommit
//        connectionTimeout
//        idleTimeout
//        maxLifetime
//        connectionTestQuery
//        connectionInitSql
//        validationTimeout
//        maximumPoolSize
//        poolName
//        allowPoolSuspension
//        readOnly
//        transactionIsolation
//        ds = new HikariDataSource( config );
        
        HikariDataSource dataSource = new HikariDataSource(config);
        return dataSource;
//		DataSourceBuilder<? extends DataSource> dataSourceBuilder = DataSourceBuilder.create();
//		dataSourceBuilder.driverClassName("org.h2.Driver");
//		dataSourceBuilder.url("jdbc:h2:mem:test");
//		dataSourceBuilder.username("SA");
//		dataSourceBuilder.password("");
//		return dataSourceBuilder.build();
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate () {
		return new JdbcTemplate(getDataSource());
	}

}