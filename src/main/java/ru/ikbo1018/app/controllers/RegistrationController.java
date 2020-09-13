package ru.ikbo1018.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.ikbo1018.app.data.AccountRepository;
import ru.ikbo1018.app.models.account.Account;
import ru.ikbo1018.app.services.AccountService;

@Controller
@RequestMapping(path = "/registration")
public class RegistrationController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.GET)
    public String registration(){
        return "registration/registration";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String registerAccount(@ModelAttribute(name = "account") Account account, BindingResult result) {
        //TODO: add validation
        try {
            accountService.createAccount(account);
        } catch (DuplicateKeyException ex) {
            result.reject("dupkey");
            return "registration/registration";
        }
        return "home";
    }

    @ModelAttribute(name = "account")
    public Account account() {
        return new Account();
    }
}
