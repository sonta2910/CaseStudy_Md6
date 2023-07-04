package com.casestudymodule6.repository;

import com.casestudymodule6.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User,Long>
{
    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByPhone(String phone);
}
