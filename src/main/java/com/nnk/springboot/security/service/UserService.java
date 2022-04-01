package com.nnk.springboot.security.service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> list(){return this.userRepository.findAll();}

    public Optional<User> findById(Long id){return this.userRepository.findById(id);}

    public User save(User user){
        return this.userRepository.save(user);
    }

    public User update(User newUser){
        Optional<User> userFind = this.userRepository.findById(newUser.getId());

        User userUpdated = null;

        if(userFind.isPresent()){
            User userUpdate = userFind.get();
            userUpdate.setFullname(newUser.getFullname());
            userUpdate.setEmail(newUser.getEmail());
            userUpdate.setPassword(newUser.getPassword());
            userUpdate.setRoles(newUser.getRoles());

            userUpdated = this.userRepository.save(userUpdate);
        }

        return userUpdated;
    }

    public void deleteById(Long id){
        this.userRepository.deleteById(id);
    }

    public boolean checkExistUsername(String username){
        return this.userRepository.existsByUsername(username);
    }

    public boolean checkExistEmail(String email){
        return this.userRepository.existsByEmail(email);
    }
}
