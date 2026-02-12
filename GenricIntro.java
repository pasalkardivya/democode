class GenEx<T>{
    T var;
     GenEx(T v){
        var = v;
     }

T getVar(){
    return var;

} 
void printvar(){
    System.out.println("the value of var"+var);
}
}


public class GenricIntro {
    public static void main(String [] args){
        System.out.println("hello");
        GenEx<Integer> genEx = new GenEx<Integer>(10);
        genEx.printvar();
    }
}


