package Lab3.Assignment2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;

public class FrequencyCounter 
{
    public static void main(String[] args) throws Exception
    {
        int minLen = Integer.parseInt(args[0]);

        SearchTree<String, Integer> st = new SearchTree<>(200000);


        List<String> lines = Files.readAllLines(Path.of(args[1]));

        Iterator<String> iterator = lines.iterator();
        while(iterator.hasNext()) {
            String[] words = iterator.next().split(" ");

            for(String word : words) {
                if(word.length() < minLen) continue;

                if(!st.contains(word)) {
                    st.put(word, 1);
                } else {
                    st.put(word, st.get(word) + 1);
                }
            }
        }
        String max = "";
        st.put(max, 0);

        for(String word : st.keys()) {
            if(st.get(word) > st.get(max)) max = word;
        }
        System.out.println(max + " " + st.get(max));
    }
}
