import java.util.HashSet;
import java.util.Set;

public class WordalizerMain{

    public static void main(String args[]) {
        int n = 3;
        Wordalizer wordalizer = new Wordalizer(n);
        WordIterator words = new WordIterator("verbList.txt");
        Set wordSet = new HashSet();
        while (words.hasNext()) {
            String word = words.next();
            wordalizer.addWord(word);
            wordSet.add(word);
        }
        for (int i = 0; i < 10; i++) {
            String x = wordalizer.createWord();
            if (!wordSet.contains(x)) {
                System.out.println(x);
            } else i--;
        }
    }
}
