package Lab3.Assignment4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordFinder
{
    public static List<Integer> findWord(String text, String word)
    {
        List<Integer> indices = new ArrayList<>();
        String desiredWord = ' ' + word + ' ';

        int i = 0;

        while ((i = text.indexOf(desiredWord, i)) != -1) {
            indices.add(i);
            i++;
        }
        return indices;
    }

    public static List<Integer> findWord2(String text, String word)
    {
        List<Integer> indices = new ArrayList<>();
        String[] words = text.split(" ");

        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word))
                indices.add(i);
        }
        return indices;
    }

    private static void printResult(List<Integer> foundIndices, String word)
    {
        if (foundIndices.isEmpty())
            System.out.println("No instances of the word found in the text");
        else {
            int col = 0;
            System.out.printf("The word '%s' is found %d time(s) at the position(s):%n", word, foundIndices.size());
            for (Integer index : foundIndices) {
                System.out.printf("%d\t%n", index);
                col++;
                if (col == 6) {
                    System.out.println();
                    col = 0;
                }
            }
        }
    }

    public static void main(String[] args)
    {

        try (Stream<String> lines = Files.lines(Path.of(args[0]))) {
            String text = lines.collect(Collectors.joining());
            lines.close();

            System.out.println(text);
            System.out.println("Enter the word you are looking for");
            String word = new Scanner(System.in).next();

            List<Integer> foundIndices = findWord(text, word);
            List<Integer> wordIndices = findWord2(text, word);

            printResult(foundIndices, word);
            printResult(wordIndices, word);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
