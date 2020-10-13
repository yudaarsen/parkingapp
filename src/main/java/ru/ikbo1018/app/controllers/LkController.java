package ru.ikbo1018.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.ikbo1018.app.models.account.Account;
import ru.ikbo1018.app.models.account.Moderator;
import ru.ikbo1018.app.models.account.User;
import ru.ikbo1018.app.services.AccountService;

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

    @InitBinder("accountInfo")
    public void dataBind(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(accountValidator);
    }

    @GetMapping
    public String lk(HttpSession session, Model model){
        try {
            Integer accountId = (Integer) session.getAttribute("accountId");
            if(!model.containsAttribute("accountInfo")) {
                Account account = accountService.getAccountById(accountId);
                model.addAttribute("accountInfo", account);
            }
            return "lk/lk";
        } catch (IllegalArgumentException e) {
            session.removeAttribute("accountId");
            return "redirect:/";
        }
    }

    @PostMapping(value = "/update")
    public String updateAccount(@Validated @ModelAttribute("accountInfo") User accountInfo, BindingResult result, HttpSession session, RedirectAttributes redirectAttributes) {
        if(result.getFieldErrorCount("firstName") > 0 || result.getFieldErrorCount("lastName") > 0
                || result.getFieldErrorCount("midName") > 0) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.accountInfo", result);
            redirectAttributes.addFlashAttribute("accountInfo", accountInfo);
            return "redirect:/lk";
        }
        try {
            Integer accountId = (Integer) session.getAttribute("accountId");
            Account currentAccount = accountService.getAccountById(accountId);
            currentAccount.setFirstName(accountInfo.getFirstName());
            currentAccount.setLastName(accountInfo.getLastName());
            currentAccount.setMidName(accountInfo.getMidName());
            accountService.updateAccount(currentAccount);
            return "redirect:/lk";
        } catch (IllegalArgumentException e) {
            session.removeAttribute("accountId");
            return "redirect:/";
        }
    }
}
