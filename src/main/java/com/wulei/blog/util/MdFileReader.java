package com.wulei.blog.util;

import com.wulei.blog.model.Post;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MdFileReader {
    public static String readContent (Post post) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(post.getSavePath()));) {
            String line;
            while ((line = br.readLine()) != null)
                sb.append(line).append('\n');
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return sb.toString();
    }

}
