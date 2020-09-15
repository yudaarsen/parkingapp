package ru.ikbo1018.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.ikbo1018.app.data.AccountRepository;
import ru.ikbo1018.app.models.account.Account;
import ru.ikbo1018.app.services.AccountService;
import ru.ikbo1018.app.validators.AccountValidator;

@Controller
@RequestMapping(path = "/registration")
public class RegistrationController {

    @Autowired
    private AccountService accountService;

    @Autowired
    @Qualifier("accountValidator")
    private Validator validator;

    @InitBinder
    public void DataBinding(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(validator);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String registration(){
        return "registration/registration";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String registerAccount(@Validated @ModelAttribute(name = "account") Account account, BindingResult result) {
        if(result.hasErrors())
            return "registration/registration";
        try {
            accountService.createAccount(account);
        } catch (DuplicateKeyException ex) {
            return "registration/registration";
        }
        return "home";
    }

    @ModelAttribute(name = "account")
    public Account account() {
        return new Account();
    }
}
