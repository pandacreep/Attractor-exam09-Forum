package com.pandacreep.forum.model.post;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@AllArgsConstructor
@RequestMapping("/api/comments")
public class PostRestController {
    private final PostService postService;

    @RequestMapping("/add")
    public ResponseEntity<Principal> addComment(@RequestParam String id,
                             @RequestParam String text,
                             Principal principal) {
        postService.addPostRest(id, text, principal);
        return ResponseEntity.ok().body(principal);
    }

}
