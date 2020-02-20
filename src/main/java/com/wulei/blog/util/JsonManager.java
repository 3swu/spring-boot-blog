package com.wulei.blog.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wulei.blog.model.GlobalInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JsonManager {
    public static GlobalInfo jsonFileToBean(String jsonFilePath) {
        StringBuilder sb = new StringBuilder();
        ObjectMapper objectMapper = new ObjectMapper();
        try (BufferedReader br = new BufferedReader(new FileReader(jsonFilePath))) {
            String line;
            while ((line = br.readLine()) != null)
                sb.append(line).append('\n');

            return objectMapper.readValue(sb.toString(), GlobalInfo.class);
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean writeBeanToJsonFile(GlobalInfo globalInfo, String jsonFilePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(jsonFilePath), globalInfo);
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
