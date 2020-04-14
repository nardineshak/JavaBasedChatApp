package tests.gitlab.ckpt1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.Map;
import java.util.function.Supplier;

import cse332.datastructures.containers.Item;
import cse332.interfaces.misc.Dictionary;
import cse332.types.AlphabeticString;
import cse332.types.NGram;
import cse332.datastructures.trees.BinarySearchTree;
import p2.wordsuggestor.NGramToNextChoicesMap;
import org.junit.Test;
import static org.junit.Assert.*;

public class NGramToNextChoicesMapTests {
    private Supplier<Dictionary<NGram, Dictionary<AlphabeticString, Integer>>> newOuter =
            () -> new BinarySearchTree();

    private Supplier<Dictionary<AlphabeticString, Integer>> newInner =
            () -> new BinarySearchTree();

    private NGramToNextChoicesMap init() {
        return new NGramToNextChoicesMap(newOuter, newInner);
    }

    @Test(timeout = 3000)
    public void testOneWordPerNGram() {
        NGramToNextChoicesMap map = init();
        NGram[] ngrams = new NGram[]{
                new NGram(new String[]{"foo", "bar", "baz"}),
                new NGram(new String[]{"fee", "fi", "fo"}),
                new NGram(new String[]{"a", "s", "d"})
        };

        String[] words = new String[]{"bop", "fum", "f"};
        for (int i = 0; i < ngrams.length; i++) {
            map.seenWordAfterNGram(ngrams[i], words[i]);
        }
        for (int i = 0; i < ngrams.length; i++) {
            Item<String, Integer>[] items = map.getCountsAfter(ngrams[i]);
            assertEquals(1, items.length);
            Item<String, Integer> item = items[0];
            assertEquals(words[i], item.key);
            assertEquals(1, item.value.intValue());
        }
    }

    @Test(timeout = 3000)
    public void testMultipleWordsPerNGram() {
        NGramToNextChoicesMap map = init();
        NGram[] ngrams = new NGram[]{
                new NGram(new String[]{"foo", "bar", "baz"}),
                new NGram(new String[]{"fee", "fi", "fo"}),
                new NGram(new String[]{"four", "score", "and"}),
                new NGram(new String[]{"3", "2", "2"}),
                new NGram(new String[]{"a", "s", "d"})
        };

        String[][] words = new String[][] {
                new String[]{"bip", "bop", "bzp"},
                new String[]{"fum", "giants"},
                new String[]{"ago", "seven", "years"},
                new String[]{"new", "thrown", "uuu", "zzz"},
                new String[]{"do", "for", "while"}
        };

        for (int i = 0; i < ngrams.length; i++) {
            for (int j = 0; j < words[i].length; j++) {
                map.seenWordAfterNGram(ngrams[i], words[i][j]);
            }

        }
        for (int i = 0; i < ngrams.length; i++) {
            Item<String, Integer>[] items = map.getCountsAfter(ngrams[i]);
            String[] answer = words[i];
            assertEquals(answer.length, items.length);
            String[] itemsWithoutCounts = new String[items.length];
            for (int j = 0; j < answer.length; j++) {
                assertEquals(1, items[j].value.intValue());
                itemsWithoutCounts[j] = items[j].key;
            }
            Arrays.sort(itemsWithoutCounts);
            assertArrayEquals(answer, itemsWithoutCounts);
        }
    }

    @Test(timeout = 3000)
    public void testGetNonexistentNGram() {
        NGramToNextChoicesMap map = init();
        NGram[] ngrams = new NGram[]{
                new NGram(new String[]{"foo", "bar", "baz"}),
                new NGram(new String[]{"fee", "fi", "fo"}),
                new NGram(new String[]{"a", "s", "d"})
        };

        String[] words = new String[]{"bop", "fum", "f"};
        for (int i = 0; i < ngrams.length; i++) {
            map.seenWordAfterNGram(ngrams[i], words[i]);
        }
        Item<String, Integer>[] items = map.getCountsAfter(new NGram(new String[] { "yo" }));
        assertNotNull(items);
        assertEquals(0, items.length);
    }

    @SuppressWarnings("unchecked")
    @Test(timeout = 3000)
    public void testRepeatedWordsPerNGram() {
        NGramToNextChoicesMap map = init();
        // Creates Ngrams to test for with N = 3
        NGram[] ngrams = new NGram[]{
                new NGram(new String[]{"foo", "bar", "baz"}),
                new NGram(new String[]{"fee", "fi", "fo"}),
                new NGram(new String[]{"four", "score", "and"}),
                new NGram(new String[]{"3", "2", "2"}),
                new NGram(new String[]{"a", "s", "d"})
        };
        // Array of words seen after each Ngram with correlating index from above
        String[][] words = new String[][] {
                new String[]{"bop", "bip", "boop", "bop", "bop"},
                new String[]{"fum", "giants", "giants"},
                new String[]{"seven", "years", "years", "ago", "ago"},
                new String[]{"throw", "throw", "throw", "throw", "throw"},
                new String[]{"for", "while", "do", "do", "while", "for"}
        };

        // yes this is awful, but i can't think of a better way to do it atm
        // Creates answers for getCountsAfter - Word seen after and count
        // corrlates with words and ngrams above
        // Note that words after are in sorted order, not in order of array in words
        Map<NGram, Item<String, Integer>[]> answers = new TreeMap<>();
        answers.put(ngrams[0], (Item<String, Integer>[]) new Item[]{
                new Item<>("bip", 1),
                new Item<>("boop", 1),
                new Item<>("bop", 3)
        });

        answers.put(ngrams[1], (Item<String, Integer>[]) new Item[]{
                new Item<>("fum", 1),
                new Item<>("giants", 2)
        });

        answers.put(ngrams[2], (Item<String, Integer>[]) new Item[]{
                new Item<>("ago", 2),
                new Item<>("seven", 1),
                new Item<>("years", 2)
        });

        answers.put(ngrams[3], (Item<String, Integer>[]) new Item[]{
                new Item<>("throw", 5)
        });

        answers.put(ngrams[4], (Item<String, Integer>[]) new Item[]{
                new Item<>("do", 2),
                new Item<>("for", 2),
                new Item<>("while", 2)
        });

        // Adds nGrams and words after to student's NGramToNextChoicesMap
        for (int i = 0; i < ngrams.length; i++) {
            for (int j = 0; j < words[i].length; j++) {
                map.seenWordAfterNGram(ngrams[i], words[i][j]);
            }
        }

        // checks to see if getCountsAfter returns correctly
        for (int i = 0; i < ngrams.length; i++) {
            NGram ngram = ngrams[i];
            Item<String, Integer>[] results = map.getCountsAfter(ngram);
            Arrays.sort(results, Comparator.comparing(r -> r.key));
            Item<String, Integer>[] expected = answers.get(ngram);
            // checks for correct number of unique words after
            assertEquals(expected.length, results.length);
            for (int j = 0; j < expected.length; j++) {
                // checks if correct word after via sorted words
                assertEquals(expected[j].key, results[j].key);
                // checks if correct count for given word after
                assertEquals(expected[j].value, results[j].value);
            }
        }
    }
}
