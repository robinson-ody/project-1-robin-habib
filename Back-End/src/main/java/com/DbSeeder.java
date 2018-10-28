package com;

import java.util.Arrays;
import java.util.List;
import com.model.Login;
import com.repository.LoginRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

@Component
public class DbSeeder implements CommandLineRunner {

    private LoginRepository loginRepository;

    public static void main(String[] args) {
        SpringApplication.run(DbSeeder.class, args);
    }

    public DbSeeder(LoginRepository loginRepository){
        this.loginRepository = loginRepository;
    }

    @Override
    public void run(String... strings){
        loginRepository.deleteAll();

        loginRepository.save(new Login("robin", "robin123","Admin"));
        loginRepository.save(new Login("habib", "habib123","Admin"));
        loginRepository.save(new Login("siapa", "siapaaja","User"));

        System.out.println("Login found with findAll():");
        System.out.println("-------------------------------");
        for (Login login : loginRepository.findAll()) {
            System.out.println(login);
        }
        System.out.println();

        System.out.println("Login found with findByUsername('robin'):");
        System.out.println("--------------------------------");
        System.out.println(loginRepository.findByUsername("robin"));
        System.out.println();

        System.out.println("Login found with findByPassword('habib123'):");
        System.out.println("--------------------------------");
        System.out.println(loginRepository.findByPassword("habib123"));


//        Login MHabib = new Login(
//                "mhabib",
//                "habib123",
//                "Manager"
//        );
//        Login Robin = new Login(
//                "robin",
//                "robin456",
//                "Staff"
//        );
//        Login User = new Login(
//                "admin",
//                "admin",
//                "User"
//        );

//        this.loginRepository.deleteAll();

//        List<Login> Login = Arrays.asList(MHabib, Robin, User);

//        this.loginRepository.saveAll(Login);
    }

}
