package com;

<<<<<<< HEAD
<<<<<<< HEAD
import com.model.Login;
import com.repository.LoginRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

=======
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
>>>>>>> still on progress
=======
import com.model.Login;
import com.repository.LoginRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

>>>>>>> berhasil koneksi ke html belum bisa melakukan verifikasi
import java.util.Arrays;
import java.util.List;

@Component
public class DbSeeder implements CommandLineRunner {
<<<<<<< HEAD
<<<<<<< HEAD
//PENGISIAN DATA AWAL HANYA UNTUK CEK HTML DAN DATA DI BAWAH INI DIMASUKKAN KE DATABASE MONGODB
=======

>>>>>>> still on progress
=======
//PENGISIAN DATA AWAL HANYA UNTUK CEK HTML DAN DATA DI BAWAH INI DIMASUKKAN KE DATABASE MONGODB
>>>>>>> berhasil koneksi ke html belum bisa melakukan verifikasi
    private LoginRepository loginRepository;

    public DbSeeder(LoginRepository loginRepository){
        this.loginRepository=loginRepository;
    }

    @Override
    public void run(String... strings) throws Exception{
<<<<<<< HEAD
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
=======
        Login loginPage = new Login(
                "admin",
                "admin",
                "justAdmin"
        );
        //drop all
        this.loginRepository.deleteAll();

        //add to the database
        List<Login>Login=Arrays.asList(loginPage);
>>>>>>> still on progress
        this.loginRepository.saveAll(Login);
    }

}
