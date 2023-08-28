package com.example.authserver;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class AuthServerApplication implements CommandLineRunner {

    // @Autowired
    // private BCryptPasswordEncoder bCryptPasswordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        String valor = "12345";
        for (int i = 0; i < 4; i++) {
            //String password = bCryptPasswordEncoder.encode(valor);
            System.out.println("password");
        }

    }

}
