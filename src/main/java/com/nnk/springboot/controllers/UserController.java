package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Role;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.payload.request.SignupRequest;
import com.nnk.springboot.payload.response.MessageResponse;
import com.nnk.springboot.security.service.RoleService;
import com.nnk.springboot.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    PasswordEncoder encoder;

    @RequestMapping("/user/list")
    public String home(Model model)
    {
        model.addAttribute("users", userService.list());
        return "user/list";
    }

    @ModelAttribute("user")
    public SignupRequest signupRequest() {
        return new SignupRequest();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/add")
    public String addUser() {
        return "user/add";
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userService.checkExistUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        if (userService.checkExistEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        User user = new User(signUpRequest.getFullname(), signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()), signUpRequest.getEmail());
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = roleService.checkRoles(strRoles);
        user.setRoles(roles);

        userService.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/user/add")
    public String validate(@Valid @ModelAttribute("user") SignupRequest signUpRequest, BindingResult result) {
        if (!result.hasErrors()) {
            User user = new User(signUpRequest.getFullname(), signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()), signUpRequest.getEmail());
            Set<String> strRoles = signUpRequest.getRole();
            Set<Role> roles = roleService.checkRoles(strRoles);
            user.setRoles(roles);
            userService.save(user);
        }
        return "/login";
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        user.setPassword("");
        model.addAttribute("user", user);
        return "user/update";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Long id, @Valid User user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/update";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setId(id);
        userService.save(user);
        model.addAttribute("users", userService.list());
        return "redirect:/user/list";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userService.deleteById(user.getId());
        model.addAttribute("users", userService.list());
        return "redirect:/user/list";
    }
}
