package org.example.model;

import java.util.TreeMap;

public class WordCounter {

    public static TreeMap<String, Integer> count(String content) {
        if (content.isEmpty()) {
            return null;
        }
        String[] words = content.split("\\W+");
        TreeMap<String, Integer> countedWords = new TreeMap<>();
        for (String word : words) {
            word = word.toLowerCase();
            Integer value = 0;
            if (countedWords.containsKey(word)) {
                value = countedWords.get(word);
            }
                countedWords.put(word.toLowerCase(), value+1);
        }
        return countedWords;
    }

    public static TreeMap<String, Integer> getExclusionCounts(TreeMap<String, Integer> countedWords, String[] exclusions) {
        if (exclusions.length == 0) {
            return null;
        }
        TreeMap<String, Integer> excludedWordCounts= new TreeMap<>();
        for (String exclusion : exclusions) {
            if (countedWords.containsKey(exclusion)) {
                excludedWordCounts.put(exclusion, countedWords.get(exclusion));
            }
        }
        return excludedWordCounts;
    }
    public static TreeMap<String, Integer> removeExclusionCounts(TreeMap<String, Integer> countedWords, String[] exclusions) {
        if (countedWords.isEmpty()) {
            return null;
        }
        for (String exclusion : exclusions) {
            countedWords.remove(exclusion);
        }
        return countedWords;
    }
}
