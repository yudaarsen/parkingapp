package ru.ikbo1018.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
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
import ru.ikbo1018.app.models.account.User;
import ru.ikbo1018.app.models.factories.AccountFactory;
import ru.ikbo1018.app.services.AccountService;
import ru.ikbo1018.app.validators.AccountValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Controller
@RequestMapping(path = "/registration")
public class RegistrationController {

    @Autowired
    private AccountService accountService;

    @Autowired
    @Qualifier("accountValidator")
    private Validator validator;

    @Autowired
    private MessageSource messageSource;

    @Value("${userRoleId}")
    private int userRoleId;

    @Autowired
    private AccountFactory accountFactory;

    @InitBinder
    public void dataBinding(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(validator);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String registration(){
        return "registration/registration";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String registerAccount(@Validated @ModelAttribute(name = "account") Account account, BindingResult result,
                                  HttpServletRequest request, Locale locale) {
        if(result.hasErrors())
            return "registration/registration";
        try {
            accountService.createAccount(account);
        } catch (DataIntegrityViolationException e) {
            String errorCode = "reg.internal";
            if(e instanceof DuplicateKeyException) {
                errorCode = "reg.duplicate";
            }
            request.setAttribute("error", messageSource.getMessage(errorCode, null, locale));
            return "registration/registration";
        }
        return "home";
    }

    @ModelAttribute(name = "account")
    public Account account() {
        return accountFactory.create(userRoleId);
    }
}
