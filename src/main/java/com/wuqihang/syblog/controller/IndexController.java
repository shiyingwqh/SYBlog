package com.wuqihang.syblog.controller;

import com.wuqihang.syblog.pojo.Account;
import com.wuqihang.syblog.services.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Wuqihang
 */
@Controller
public class IndexController {
    private final AccountService accountService;


    public IndexController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping({"/", "/index"})
    String getId(HttpServletResponse response, Model model) throws IOException {
        Account account = accountService.getAllAccount().get(0);
        account = accountService.copyNullUserAccount(account);
        if (account == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        model.addAttribute("account", account);
        return "index";
    }


}
