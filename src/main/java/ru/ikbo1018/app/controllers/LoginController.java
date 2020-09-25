package ru.ikbo1018.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceResourceBundle;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.Errors;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.ikbo1018.app.models.account.Account;
import ru.ikbo1018.app.services.AccountService;

import javax.servlet.http.HttpSession;
import java.util.Locale;

@Controller
@RequestMapping(path = "/login")
@SessionAttributes("accountId")
public class LoginController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private MessageSource messageSource;

    @PostMapping
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        HttpSession session,
                        Model model,
                        Locale locale) {
        try {
            Account account = accountService.login(email, password);
            session.setAttribute("accountId", account.getId());
            return "home";
       } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", messageSource.getMessage("login.data", null, locale));
            return "home";
       }
    }
}
