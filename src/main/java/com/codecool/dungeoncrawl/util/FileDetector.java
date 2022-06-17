package com.codecool.dungeoncrawl.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileDetector {
    public static List<String> getAvailableFileNamesInResources() {
        List<String> list = new ArrayList<>();
        // magic
        File file = new File("src/main/resources/levels");
        String[] listOfFileNames = file.list();
        for (String fileName : listOfFileNames) {
            list.add("/levels/" + fileName);
        }
        return list.stream().sorted().collect(Collectors.toList());
    }
}
