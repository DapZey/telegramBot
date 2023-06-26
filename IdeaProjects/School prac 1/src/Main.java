import java.util.Scanner;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();
        while (scan.hasNextInt()) {
            int a = scan.nextInt();
            if (a > 0){
            list.add(a);}
        }
        for (int i : list){
            if ((i %3 == 0) && (i%5 == 0)){
                System.out.println("fizzbuzz");
            } else if (i % 3 == 0) {
                System.out.println("fizz");
            } else if (i % 5 == 0) {
                System.out.println("buzz");
            } else {
                System.out.println(i);
            }
        }
    }
}