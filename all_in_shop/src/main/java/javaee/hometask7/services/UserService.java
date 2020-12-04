package javaee.hometask7.services;

import javaee.hometask7.entities.Roles;
import javaee.hometask7.entities.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    Users getUserByEmail(String email);
    Users saveUser(Users user);
    Users getUserById(Long id);
    Users addUser(Users user);
    List<Users> getAllUsers();

    List<Roles> getAllRoles();
    void saveRole(Roles role);
    Roles getRole(String name);
    void addRole(Roles role);
    void deleteRole(Roles role);
    Roles getRoleById(Long id);

}
