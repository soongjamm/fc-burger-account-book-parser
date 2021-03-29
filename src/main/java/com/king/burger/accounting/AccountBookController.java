package com.king.burger.accounting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AccountBookController {

    private AccountDayDao accountDayDao;

    @Autowired
    public void setAccountDayDao(AccountDayDao accountDayDao) {
        this.accountDayDao = accountDayDao;
    }

    @GetMapping("/accountBook")
    public String showAll(Model model) {
        List<AccountDay> all = accountDayDao.findAll();
        model.addAttribute("accountBook", all);
        return "accountBook";
    }
}
