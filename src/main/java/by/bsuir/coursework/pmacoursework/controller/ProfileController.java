package by.bsuir.coursework.pmacoursework.controller;

import by.bsuir.coursework.pmacoursework.entity.Employee;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/profile")
public class ProfileController {

    /*
    ToDo:
        - Add profile page
        - Profile edit page (with form)
        - Add post mapping for profile page form submission
     */

    // @GetMapping(value = "/{employee}")
    @GetMapping
    public String displayProfile(@AuthenticationPrincipal Employee employee, Model model) {
        model.addAttribute("employee", employee);
        return "profile/profile";
    }

    // @GetMapping(value = "/{id}/edit")
    @GetMapping(value = "/edit")
    public String displayEditForm(@AuthenticationPrincipal Employee employee, Model model) {
        model.addAttribute("employee", employee);
        return "profile/profile-edit";
    }

    @PostMapping(value = "/update")
    public String updateProfile(@AuthenticationPrincipal Employee employee,
                                BindingResult bindingResult,
                                Model model) {
        return "redirect:/profile";
    }

}
