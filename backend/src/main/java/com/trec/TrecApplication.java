package com.trec;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrecApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrecApplication.class, args);
	}

}
