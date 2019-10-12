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

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan(basePackages = "com.neasaa.app.operation")
@PropertySource("classpath:db.config")
public class AppConfig {

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