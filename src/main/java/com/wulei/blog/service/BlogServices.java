package com.wulei.blog.service;

import com.wulei.blog.model.GlobalInfo;
import com.wulei.blog.model.Post;
import com.wulei.blog.util.CssReader;
import com.wulei.blog.util.JsonManager;
import com.wulei.blog.util.MarkdownParser;
import com.wulei.blog.util.MdFileReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BlogServices {
    private GlobalInfo blogInfo;

    private Map<String, Post> postMap;

    private String jsonPath;

    private String cssPath;

    private String fileSavePath;

    private String password;

    public BlogServices(@Value("${blog.configuration_path}") String configPath,
                        @Value("${blog.css_path}") String css,
                        @Value("${blog.file_save_path}") String filePath,
                        @Value("${blog.upload_passwd}") String pd) {
        jsonPath = configPath;
        cssPath = css;
        fileSavePath = filePath;
        password = pd;
        blogInfo = JsonManager.jsonFileToBean(configPath);
        postMap = new HashMap<>();
        assert blogInfo != null;
        for (Post post : blogInfo.getPosts())
            postMap.put(post.getName(), post);
    }

    public String getBlogName() {
        return blogInfo.getBlogName();
    }

    public int getBlogVisits() {
        return blogInfo.getNumsOfVisits();
    }

    public List<Post> getPosts() {
        return blogInfo.getPosts();
    }

    public String getPostByName(String postName) {
        if (postMap.containsKey(postName)) {
            Post post = postMap.get(postName);
            return MarkdownParser.mdToHTML(MdFileReader.readContent(post));
        }
        return null;
    }

    public void increaseVisit() {
        blogInfo.setNumsOfVisits(blogInfo.getNumsOfVisits() + 1);
        JsonManager.writeBeanToJsonFile(blogInfo, jsonPath);
    }

    public boolean uploadFile(MultipartFile file, Post post) {
        try {
            String filePath = fileSavePath + "\\" + file.getOriginalFilename();
            Files.copy(file.getInputStream(), Path.of(filePath));
            post.setSavePath(filePath);
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        blogInfo.getPosts().add(0, post);
        JsonManager.writeBeanToJsonFile(blogInfo, jsonPath);
        postMap.put(post.getName(), post);
        return true;
    }

    public boolean isPasswordCorrect(String p) {
        return p.equals(password);
    }

}
