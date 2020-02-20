package com.wulei.blog.controller;

import com.wulei.blog.model.Post;
import com.wulei.blog.service.BlogServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/blog")
public class BlogController {

    @Autowired
    private BlogServices blogServices;

    @GetMapping("/name")
    public String getBlogName() {
        return blogServices.getBlogName();
    }

    @GetMapping("/visits")
    public int getVisits() {
        return blogServices.getBlogVisits();
    }

    @GetMapping("/posts")
    public List<Post> getPosts() {
        return blogServices.getPosts();
    }

    @GetMapping("/post/{title}")
    public String getPostHtml(@PathVariable String title) {
        return blogServices.getPostByName(title);
    }

    @PostMapping("/visit")
    public String postVisit() {
        blogServices.increaseVisit();
        return "success";
    }

    @PostMapping("/post")
    public String uploadFile(@RequestParam("md_file")MultipartFile file,
                             HttpServletRequest request) {
        if (!blogServices.isPasswordCorrect(request.getParameter("password")))
            return "wrong password";
        if (file.isEmpty())
            return "empty file";
        else {
            Post post = new Post();
            post.setName(request.getParameter("name"));
            post.setTitle(request.getParameter("title"));

            if (blogServices.uploadFile(file, post))
                return "success";
            return "failed";
        }
    }
}
