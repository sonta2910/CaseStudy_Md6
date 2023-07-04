package com.casestudymodule6.service.account;

import com.casestudymodule6.model.user.Account;
import com.casestudymodule6.model.user.Role;
import com.casestudymodule6.model.user.User;
import com.casestudymodule6.repository.IAccountRepository;
import com.casestudymodule6.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private IAccountRepository iAccountRepository;
    @Override
    public Iterable<Account> findAll() {
        return iAccountRepository.findAll();
    }

    @Override
    public Optional<Account> findById(Long id) {
        return iAccountRepository.findById(id);
    }

    @Override
    public Account save(Account account) {
        return iAccountRepository.save(account);

    }

    public Account findByUsername(String username) {
        return iAccountRepository.findByUsername(username);
    }

    @Override
    public Account findByUser(User user) {
        return iAccountRepository.findByUser(user);
    }

    @Override
    public void remove(Long id) {
        iAccountRepository.deleteById(id);
    }

    public Account register(Account account) {
        String username = account.getUsername();
        String email = account.getUser().getEmail();

        if (!isUsernameExists(username) && !isEmailExists(email) && account.getRole() != null) {
            save(account);
            Account savedAccount = findByUsername(username);
            if (account.getRole().equals(Role.RoleType.USER)) {
                iAccountRepository.save(savedAccount);
            }
            return account;
        }
        return null;
    }

    public Account login(Long id, String username, String password, User user) {
        Account account = findByUsername(username);
        if (account != null && account.getPassword().equals(password)) {
            return account;
        }
        return null;
    }

    public boolean isUsernameExists(String username) {
        return iAccountRepository.findByUsername(username) != null;
    }

    public boolean isEmailExists(String email) {
        return iAccountRepository.findByUserEmail(email) != null;
    }
}