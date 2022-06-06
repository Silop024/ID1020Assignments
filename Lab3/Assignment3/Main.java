package Lab3.Assignment3;

import java.io.File;
import java.util.Scanner;

public class Main 
{
    public static void main(String[] args) throws Exception
    {
        File f = new File("C:\\Users\\Silop\\Desktop\\ID1020Assignments\\Lab3\\Assignment3\\Text.txt");
        Scanner s = new Scanner(f);

        SearchTree<String, Integer> st = new SearchTree<String, Integer>(997);

        for(int i = 0; s.hasNext(); i++)
        {
            String key = s.next();
            st.put(key, i);
        }
        s.close();
        st.printSize();
    }
}
