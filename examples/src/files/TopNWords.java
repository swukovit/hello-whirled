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
     * @param f The file to read from
     * @param n The number of words to find
     * @return Array of the top n words in the file
     */
    public String[] findTopNWords(File f, int n) throws FileNotFoundException
    {
        validateInput(f, n);
        Map<String,Integer> wordCounts = countWords(f);
        return findTopNWords(wordCounts, n);
    }

    private void validateInput(File f, int n)
    {
        if (f == null || n <= 0 || !f.exists() || f.length() == 0) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Counts the words in the file. A word is defined as anything that doesn't include whitespace.
     * @param f The file
     * @return A created map of word to count
     * @throws FileNotFoundException
     */
    Map<String, Integer> countWords(File f) throws FileNotFoundException
    {
        Scanner fileScanner = new Scanner(f);

        HashMap<String, Integer> wordCounts = createHashMap(f.length());

        while (fileScanner.hasNextLine())
        {
            String[] lineWords = fileScanner.nextLine().split("\\s");
            for (String word : lineWords)
            {
                int currentCount = wordCounts.get(word);
                wordCounts.put(word, ++currentCount);
            }
        }

        return wordCounts;
    }

    /**
     * Creates a hashmap initialized to an estimated correct capacity to hold all the words given a file length.
     * @param fileLength the length of the text file
     * @return A new HashMap
     */
    HashMap<String, Integer> createHashMap(long fileLength)
    {
        final int estimatedWordCount = (int) fileLength / 10 + 1;
        return new HashMap<>(estimatedWordCount);
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
            return Integer.compare(count, o.count);
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

