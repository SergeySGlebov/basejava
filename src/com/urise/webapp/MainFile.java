package com.urise.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) {

        File dir = new File(".");
        printDir(dir, 0);
//        System.out.println(dir.isDirectory());
//        String[] list = dir.list();
//        if (list != null) {
//            for (String name : list) {
//                System.out.println(name);
//            }
//        }


    }
//        String filePath = ".\\.gitignore";
//        File file = new File(filePath);
//        try {
//            System.out.println(file.getCanonicalPath());
//        } catch (IOException e) {
//            throw new RuntimeException("Error", e);
//        }
//        File dir = new File("./src/com/urise/webapp");
//        System.out.println(dir.isDirectory());
//        String[] list = dir.list();
//        if (list != null) {
//            for (String name : list) {
//                System.out.println(name);
//            }
//        }
//        try (FileInputStream fis = new FileInputStream(filePath)){
//            System.out.println(fis.read());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    private static void printDir(File dir, int level) {
        File[] list = dir.listFiles();
        for (File file : list) {
            for (int i = 0; i < level; i++) {
                System.out.print("  ");
            }
            System.out.println(file.getName());
            if (file.isDirectory()) {
                printDir(file, level + 1);
            }
        }


    }
}
