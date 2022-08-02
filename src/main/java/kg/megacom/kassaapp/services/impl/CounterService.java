package kg.megacom.kassaapp.services.impl;

public class CounterService {

    private static  int counter = 0;

    public static int getCounter () {

        counter++;
        System.out.println(counter);
        return counter;
    }

}
