package com.casestudymodule6.service.user;

import com.casestudymodule6.model.user.User;
import com.casestudymodule6.service.IGeneralService;

import java.util.Optional;


public interface IUserService extends IGeneralService<User>
{

    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByPhone(String phone);
}
