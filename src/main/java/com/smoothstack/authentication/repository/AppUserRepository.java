package com.smoothstack.authentication.repository;

import com.smoothstack.authentication.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {

    AppUser findAppUserByUsername(String username);
}
