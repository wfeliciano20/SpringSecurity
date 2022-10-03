package com.smoothstack.authentication.service;

import com.smoothstack.authentication.model.AppUser;
import com.smoothstack.authentication.model.Role;

import java.util.List;

public interface AppUserService {
    AppUser saveAppUser(AppUser appUser);
    Role saveRole(Role role);
    void addRoleToAppUser(String username,String roleName);
    AppUser getAppUser(String username);
    List<AppUser> getAllAppUsers();
}
