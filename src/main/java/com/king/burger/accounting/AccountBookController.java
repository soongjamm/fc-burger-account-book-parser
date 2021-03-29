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
        if (all .size() == 0) {
            return "redirect:/";
        }

        // 잔액 설정
        all.get(0).setBalance(all.get(0).sum());
        for (int i = 1; i < all.size(); i++) {
            all.get(i).setBalance(all.get(i-1).getBalance() + all.get(i).sum());
        }

        model.addAttribute("accountBook", all);
        return "accountBook";
    }

}
