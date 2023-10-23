package org.example.model;

import java.io.*;
import java.util.TreeMap;

public class FileUtil {
    public static String generateFileContent(TreeMap<String, Integer> wordCounts) {
        StringBuilder content = new StringBuilder();
        for (String key : wordCounts.keySet()) {
            content.append(key).append(" ").append(wordCounts.get(key)).append("\n");
        }
        return content.toString();
    }

    public static String readFile(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        return readFromInputStream(reader);
    }

    public static String readFromInputStream(BufferedReader br) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        catch (IOException e) {
            System.out.println("Error while attempting to read file.");
        }
        return resultStringBuilder.toString();
    }
    public static File[] getFiles(String path) {
        File folder = new File(path);
        if (folder.isDirectory())
            return folder.listFiles();
        else
            return null;
    }

    public static boolean isTxtFile(File file) {
        return file.isFile() && file.getName().split("\\.")[1].equals("txt");
    }

    public static void writeFile(String content, File file) {
        try {
            FileWriter myWriter = new FileWriter(file.getPath());
            myWriter.write(content);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
