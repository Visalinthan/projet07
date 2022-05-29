package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.ERole;
import com.nnk.springboot.domain.Role;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.payload.request.SignupRequest;
import com.nnk.springboot.payload.request.UpdateRequest;
import com.nnk.springboot.security.authentication.AuthenticationFacadeImpl;
import com.nnk.springboot.security.service.RoleService;
import com.nnk.springboot.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Set;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private AuthenticationFacadeImpl authenticationFacade;

    /**
     * Récupère la liste des utilisateur avec la méthode list dans la class userService
     * Affiche la page contenant la liste des utilisateurs
     * @return une liste d'utilisateur
     */
    @RequestMapping("/user/list")
    public String homeUser(Model model)
    {
        model.addAttribute("users", userService.list());
        return "user/list";
    }

    /**
     * DTO pour enregistrer un utilisateur
     * @return SignupRequest
     */
    @ModelAttribute("user")
    public SignupRequest signupRequest() {
        return new SignupRequest();
    }

    /**
     * Récupère la liste des utilisateur avec la méthode list dans la class userService
     * @return une liste d'utilisateur
     */
    @RequestMapping(method = RequestMethod.GET, value = "/user/add")
    public String addUser() {
        return "user/add";
    }

    /**
     * Enregitsre un utilisateur avec la méthode save dans la class userService si validation est correcte
     * @param signUpRequest
     * @param result
     * @return une page
     */
    @PostMapping("/user/add")
    public String validateUser(@Valid @ModelAttribute("user") SignupRequest signUpRequest, BindingResult result) {
        Authentication authentication = authenticationFacade.getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        boolean hasAdminRole = authorities.stream().filter(r->r.getAuthority().equals(ERole.ROLE_ADMIN.name())).count()>0;

        if (!result.hasErrors()) {
            User user = new User(signUpRequest.getFullname(), signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()), signUpRequest.getEmail());
            Set<String> strRoles = signUpRequest.getRole();
            Set<Role> roles = roleService.checkRoles(strRoles);
            user.setRoles(roles);
            userService.save(user);
            if (hasAdminRole) {
                return "/user/list";
            }else {
                return "/login";
            }
        }
        return "/user/add";
    }

    /**
     * Affiche la page contenant l' utilisateur à modifier
     * @param id
     * @param model
     * @return page user/update
     */
    @GetMapping("/user/update/{id}")
    public String showUpdateFormUser(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        user.setPassword("");
        model.addAttribute("user", user);
        return "user/update";
    }

    /**
     * Modifie un utilisateur avec la méthode update dans la class userService si validation est correcte
     * @param id
     * @param user
     * @param result
     * @param model
     * @return une page
     */
    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Long id, UpdateRequest user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/update";
        }
        userService.update(id,user);
        model.addAttribute("users", userService.list());
        return "redirect:/user/list";
    }

    /**
     * Supprime un utilisateur avec la méthode deleteById dans la class userService
     * @param id
     * @param model
     * @return page user/list
     */
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userService.deleteById(user.getId());
        model.addAttribute("users", userService.list());
        return "redirect:/user/list";
    }
}
