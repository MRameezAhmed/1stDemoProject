package com.nisum;

import com.nisum.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class SpringStart {

    public static void main(String[] args){ SpringApplication.run(SpringStart.class, args); }


}
