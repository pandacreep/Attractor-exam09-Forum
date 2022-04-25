package com.pandacreep.forum.model.user;

import com.pandacreep.forum.exception.ForumSuccessRegisterException;
import com.pandacreep.forum.exception.ForumUserExistException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String loginPage(Model model, Principal principal,
                            @RequestParam(required = false, defaultValue = "false") Boolean error) {
        model.addAttribute("error", error);
        if (principal == null) {
            model.addAttribute("userLogged", false);
        } else {
            model.addAttribute("userLogged", true);
        }
        return "login";
    }

    @GetMapping("register")
    public String showRegister(Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("userLogged", false);
        } else {
            model.addAttribute("userLogged", true);
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerPage(@Valid UserDtoRegister form,
                               BindingResult validationResult,
                               RedirectAttributes attributes) throws ForumUserExistException, ForumSuccessRegisterException {
        attributes.addFlashAttribute("form", form);
        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/register";
        }
        User user= userService.save(form);
        throw new ForumSuccessRegisterException(user.getEmail());
    }

    @GetMapping("/profile")
    public String showProfile(Model model, Principal principal) {
        model.addAttribute("email", principal.getName());
        User user = userService.getByEmailOrNull(principal.getName());
        model.addAttribute("user", UserDto.from(user));
        if (principal == null) {
            model.addAttribute("userLogged", false);
        } else {
            model.addAttribute("userLogged", true);
        }
        return "profile";
    }

    @GetMapping("/quit")
    public String showLogout(Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("userLogged", false);
        } else {
            model.addAttribute("userLogged", true);
        }
        return "logout-page";
    }

    @ExceptionHandler(ForumUserExistException.class)
    private String handleUserExistException(ForumUserExistException ex,
                                                Model model,
                                                Principal principal) {
        model.addAttribute("header", "Error");
        model.addAttribute("message", ex.getMessage());
        if (principal == null) {
            model.addAttribute("userLogged", false);
        } else {
            model.addAttribute("userLogged", true);
        }
        return "info";
    }

    @ExceptionHandler(ForumSuccessRegisterException.class)
    private String handleSuccessRegisterException(ForumSuccessRegisterException ex,
                                                  Model model,
                                                  Principal principal) {
        model.addAttribute("header", "Info");
        model.addAttribute("message", "Registration completed successfully");
        if (principal == null) {
            model.addAttribute("userLogged", false);
        } else {
            model.addAttribute("userLogged", true);
        }
        return "info";
    }
}
