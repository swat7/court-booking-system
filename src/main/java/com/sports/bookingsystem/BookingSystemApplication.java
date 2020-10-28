package com.sports.bookingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.sports.bookingsystem.repository.BaseRepositoryFactoryBean;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = { "com.sports.bookingsystem.repository" }, repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
public class BookingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingSystemApplication.class, args);
	}

}
