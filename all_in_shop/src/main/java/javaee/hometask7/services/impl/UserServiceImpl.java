package javaee.hometask7.services.impl;

import javaee.hometask7.entities.Roles;
import javaee.hometask7.entities.Users;
import javaee.hometask7.repositories.RolesRepository;
import javaee.hometask7.repositories.UserRepository;
import javaee.hometask7.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Users myUser = userRepository.findByEmail(s);
        if (myUser!=null){
            User secUser = new User(myUser.getEmail(), myUser.getPassword(), myUser.getRoles());
            return secUser;
        }
        throw new UsernameNotFoundException("User Not Found");
    }

    @Override
    public Users getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Users saveUser(Users user) {
        return userRepository.save(user);
    }

    @Override
    public Users getUserById(Long id) {
        return userRepository.findByIdEquals(id);
    }

    @Override
    public Users addUser(Users user) {
        return userRepository.save(user);
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<Roles> getAllRoles() {
        return rolesRepository.findAll();
    }

    @Override
    public void saveRole(Roles role) {
        rolesRepository.save(role);
    }

    @Override
    public Roles getRole(String name) {
        return rolesRepository.findByRole(name);
    }

    @Override
    public void addRole(Roles role) {
        rolesRepository.save(role);
    }

    @Override
    public void deleteRole(Roles role) {
        rolesRepository.delete(role);
    }

    @Override
    public Roles getRoleById(Long id) {
        return rolesRepository.findByIdEquals(id);
    }
}
