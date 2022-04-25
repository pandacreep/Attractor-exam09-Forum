package com.pandacreep.forum.frontend;

import com.pandacreep.forum.model.topic.TopicService;
import com.pandacreep.forum.util.PropertiesService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@AllArgsConstructor
public class FrontendController {
    private final TopicService topicService;
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

    @GetMapping("/")
    public String showIndex(Model model,
                            Principal principal,
                            Pageable pageable,
                            HttpServletRequest uriBuilder) {
        if (principal == null) {
            model.addAttribute("userLogged", false);
        } else {
            model.addAttribute("userLogged", true);
        }

        var topicsDto = topicService.findAllPage(pageable);
        var uri = uriBuilder.getRequestURI();
        constructPageable(topicsDto, propertiesService.getDefaultPageSize(), model, uri);

        return "index";
    }
}
