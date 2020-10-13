package ru.ikbo1018.app.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import ru.ikbo1018.app.models.account.Account;
import ru.ikbo1018.app.services.AccountService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ModerateInterceptor implements HandlerInterceptor {

    @Autowired
    private AccountService accountService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Integer accountId = (Integer) httpServletRequest.getSession().getAttribute("accountId");
        if(accountId == null) {
            httpServletResponse.sendRedirect("/");
            return false;
        }
        Account account = accountService.getAccountById(accountId);
        if(!account.moderate())
            httpServletResponse.sendRedirect("/");
        return account.moderate();
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
