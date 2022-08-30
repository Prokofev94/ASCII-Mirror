package asciimirror;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        System.out.println("Input the file path:");
        String filePath = scanner.nextLine();
        File file = new File(filePath);
        if (file.exists() && !file.isDirectory()) {
            int maxLength = 0;
            try (Scanner sc = new Scanner(file)) {
                while (sc.hasNext()) {
                    String line = sc.nextLine();
                    if (line.length() > maxLength) {
                        maxLength = line.length();
                    }
                    list.add(line);
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found!");
            }
            for (String line : list) {
                int spaces = maxLength - line.length();
                line = line + " ".repeat(spaces);
                String mirror = new StringBuilder(line).reverse().toString();
                mirror = mirror.replaceAll("\\(", "\n")
                        .replaceAll("\\)", "(")
                        .replaceAll("\n", ")")
                        .replaceAll("<", "\n")
                        .replaceAll(">", "<")
                        .replaceAll("\n", ">")
                        .replaceAll("\\\\", "\n")
                        .replaceAll("/", "\\\\")
                        .replaceAll("\n", "/")
                        .replaceAll("\\[", "\n")
                        .replaceAll("]", "[")
                        .replaceAll("\n", "]")
                        .replaceAll("\\{", "\n")
                        .replaceAll("}", "{")
                        .replaceAll("\n", "}");
                System.out.println(line + " | " + mirror);
            }
        } else {
            System.out.println("File not found!");
        }
    }
}