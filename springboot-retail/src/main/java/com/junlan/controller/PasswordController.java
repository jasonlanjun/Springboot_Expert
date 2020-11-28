package com.junlan.controller;

import com.junlan.WebSecurityConfig;
import com.junlan.domain.ChangePasswordEntity;
import com.junlan.domain.UserEntity;
import com.junlan.service.UserServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.util.UUID;

@Controller
public class PasswordController {

    @Autowired
    DataSource dataSource;
    @Autowired
    private UserServiceInt userService;
    @Autowired
    private JavaMailSender javaMailService;
    @Autowired
    private WebSecurityConfig webSecurity;
    @Autowired
    private HttpServletRequest request;

    @GetMapping("/forgotpassword")
    public String displayForgotPassword(Model model) {
        model.addAttribute("forgotpassword", new UserEntity());
        return "ForgotPassword";
    }

    @PostMapping("/forgotpassword")
    public String saveForgotPassword(UserEntity userData, BindingResult result, Model model) {

        UserEntity user = userService.findUserByEmail(userData.getEmail());
        if (user == null) {
            return "redirect:/forgotpassword?emailNotFound";
        }

        user.setResetToken(UUID.randomUUID().toString());
        userService.saveUser(user);
        String appUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

        // Email message
        SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
        passwordResetEmail.setTo(userData.getEmail());
        passwordResetEmail.setSubject("Password Reset Request");
        passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl + "/resetpassword?token="
                + user.getResetToken());
        javaMailService.send(passwordResetEmail);

        return "redirect:/forgotpassword?success";
    }

    @GetMapping("/resetpassword")
    public String diplayResetPassword(@RequestParam("token") String token, Model model) {
        UserEntity user = userService.findUserByResetToken(token);
        if (user != null) { // Token found in DB
            model.addAttribute("resetpassword", user);
        } else {
            return "Error";
        }
        return "ResetPassword";
    }

    @PostMapping("/resetpassword")
    public String saveResetPassword(UserEntity resetUser, BindingResult result) {
        UserEntity setNewUser = userService.findUserByResetToken(resetUser.getResetToken());
        setNewUser.setPassword(webSecurity.passwordEncoder().encode(resetUser.getPassword()));
        setNewUser.setResetToken(null);
        userService.saveUser(setNewUser);
        return "redirect:/login";
    }

    @GetMapping("/admin/changepassword")
    public String changePasswordForm(ModelMap model) {
        model.addAttribute("changepassword", new ChangePasswordEntity());
        return "ChangePasswordForm";
    }

    @PostMapping("/admin/changepassword")
    public String saveChangePassword(ChangePasswordEntity changePassword) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity existingUser = userService.getUserById(userService.findIdByUsername(user.getUsername()));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean isPasswordMatched = passwordEncoder.matches(changePassword.getOldPassword(),
                existingUser.getPassword());

        if (!isPasswordMatched) {
            return "redirect:/admin/changepassword?invalidOldPassword";
        } else if (!changePassword.getNewPassword().equals(changePassword.getConfirmPassword())) {
            return "redirect:/admin/changepassword?passwordNotMatched";
        }
        existingUser.setPassword(webSecurity.passwordEncoder().encode(changePassword.getNewPassword()));
        userService.saveUser(existingUser);
        return "redirect:/admin/dashboard";
    }
}
