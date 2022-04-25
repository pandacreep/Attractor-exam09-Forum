package com.pandacreep.forum.model.topic;

import com.pandacreep.forum.model.post.PostService;
import com.pandacreep.forum.util.PropertiesService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping
@AllArgsConstructor
public class TopicController {
    private final TopicService topicService;
    private final PostService postService;
    private final PropertiesService propertiesService;

    private static <T> void constructPageable(Page<T> list, int pageSize, Model model, String uri) {
        if (list.hasNext()) {
            model.addAttribute("nextPageLink", constructPageUri(uri, list.nextPageable().getPageNumber(), list.nextPageable().getPageSize()));
        }

        if (list.hasPrevious()) {
            model.addAttribute("prevPageLink", constructPageUri(uri, list.previousPageable().getPageNumber(), list.previousPageable().getPageSize()));
        }

        model.addAttribute("hasNext", list.hasNext());
        model.addAttribute("hasPrev", list.hasPrevious());
        model.addAttribute("items", list.getContent());
        model.addAttribute("defaultPageSize", pageSize);
    }

    private static String constructPageUri(String uri, int page, int size) {
        return String.format("%s?page=%s&size=%s", uri, page, size);
    }

    @GetMapping("/add-topic")
    public String showAddTopicPage(Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("userLogged", false);
        } else {
            model.addAttribute("userLogged", true);
        }
        if (!model.containsAttribute("form")) {
            model.addAttribute("form", new TopicDtoAddForm());
        }
        return "topic-add";
    }

    @PostMapping("/addTopic")
    public String addTopic(@Valid TopicDtoAddForm form,
                           BindingResult validationResult,
                           RedirectAttributes attributes,
                           Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("userLogged", false);
        } else {
            model.addAttribute("userLogged", true);
        }
        attributes.addFlashAttribute("form", form);
        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/add-topic";
        }
        topicService.addTopic(form, principal);
        return "redirect:/";
    }

    @GetMapping("/topics/{id}")
    public String showTopicPage(@PathVariable String id,
                                Model model,
                                Principal principal,
                                Pageable pageable,
                                HttpServletRequest uriBuilder) {
        if (principal == null) {
            model.addAttribute("userLogged", false);
        } else {
            model.addAttribute("userLogged", true);
        }
        Topic topic = topicService.findById(id);
        model.addAttribute("topic", TopicDto.from(topic));
        var posts = postService.findByTopicPage(topic, pageable);

        var uri = uriBuilder.getRequestURI();
        constructPageable(posts, propertiesService.getDefaultPageSize(), model, uri);

        return "/topic";
    }
}
