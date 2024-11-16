package com.FirstAPI.demo.Service;

import com.FirstAPI.demo.Model.Users;
import com.FirstAPI.demo.Repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Controller
public class UsersService {

    public UsersRepo getUsersRepo() {
        return usersRepo;
    }
    @Autowired
    public void setUsersRepo(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }

    private UsersRepo usersRepo;

    public List<Users> getAllUsers(){
        return usersRepo.findAll();
    }
    public void addUsers(Users users){
        boolean isSaved=usersRepo.save(users);
        if (isSaved==true){
            System.out.println("Record Saved Successfully.");
        }
        else{
            System.out.println("Record Not Saved.");
        }
    }
    public void deleteUse(Users users){
        boolean isdeleted=usersRepo.delete(users);
        if (isdeleted==true){
            System.out.println("Record deleted Successfully.");
        }else {
            System.out.println("Record Not deleted.");
        }
    }

}
