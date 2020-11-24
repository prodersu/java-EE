package javaee.hometask7.services;

import javaee.hometask7.entities.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    Users getUserByEmail(String email);
}
