package files;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;


public class TopNWords {

    /**
     * Finds the top n words in a text file. File is required to be a standard text file without
     * needing character translation.
     *
     * @param fileScanner The prepared scanner to read from
     * @param n The number of words to find
     * @return Array of the top n words in the file
     */
    public String[] findTopNWords(Scanner fileScanner, int n) throws FileNotFoundException
    {
        if (fileScanner == null || n <= 0) {
            throw new IllegalArgumentException();
        }
        Map<String,Integer> wordCounts = countWords(fileScanner);
        return findTopNWords(wordCounts, n);
    }

    /**
     * Counts the words in the file. A word is defined as anything that doesn't include whitespace.
     * @param fileScanner The file
     * @return A created map of word to count
     */
    Map<String, Integer> countWords(Scanner fileScanner)
    {
        HashMap<String, Integer> wordCounts = new HashMap<>();

        while (fileScanner.hasNext())
        {
            String word = fileScanner.next();
            int currentCount = wordCounts.getOrDefault(word, 0);
            wordCounts.put(word, ++currentCount);
        }

        return wordCounts;
    }

    class PriorityWordCount implements Comparable<PriorityWordCount>
    {
        String word;
        int count;

        PriorityWordCount(String word, int count)
        {
            this.word = word;
            this.count = count;
        }

        @Override
        public int compareTo(@SuppressWarnings("NullableProblems") PriorityWordCount o)
        {
            return Integer.compare(o.count, count);
        }
    }

    String[] findTopNWords(Map<String,Integer> wordCounts, int n)
    {

        PriorityQueue<PriorityWordCount> q = new PriorityQueue<>(n);

        for (String key : wordCounts.keySet())
        {
            q.offer(new PriorityWordCount(key, wordCounts.get(key)));
        }

        String[] words = new String[q.size()];
        for (int i = 0; !q.isEmpty(); ++i)
        {
            words[i] = q.poll().word;
        }

        return words;
    }
}

