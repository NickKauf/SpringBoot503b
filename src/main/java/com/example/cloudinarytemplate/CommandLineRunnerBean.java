package com.example.cloudinarytemplate;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerBean implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    CarRepository carRepository;

    public void run(String...args){
        User admin = new User("admin", "Dave@domain.com", "admin", "Dave","Wolf", true);
        Role adminRole = new Role("admin", "ROLE_USER");
        Role adminRole2 = new Role("admin", "ROLE_ADMIN");

        userRepository.save(admin);
        roleRepository.save(adminRole);
        roleRepository.save(adminRole2);

        User user = new User("user", "user@domain.com", "user", "User", "User", true);
        Role userRole = new Role("user", "ROLE_USER");

        userRepository.save(user);
        roleRepository.save(userRole);

        Car car1 = new Car("Accord", "Honda", "a red honda accord", "https://res.cloudinary.com/nk4363/image/upload/v1628530117/twznlril5khsq8khwufv.png");
        Car car2 = new Car("Gremlin", "AMC", "a green amc gremlin", "https://res.cloudinary.com/nk4363/image/upload/v1628530015/hgq3potesysjkgu3f6wj.jpg");
        carRepository.save(car1);
        carRepository.save(car2);


    }


}
