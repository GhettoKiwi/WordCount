package org.example.controller;

import com.sun.source.tree.Tree;
import org.example.model.FileUtil;
import org.example.model.WordCounter;

import java.io.*;
import java.util.TreeMap;

public class Controller {
    public void performWordCountWithExclusionsAndGenerateFiles(String path) throws IOException {
        File[] files = FileUtil.getFiles(path);
        if (files == null)
            System.out.println("Folder " + path + " was empty!");
        else {
            TreeMap<String, Integer> countedWords;
            TreeMap<String, Integer> exclusionCount;
            StringBuilder fileContents = new StringBuilder();
            String[] exclusions = new String[0];
            for (File file : files) {
                if (FileUtil.isTxtFile(file) && !file.getName().toLowerCase().contains("exclude.txt")) {
                    fileContents.append(FileUtil.readFile(file));
                }
                if (file.getName().toLowerCase().contains("exclude.txt")) {
                    exclusions = FileUtil.readFile(file).split("\n");
                }
            }
            countedWords = WordCounter.count(fileContents.toString());
            if (countedWords == null) {
                System.out.println("All files were empty!");
            }
            if (exclusions.length > 0) {
                exclusionCount = WordCounter.getExclusionCounts(countedWords, exclusions);
                String content = FileUtil.generateFileContent(exclusionCount);
                File exclusionCountsFile = new File(path+"/excludeCount.txt");
                FileUtil.writeFile(content, exclusionCountsFile);
            }
            countedWords = WordCounter.removeExclusionCounts(countedWords, exclusions);
            TreeMap<Character, String> sortedCounts = sortWordsOnCharacter(countedWords);
            writeWordCountFiles(sortedCounts, path);
        }
    }

    private TreeMap<Character, String> sortWordsOnCharacter(TreeMap<String, Integer> countedWords) {
        char character = 'a';
        TreeMap<Character, String> sortedCounts = new TreeMap<>();
        for (String key : countedWords.keySet()) {
            if (character != key.charAt(0)) {
                character = key.charAt(0);
            }
            String oldContent = "";
            if (sortedCounts.get(character) != null) {
                oldContent = sortedCounts.get(character);
            }
            sortedCounts.put(character, oldContent + key + " " + countedWords.get(key) + "\n");
        }
        return sortedCounts;
    }

    private void writeWordCountFiles(TreeMap<Character, String> sortedCounts, String parentFolder) {
        for (Character key : sortedCounts.keySet()) {
            File file = new File(parentFolder+"/file_"+key+".txt");
            FileUtil.writeFile(sortedCounts.get(key), file);
        }
    }
}
