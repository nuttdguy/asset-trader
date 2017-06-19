package com.assettrader.config;


//import java.util.Properties;
//
//import javax.sql.DataSource;
//
//import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
//import org.springframework.core.env.Environment;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

//@Configuration
//@EnableJpaRepositories(basePackages = "com.assettrader.dao")
//@PropertySource("classpath:application.properties")
//public class DataConfig {
	
	//@Autowired
//	private Environment env;
	
	//@Bean
//	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//		
//		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//		
//		factory.setDataSource(dataSource());
//		factory.setJpaVendorAdapter(vendorAdapter);
//		factory.setJpaProperties(getHibernateProperties());
//		factory.setPackagesToScan(env.getProperty("trader.model.package"));
//		
//		return factory;
//	}
	
	//@Bean
//	public DataSource dataSource() {
//		BasicDataSource ds = new BasicDataSource();
//		ds.setDriverClassName(env.getProperty("trader.db.driver"));
//		ds.setUrl(env.getProperty("trader.db.url"));
//		ds.setUsername(env.getProperty("trader.db.username"));
//		ds.setPassword(env.getProperty("trader.db.password"));
//		ds.setMinIdle( Integer.parseInt(env.getProperty("connection.minPoolSize") ));
//		ds.setMaxIdle( Integer.parseInt(env.getProperty("connection.maxIdleTime")) );
//		ds.setMaxTotal(Integer.parseInt(env.getProperty("connection.maxPoolSize")) );
//		return ds;		
//	}
	
	//@Bean
//	private Properties getHibernateProperties() {
//		Properties prop = new Properties();
//		prop.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
//		prop.put("hibernate.hbm2ddl.auto", env.getProperty("update"));
//		prop.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
//		prop.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));		
//		return prop;
//	}
	
	//@Bean
//	public JpaTransactionManager transactionManager() {
//		JpaTransactionManager transactionManager = new JpaTransactionManager();
//		transactionManager.setEntityManagerFactory( entityManagerFactory().getObject() );
//		transactionManager.setDataSource(dataSource());
//		return transactionManager;
//	}

//}





















