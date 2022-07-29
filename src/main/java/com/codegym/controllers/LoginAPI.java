package com.codegym.controllers;

import com.codegym.models.Account;
import com.codegym.service.AccService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
@RestController
@CrossOrigin("*")
@RequestMapping("/acc")
public class LoginAPI {
    private static Account account;
    @Autowired
    HttpSession httpSession;
    @Autowired
    AccService accService;

    @GetMapping
    public Account getAcc(){
        return account;
    }

    @GetMapping("/logout")
    public void logout(){
        account.setLogged(false);
        accService.save(account);
        account=null;

    }

    @PostMapping
    public boolean login(@RequestBody Account account1){
        Account account2=accService.check(account1.getUserName(),account1.getPass());

        if (account2!=null){
            account2.setLogged(true);
            account=account2;
            accService.save(account2);
            return true;
        }
        return false;
    }


}
