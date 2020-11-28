package com.junlan.controller;

import com.junlan.WebSecurityConfig;
import com.junlan.domain.CityEntity;
import com.junlan.domain.StateEntity;
import com.junlan.domain.UserEntity;
import com.junlan.domain.UserRolesEntity;
import com.junlan.service.CityServiceInt;
import com.junlan.service.StateServiceInt;
import com.junlan.service.UserRolesServiceInt;
import com.junlan.service.UserServiceInt;
import com.junlan.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.sql.DataSource;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    DataSource dataSource;
    @Autowired
    private UserServiceInt userService;
    @Autowired
    private UserRolesServiceInt roleService;
    @Autowired
    private JavaMailSender javaMailService;
    @Autowired
    private WebSecurityConfig webSecurity;
    @Autowired
    private StateServiceInt stateServiceInt;
    @Autowired
    private CityServiceInt cityServiceInt;
    @Autowired
    private CommonUtils commonUtils;

    @GetMapping("/newuser")
    public String newUser(Model model) {
        model.addAttribute("user", new UserEntity());
        return "UserForm";
    }

    @PostMapping("/saveuser")
    public String saveUser(UserEntity user) {
        if (!commonUtils.isLicenseValid(user.getLicenseNo())) {
            return "redirect:/newuser?invalidLicense";
        } else if (!commonUtils.isDateValid(user.getDOB())) {
            return "redirect:/newuser?invalidDob";
        } else if (!commonUtils.isContactValid(user.getContactNo())) {
            return "redirect:/newuser?invalidContact";
        } else if (!((userService.findUserByEmail(user.getEmail())) == null)) {
            return "redirect:/newuser?emailExist";
        }

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("User Registration");
        mailMessage.setText("Hello " + user.getUserName() + "\nYour registration is successfull");
        javaMailService.send(mailMessage);
        user.setPassword(webSecurity.passwordEncoder().encode(user.getPassword()));
        user.setCreatedDateTime(commonUtils.getTime());
        userService.saveUser(user);

        boolean isUserNew = roleService.findRoleByUser(user.getUserName()) == null;
        if (isUserNew) {
            UserRolesEntity newRole = new UserRolesEntity();
            newRole.setUserName(user.getUserName());
            newRole.setUserRole(user.getUserRole());
            roleService.saveUserRole(newRole);
        } else {
            UserRolesEntity existUserRole = roleService.findRoleByUser(user.getUserName());
            existUserRole.setUserRole(user.getUserRole());
            roleService.saveUserRole(existUserRole);
        }
        return "redirect:/login?registered";
    }

    @GetMapping("/admin/userpermission/{id}")
    public String userPermission(@PathVariable Long id, Model model) {
        UserEntity existUser = userService.getUserById(id);
        UserRolesEntity existUserRole = roleService.findRoleByUser(existUser.getUserName());
        existUser.setUserRole(existUserRole.getUserRole());
        model.addAttribute("user", existUser);
        return "UserPermissionForm";
    }

    @PostMapping("/admin/userpermission")
    public String savePermission(UserEntity user) {
        UserRolesEntity existUserRole = roleService.findRoleByUser(user.getUserName());
        existUserRole.setUserRole(user.getUserRole());
        roleService.saveUserRole(existUserRole);
        userService.updateUserRole(user.getId(), user.getUserRole());
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/updateuserdetails")
    public String updateUser(ModelMap model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userService.findIdByUsername(user.getUsername());
        List<StateEntity> stateList = stateServiceInt.findAll();
        List<CityEntity> cityList = cityServiceInt.findAll();
        UserEntity existUser = userService.getUserById(userId);
        UserRolesEntity existUserRole = roleService.findRoleByUser(existUser.getUserName());
        existUser.setUserRole(existUserRole.getUserRole());
        model.addAttribute("user", existUser);
        model.addAttribute("stateList", stateList);
        model.addAttribute("cityList", cityList);
        return "UserUpdateForm";
    }

    @PostMapping("/admin/updateuserdetails")
    public String updateUserDetails(UserEntity updatedUser) {
        Long id = updatedUser.getId();

        if (!commonUtils.isContactValid(updatedUser.getContactNo())) {
            return "redirect:/admin/updateuserdetails?invalidContact";
        }
        userService.updateUserDetails(id, updatedUser.getFirstName(), updatedUser.getLastName(), updatedUser.getEmail(),
                updatedUser.getContactNo(), updatedUser.getCountry(), updatedUser.getState(), updatedUser.getCity(),
                updatedUser.getStreet(), updatedUser.getNationality(), updatedUser.getZipcode());

        return "redirect:/admin/dashboard";
    }

}
