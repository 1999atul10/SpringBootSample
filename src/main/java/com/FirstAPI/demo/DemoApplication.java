package com.FirstAPI.demo;

import com.FirstAPI.demo.Model.Users;
import com.FirstAPI.demo.Service.UsersService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext context=SpringApplication.run(DemoApplication.class, args);
		Users users=context.getBean(Users.class);
		users.firstName="Ayush";
		users.lastName="Bisht";
		users.address="Badarpur Delhi";
		users.mobileNo="7589623589";
		System.out.println(users);
		UsersService usersService =context.getBean(UsersService.class);

		//usersService.addUsers(users);
		usersService.deleteUse(users);
		usersService.getAllUsers();





	}
}
