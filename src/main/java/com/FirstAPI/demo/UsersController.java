package com.FirstAPI.demo;

import com.FirstAPI.demo.Model.Users;
import com.FirstAPI.demo.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/users")
public class UsersController {
    public UsersService getUsersService() {
        return usersService;
    }

    @Autowired
    public void setUsersService(UsersService usersService) {
        this.usersService = usersService;
    }

    private UsersService usersService;

    @PostMapping("/add")
    public String addUsers(@RequestBody Users users) {
        usersService.addUsers(users);
        return "User Added Successfully.";
    }
    @GetMapping("/All")
    public List<Users> getUsersInfo(){
        return usersService.getAllUsers();

    }
}
