package com.casestudymodule6.service.account;

import com.casestudymodule6.model.user.Account;
import com.casestudymodule6.model.user.User;
import com.casestudymodule6.service.IGeneralService;

public interface IAccountService extends IGeneralService<Account> {
    public Account findByUsername(String username);
    public Account findByUser(User user);
}
