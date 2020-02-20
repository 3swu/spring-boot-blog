package com.wulei.blog.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CssReader {
    public static String readCss(String cssPath) {
        StringBuilder sb = new StringBuilder();
        sb.append("<style type=\"text/css\">\n");
        try (BufferedReader br = new BufferedReader(new FileReader(cssPath));) {
            String line;
            while ((line = br.readLine()) != null)
                sb.append(line).append('\n');
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        sb.append("\n</style>\n");
        return sb.toString();
    }
}
