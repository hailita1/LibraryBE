package com.example.demo.service.user;

import com.example.demo.model.auth.Role;
import com.example.demo.model.auth.Reader;
import com.example.demo.model.auth.UserPrinciple;
import com.example.demo.repository.IUserRepository;
import com.example.demo.service.role.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.example.demo.model.auth.RoleName.ROLE_ADMIN;
import static com.example.demo.model.auth.RoleName.ROLE_USER;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Iterable<Reader> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<Reader> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Reader save(Reader reader) {
        if (reader.getRoles() == null) {
            Role role = roleService.findByName(ROLE_USER.toString());
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            reader.setRoles(roles);
        } else {
            boolean check = false;
            for (Role role1 : reader.getRoles()) {
                if (role1.getName().equals("ROLE_USER")) {
                    check = false;
                } else {
                    check = true;
                }
                if (check == true) {
                    Role role = roleService.findByName(ROLE_ADMIN.toString());
                    Set<Role> roles = new HashSet<>();
                    roles.add(role);
                    reader.setRoles(roles);
                } else {
                    Role role = roleService.findByName(ROLE_USER.toString());
                    Set<Role> roles = new HashSet<>();
                    roles.add(role);
                    reader.setRoles(roles);
                }
            }
        }
        reader.setPassword(passwordEncoder.encode(reader.getPassword()));
        return userRepository.save(reader);
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Reader> userOptional = Optional.ofNullable(userRepository.findByEmail(username));
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return UserPrinciple.build(userOptional.get());
    }

    @Override
    public Reader findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
