//import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class RandomDist<Type> {
    private Type[] dist;
    private int size;


    public RandomDist(){
        dist = (Type[]) new Object[10];
        size = 0;
    }

    public void add(Type addition){
        if (this.filled()) {
            this.expand();
        }
        dist[size] = addition;
        size++;
    }

    public boolean filled(){
        if(this.dist.length == this.size){
            return true;
        } else return false;
    }


    public void expand(){
        this.dist = Arrays.copyOf(this.dist, this.dist.length*2);
    }

    public Type draw(){
        Random rand = new Random();
        int index = rand.nextInt(this.size);
        Type i = this.dist[index];
        return i;
    }
}

