package com.bynk.search;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TextSearcher {

    public static void main(String[] args) {
        

        final File indexableDirectory = new File("C:/Users/pratibha.devoor/Downloads/Search");
        int fileCount=indexableDirectory.list().length;
        System.out.println(fileCount + " files read in the directory");
        WordIndexer indexer = new WordIndexer();

        Map<String, List<FileWordCounter>> index = null;
        try {
            index = indexer.index(Files.list(indexableDirectory.toPath()));
        } catch (IOException e) {
            System.out.println("Loading files failed");
            e.printStackTrace();
            System.exit(1);
        }

        Scanner keyboard = new Scanner(System.in);
        while (true) {
            System.out.print("search> ");
            final String line = keyboard.nextLine();

            if (":quit".equals(line)) {
                System.out.println("Bye!");
                System.exit(0);
            }

            String[] words = line.split(" ");
            int searchWordCount = words.length;
            Map<String, Long> searchWords = Arrays
                    .stream(words)
                    .map(TextSearcher::normalizeWord)
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

            Map<String, Long> result = RankCalculator.calculate(index, searchWords, searchWordCount);

            printResult(result);
        }
    }

    private static void printResult(Map<String, Long> result) {
        if (result.isEmpty()) {
            System.out.println("no matches found");
        } else {
            result.entrySet().forEach(e -> {
                System.out.println(e.getKey() + " : " + e.getValue() + "%");
            });
        }
    }

    static String normalizeWord(String word) {
        return word.replaceAll("[^\\p{IsAlphabetic}]", "").toLowerCase();
    }
}