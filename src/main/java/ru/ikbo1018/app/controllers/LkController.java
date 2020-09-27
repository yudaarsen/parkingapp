package ru.ikbo1018.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.ikbo1018.app.models.account.Account;
import ru.ikbo1018.app.services.AccountService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/lk")
@SessionAttributes("accountId")
public class LkController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public String lk(@SessionAttribute("accountId") int accountId, HttpSession session,
                     HttpServletRequest request){
        if(accountId <= 0)
            return "redirect:/";
        try {
            Account account = accountService.getAccountById(accountId);
            request.setAttribute("accountInfo", account);
            return "lk/lk";
        } catch (IllegalArgumentException e) {
            session.removeAttribute("accountId");
            return "redirect:/";
        }
    }
}
