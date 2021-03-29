package com.king.burger.accounting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDayDao extends JpaRepository<AccountDay, Long> {

}
