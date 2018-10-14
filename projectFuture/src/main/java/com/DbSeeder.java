package com;

import com.model.Login;
import com.repository.LoginRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DbSeeder implements CommandLineRunner {

    private LoginRepository loginRepository;

    public DbSeeder(LoginRepository loginRepository){
        this.loginRepository=loginRepository;
    }

    @Override
    public void run(String... strings) throws Exception{
        Login MHabib = new Login(
                "mhabib",
                "habib123",
                "Manager"
        );
        Login Robin = new Login(
                "robin",
                "robin456",
                "Staff"
        );
        Login User = new Login(
                "admin",
                "admin",
                "User"
        );
        this.loginRepository.deleteAll();
        //add to the database
        List<Login>Login=Arrays.asList(MHabib, Robin, User);
        this.loginRepository.saveAll(Login);
    }

}
