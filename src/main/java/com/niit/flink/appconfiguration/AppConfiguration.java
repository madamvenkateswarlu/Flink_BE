package com.niit.flink.appconfiguration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.flink.model.UserDetails;



@Configuration
@ComponentScan("com.niit.flink")
@EnableTransactionManagement
public class AppConfiguration {
	

	@Bean(name = "dataSource")
	public DataSource getDatasource(){
		DriverManagerDataSource dmd=new DriverManagerDataSource();
		dmd.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dmd.setUrl("jdbc:oracle:thin:@//localhost:1521/XE ");
		dmd.setUsername("Flink");
        dmd.setPassword("root");
        System.out.println("datasource");
		return dmd;		
		
		
	}
	
	public Properties getHibernateProperties(){
		
		Properties p=new Properties();
     p.setProperty("hibernate.hbm2ddl.auto", "update"); 
		

		p.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");

		
		
        System.out.println("properties Flink");

		return p;
	}
	@Autowired
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory(DataSource source){
		
		LocalSessionFactoryBuilder session=new LocalSessionFactoryBuilder(source);
		session.addProperties(getHibernateProperties());
		session.addAnnotatedClass(UserDetails.class);
		System.out.println("session factory Flink");

	   return session.buildSessionFactory();
		
	}
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getHibernateTransaction(SessionFactory s) {
		HibernateTransactionManager hbm = new HibernateTransactionManager(s);
        System.out.println("transaction Flink");

		return hbm;
	}
	

}
