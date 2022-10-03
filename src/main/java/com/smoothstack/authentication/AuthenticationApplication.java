package com.smoothstack.authentication;

import com.smoothstack.authentication.model.AppUser;
import com.smoothstack.authentication.model.Role;
import com.smoothstack.authentication.service.AppUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class AuthenticationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(AppUserService appUserService){
        return args -> {
            appUserService.saveRole(new Role(null,"ROLE_USER"));
            appUserService.saveRole(new Role(null,"ROLE_ADMIN"));
            appUserService.saveRole(new Role(null,"ROLE_MANAGER"));
            appUserService.saveRole(new Role(null,"ROLE_SUPER_ADMIN"));

            appUserService.saveAppUser(new AppUser(null,"John Doe","jDoe","1234",new ArrayList<>()));
            appUserService.saveAppUser(new AppUser(null,"Will Jones","WillJo","1234",new ArrayList<>()));
            appUserService.saveAppUser(new AppUser(null,"Jim James","JJames","1234",new ArrayList<>()));
            appUserService.saveAppUser(new AppUser(null,"Evan Yu","eYu","1234",new ArrayList<>()));

            appUserService.addRoleToAppUser("jDoe","ROLE_USER");
            appUserService.addRoleToAppUser("WillJo","ROLE_MANAGER");
            appUserService.addRoleToAppUser("JJames","ROLE_ADMIN");
            appUserService.addRoleToAppUser("eYu","ROLE_SUPER_ADMIN");
            appUserService.addRoleToAppUser("eYu","ROLE_USER");
            appUserService.addRoleToAppUser("eYu","ROLE_MANAGER");
        };
    }

}
