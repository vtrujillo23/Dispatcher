package com.evaluacion.dispatcher.persistence.configuration;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.evaluacion.dispatcher.persistence.dao")
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = {"com.evaluacion.dispatcher.persistence.dao"})
public class DispatcherPersistenceConf {
	@Value( "${spring.datasource.url}" )
	private String databaseUrl;
	@Value( "${spring.datasource.driver-class-name}" )
	private String databaseDriver;
	@Value( "${spring.datasource.username}" )
	private String databaseUserName;
	@Value( "${spring.datasource.password}" )
	private String databasePassword;
	
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {

		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setPackagesToScan("com.evaluacion.dispatcher.persistence.entities");

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
		entityManagerFactoryBean.setJpaProperties(additionalProperties());

		return entityManagerFactoryBean;
	}

	
	@Bean
	public DataSource dataSource() {

		HikariDataSource dataSource = new HikariDataSource();

		dataSource.setDriverClassName(databaseDriver);
		dataSource.setJdbcUrl(databaseUrl);
		dataSource.setUsername(databaseUserName);
		dataSource.setPassword(databasePassword);
		dataSource.setIdleTimeout(120000);//Dos minutos
		dataSource.setMaximumPoolSize(20);
		dataSource.setMinimumIdle(5);
		dataSource.setConnectionTimeout(45000);
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {

		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);

		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {

		return new PersistenceExceptionTranslationPostProcessor();
	}

	private Properties additionalProperties() {

		Properties properties = new Properties();

		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL94Dialect");
		properties.setProperty("hibernate.physical_naming_strategy",
				"org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
		properties.setProperty("hibernate.show_sql", "false");
		return properties;
	}
}
