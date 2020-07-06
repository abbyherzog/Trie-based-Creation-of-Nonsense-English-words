import java.lang.String;

public class Wordalizer {
    private int n;
    private Trie tree;

    private class Trie {
        private Node root; // empty string


        public Trie(int n){
            root = new Node();
            root.forceHeight(n);

        }

        public RandomDist<Character> findNodeDist(String string){
            if(string.length() > n){
                throw new IllegalArgumentException();
            }
            Node hold = root;
            for(int i=0; i<string.length(); i++){
                hold = hold.getChild(string.charAt(i));
            }
            return hold.x;
        }

    }

    private class Node{
        private RandomDist<Character> x;
        private Node[] children;

        public Node(){
            children = new Node[27];
            this.x = new RandomDist<>();
        }

        public Node getChild(char c){
            int index = c - 'a';
            return children[index];
        }

        public void forceHeight(int height){
            if(height == 0){
                //
            }else{
                //not a leaf
                for(int i=0; i<27; i++){
                    children[i] = new Node();
                    children[i].forceHeight(height -1);
                }
            }
        }
    }
    // root node represents the empty string
    //build the trie based on the n
    //get takes string returns what it finds
    //put builds nodes
    //build generic trie and then make a randomdist trie in wordalizer
    //keys is a sequence of letters, tells you which VALUE randomdist is the next letter
    //class Character has a toLowercase to make sure it's lowercase
    //subract 'a' that shifts the askii code so A is index 0, etc;
    //left curly bracket { follows directly after z so.. if you generate it you're done


    public Wordalizer(int n){
        //this one makes the gibberish word
        this.tree = new Trie(n);
        this.n=n;
    }

    public void addWord(String word){
        if(word.length()<n){
            //
        } else {
            //replace printlns with something that gets correct randomdist and adds the letter
            word+='{';
            //System.out.println("empty goes to " + word.charAt(0));
            this.tree.root.x.add(word.charAt(0));
            for (int j = 0; j < n-1; j++) {
                String x = word.substring(0,j+1);
                //System.out.println(x + " goes 1 to " + word.charAt(j + 1));
                this.tree.findNodeDist(x).add(word.charAt(j+1));
            }
            for (int j = n; j < word.length(); j++) {
                String x = word.substring(j-n,j);
                //System.out.println(x + " goes 2 to " + word.charAt(j));
                this.tree.findNodeDist(x).add(word.charAt(j));
            }
        }
    }

    public char getNextLetter(String substring){
        return this.tree.findNodeDist(substring).draw();
    }

    public String createWord(){
        String newWord = "";
        String sub = "";
        Node starter = this.tree.root;
        char c = 0;
        for(int i=0; i<n; i++){
            if(starter.x.draw() != null) {
                c = starter.x.draw();
                newWord += c;
                sub += c;
                starter = starter.getChild(c);
            }
        }
        while(!newWord.endsWith("{")){
            c = this.getNextLetter(sub);
            newWord += c;
            sub = sub.substring(1) + c;
        }
        newWord = newWord.substring(0,newWord.length()-1);
        return newWord;
    }

    //Set set = new HashSet();
    //check if the word is in the verb file

    public static void main(String[] args) {
        Wordalizer w = new Wordalizer(3);
        w.addWord("grobaloo");
    }
}
