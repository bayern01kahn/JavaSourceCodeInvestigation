package justin.lambda;

import java.util.stream.IntStream;

public class IntSteamT1 {


    public static void main(String[] args) {



        IntStream.range(0,5).forEach((i) -> System.out.println(i));

        System.out.println("------------------");

        IntStream.rangeClosed(0,5).forEach((i) -> System.out.println(i));



    }
}
