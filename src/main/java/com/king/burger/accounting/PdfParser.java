package com.king.burger.accounting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PdfParser {

    private AccountDayDao accountDayDao;

    @Autowired
    public void setAccountDayDao(AccountDayDao accountDayDao) {
        this.accountDayDao = accountDayDao;
    }

    public void parse(String entire) {
        if (!entire.startsWith("축구공금")) {
            throw new IllegalArgumentException("축구공금 파일 아님");
        }

        // PDF에 있는 모든 문자를 줄별로 나누고 계속 리스트에 추가하다가
        // 중간에 '=' 로 시작하는 행을 만나면 거기까지 한 묶음으로 해서 AccountDay를 처리한다.
        String[] split = entire.split("\n");
        ArrayList<String> entireByLine = new ArrayList<>();
        for (int i = 1; i < split.length; i++) {
            entireByLine.add(split[i]);
            if (split[i].startsWith(AccountDay.TITLE_DELIMITER)) {
                AccountDay accountDay = new AccountDay(entireByLine.toArray(new String[0]));
                entireByLine.clear();
                accountDayDao.save(accountDay);
            }
        }
    }

    public List<AccountDay> getAll() {
        return accountDayDao.findAll();
    }
}
