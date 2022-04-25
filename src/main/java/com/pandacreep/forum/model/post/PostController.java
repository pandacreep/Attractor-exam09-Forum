package com.pandacreep.forum.model.post;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@AllArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/addPost")
    public String addPost(@Valid PostDtoAddPost form,
                          BindingResult validationResult,
                          RedirectAttributes attributes,
                          Principal principal) {
        attributes.addFlashAttribute("form", form);
        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/topics/" + form.getTopicId();
        }
        postService.addPost(form, principal);
        return "redirect:/topics/" + form.getTopicId();
    }
}
