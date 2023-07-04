package com.casestudymodule6.security;

import com.casestudymodule6.model.user.Account;
import com.casestudymodule6.service.account.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class JPAUserDetailsService implements UserDetailsService {
    private final IAccountService accountService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account=accountService.findByUsername(username);
        if(account==null)throw new UsernameNotFoundException("Not Found");
        return new AccountUserDetails(account);
    }
}
