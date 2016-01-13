package files;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.io.FileReader;

/**
 * This class reads a dictionary file of words, classifies them based on vowel-consonant patterns,
 * and writes or prints out info about what was found, such as which words match which patterns,
 * which patterns only have one word matching them, which patterns have the most words matching them,
 * etc.
 *
 * Created by johnwilliams on 1/8/16.
 */
public class WordVowelPatternClassifier {

    /**
     * Maps a vowel-consonant pattern (such as "_a_o_ia_") to a set of words
     * matching that pattern (such as "babylonian").
     */
    private Map<String,Set<String>> wordsMatchingPatterns = new TreeMap<>();

    /**
     * Maps the number of words that match a pattern to the patterns with that multiplicity.
     * For example, if there are two words that match the pattern _oo and two words that
     * match the pattern _aa, then this would map 2 to ["_oo", "_aa"].
     */
    private TreeMap<Integer,Set<String>> patternsHavingWordCount = new TreeMap<>();


    /**
     * Read all words from a dictionary file, classify them according to the pattern of
     * vowels and consonants, then collect statistical info about the patterns.
     *
     * @throws FileNotFoundException
     */
    public void readDictionary() throws FileNotFoundException {

        FileReader reader = new FileReader("/Users/johnwilliams/IdeaProjects/hello-whirled/examples/src/files/dict.txt");
        Scanner scanner = new Scanner(reader);

        while (scanner.hasNext()) {
            String word = scanner.next().toLowerCase();
            addWordAndPattern(word);
        }

        classifyPatternsByWordCounts();
    }

    /**
     * Given a word, construct a vowel consonant pattern matching it, and store that in a map
     * from pattern to words matching the pattern.
     */
    private void addWordAndPattern(String word) {

        String pattern = findVowelConsonantPattern(word);
        Set<String> wordsMatching;

        if (wordsMatchingPatterns.containsKey(pattern)) {
            wordsMatching = wordsMatchingPatterns.get(pattern);
        }
        else {
            wordsMatching = new TreeSet<>();
            wordsMatchingPatterns.put(pattern, wordsMatching);
        }

        wordsMatching.add(word);
    }

    /**
     * Convert a word such as "foobar" to a pattern such as "_oo_a_".
     */
    private String findVowelConsonantPattern(String word) {

        StringBuilder sb = new StringBuilder();
        boolean prevWasConsonant = false;

        for (int i = 0; i < word.length(); ++i) {
            char c = word.charAt(i);
            switch (c) {
                case 'a':case 'e':case 'i':case 'o':case 'u': {
                    sb.append(c);
                    prevWasConsonant = false;
                    break;
                }
                default: {
                    if (!prevWasConsonant) {
                        sb.append('_');
                        prevWasConsonant = true;
                    }
                    break;
                }
            }
        }

        return sb.toString();
    }

    /**
     * A given pattern will map to one or more words. Group the patterns according to
     * how many words they map to.
     */
    private void classifyPatternsByWordCounts() {
        for (String pattern : wordsMatchingPatterns.keySet()) {
            int count = wordsMatchingPatterns.get(pattern).size();
            if (patternsHavingWordCount.containsKey(count)) {
                patternsHavingWordCount.get(count).add(pattern);
            }
            else {
                Set<String> newSet = new TreeSet<>();
                newSet.add(pattern);
                patternsHavingWordCount.put(count, newSet);
            }
        }
    }

    /**
     * Write a text file listing each word pattern and the words matching that pattern.
     */
    public void writeAllWordPatternsAndWordsMatchingThem() throws IOException {
        FileWriter writer = new FileWriter("PatternsAndMatchingWords.txt");

        for (String pattern : wordsMatchingPatterns.keySet()) {
            writer.append(pattern + "\t");
            for (String word : wordsMatchingPatterns.get(pattern)) {
                writer.append(" " + word);
            }
            writer.append('\n');
        }

        writer.flush();

        /*
            _a_e_iou_	 facetious facetiously
         */
    }

    /**
     * Print the first few and last few patterns and their words.
     */
    public void printSampleWordPatternsAndSampleWordsMatchingThem() {
        Set<String> patterns = wordsMatchingPatterns.keySet();
        int numPatterns = patterns.size();
        final int numPatternsToPrint = 3;
        final int maxWordsPerPattern = 10;
        int index = 0;
        for (String pattern : patterns) {
            ++index; // TODO: Inefficient
            if (index < numPatternsToPrint || index > numPatterns - numPatternsToPrint) {
                System.out.println(pattern + ":");
                int numWordsPrinted = 0;
                for (String word : wordsMatchingPatterns.get(pattern)) {
                    System.out.println("\t" + word);
                    if (++numWordsPrinted > maxWordsPerPattern) {
                        System.out.println("\t...");
                        break;
                    }
                }
            }
        }
    }

    public void writeAllPatternsByWordCounts() throws IOException {
        FileWriter writer = new FileWriter("PatternsByWordCounts.txt");

        for (int count : patternsHavingWordCount.keySet()) {
            writer.append(count + "\t");
            for (String pattern : patternsHavingWordCount.get(count)) {
                writer.append(" " + pattern);
            }
            writer.append("\n");
        }
        writer.flush();

        /*  ...
            501	    _e_a_     _e_i_e_
            559	    _o_e_e_
            570	    _a_ie_
            582	    _a_e_e_   _o_a_
            680	    _a_o_
            796	    _a_a_     _e_i_
            826	    _u_i_
            922	     _e_
            924	     _u_
            1017	 _o_i_
            1092	 _i_i_
            1119	 _o_
            1267	 _e_e_
            1371	 _i_
            1529	 _u_e_
            1602	 _a_
            1629	 _a_i_
            1917	 _o_e_
            2351	 _i_e_
            3043	 _a_e_
         */
    }
    
    public void writeWordPatternData() throws IOException {

        FileWriter writer = new FileWriter("WordPatternData.csv");
        writer.append("VowelPattern,NumWordsWithPattern,Word\n");

        for (Integer count : patternsHavingWordCount.keySet()) {
            Integer patternsForCount = patternsHavingWordCount.get(count).size();

            for (String pattern : patternsHavingWordCount.get(count)) {
                for (String word : wordsMatchingPatterns.get(pattern)) {
                    writer.append(pattern)
                            .append(",")
                            .append(count.toString())
                            .append(",")
                            .append(word)
                            .append("\n");
                    writer.flush();
                }
            }

        }
        writer.flush();
    }

    public void writePatternWordCountStatistics() throws IOException {

        FileWriter writer = new FileWriter("PatternWordCountStatistics.csv");
        writer.append("NumWordsWithPattern,NumPatternsForThisNum\n");

        for (Integer count : patternsHavingWordCount.keySet()) {
            Integer patternsForCount = patternsHavingWordCount.get(count).size();
            writer.append(count.toString() + "," + patternsForCount.toString() + "\n");
        }
        writer.flush();

        /*
            NumWordsWithPattern,NumPatternsForThisNum
            1,5628
            2,2552
            3,928
            ...
            1629,1
            1917,1
            2351,1
            3043,1
         */
    }

    /**
     * There may be one pattern matching thousands of words. There may be thousands of patterns
     * each matching only one word. And there may be things inbetween. When put on a log-log graph,
     * these form an approximate line with a negative slope. This method prints out the approximate
     * midpoint of that graph,
     * where words matching patterns and patterns matching counts are about equal. In other words,
     * look at the non-outliers.
     */
    private void printSimilarMultiplicityPatternsAndWords() {
        for (Integer count : patternsHavingWordCount.keySet()) {
            Integer patternsForCount = patternsHavingWordCount.get(count).size();

            if (count > 10 && count < 26 && patternsForCount > 10 && patternsForCount < 26) {
                System.out.println(count + ":" + patternsForCount + ":");
                for (String pattern : patternsHavingWordCount.get(count)) {
                    System.out.println("\t" + pattern);
                    for (String word : wordsMatchingPatterns.get(pattern)) {
                        System.out.println("\t\t" + word);

                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        WordVowelPatternClassifier c = new WordVowelPatternClassifier();
        c.readDictionary();

        c.writeAllWordPatternsAndWordsMatchingThem();
        c.printSampleWordPatternsAndSampleWordsMatchingThem();

        c.writeAllPatternsByWordCounts();
        c.writeWordPatternData();

        c.writePatternWordCountStatistics();

        c.printSimilarMultiplicityPatternsAndWords();
    }
}
