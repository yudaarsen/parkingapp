package ru.ikbo1018.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.ikbo1018.app.models.account.Account;
import ru.ikbo1018.app.services.AccountService;
import ru.ikbo1018.app.validators.AccountValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/lk")
@SessionAttributes("accountId")
public class LkController {

    @Autowired
    private AccountService accountService;

    @Autowired
    @Qualifier("accountValidator")
    private Validator accountValidator;

    @InitBinder
    public void dataBind(WebDataBinder webDataBinder) {
    }

    @GetMapping
    public String lk(HttpSession session, Model model){
        Integer accountId = (Integer)session.getAttribute("accountId");
        if(accountId == null)
            return "redirect:/";
        try {
            Account account = accountService.getAccountById(accountId);
            model.addAttribute("accountInfo", account);
            return "lk/lk";
        } catch (IllegalArgumentException e) {
            session.removeAttribute("accountId");
            return "redirect:/";
        }
    }

    @PostMapping(value = "/update")
    public String updateAccount(@ModelAttribute("accountInfo") Account account, HttpSession session,
                                RedirectAttributes redirectAttributes) {
        try {
            Integer accountId = (Integer) session.getAttribute("accountId");
            Account currentAccount = accountService.getAccountById(accountId);
            currentAccount.setFirstName(account.getFirstName());
            currentAccount.setLastName(account.getLastName());
            currentAccount.setMidName(account.getMidName());
            accountService.updateAccount(currentAccount);
            return "redirect:/lk";
        } catch (IllegalArgumentException e) {
            session.removeAttribute("accountId");
            return "redirect:/";
        }
    }
}
