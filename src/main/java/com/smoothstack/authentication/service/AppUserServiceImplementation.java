package com.smoothstack.authentication.service;

import com.smoothstack.authentication.model.AppUser;
import com.smoothstack.authentication.model.Role;
import com.smoothstack.authentication.repository.AppUserRepository;
import com.smoothstack.authentication.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AppUserServiceImplementation implements  AppUserService, UserDetailsService {

    private final  AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findAppUserByUsername(username);
        if(user == null){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        }else{
            log.info("User with username {} was found on the database",username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role->authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new User(user.getUsername(),user.getPassword(),authorities);
    }

    @Override
    public AppUser saveAppUser(AppUser appUser) {
        log.info("Saving appUser {} to database",appUser.getUsername() );
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return appUserRepository.save(appUser);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving role {} to database",role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToAppUser(String username, String roleName) {
        log.info("Adding role {} to appUser {}",roleName,username);
        AppUser appUser = appUserRepository.findAppUserByUsername(username);
        Role role = roleRepository.findRoleByName(roleName);
        appUser.getRoles().add(role);
    }

    @Override
    public AppUser getAppUser(String username) {
        log.info("Fetching appUser {} from the database",username);
        return appUserRepository.findAppUserByUsername(username);
    }

    @Override
    public List<AppUser> getAllAppUsers() {
        log.info("Fetching all appUsers from the database");
        return appUserRepository.findAll();
    }
    
}
