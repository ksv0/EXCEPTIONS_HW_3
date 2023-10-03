package ksv.view;

import java.util.Scanner;

public class ConsoleView implements View{
    Scanner in = new Scanner(System.in);
    @Override
    public void set(String message) {
        System.out.println(message);
    }

    @Override
    public String get() {
        return in.nextLine();
    }
}
