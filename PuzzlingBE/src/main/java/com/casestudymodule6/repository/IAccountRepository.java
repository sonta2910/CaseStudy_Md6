package com.casestudymodule6.repository;

import com.casestudymodule6.model.user.Account;
import com.casestudymodule6.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Long> {
    Account findByUsername(String username);
    Account findByUsernameAndPassword(String username, String password);
    Account findByUserEmail(String email);
    public Account findByUser(User user);
}
