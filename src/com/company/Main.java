package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner n=new Scanner(System.in);
        System.out.println("**********************");
        System.out.println("Welcome to Robot Game");
        System.out.println("here is your Robot X");
        System.out.println("Now you can enter the series of movement, For example \n " +
                "* `F1` - move forward 1 unit\n" +
                "* `B1` - move backward 1 unit\n" +
                "* `R1` - turn right 90 degrees\n" +
                "* `L1` - turn left 90 degrees");

        String move=n.nextLine();
        String array[] = inputValidate(move);
        System.out.println(array[0]+array[1]);

        int l = move.length();
        int countUp = 0, countDown = 0;
        int countLeft = 0, countRight = 0;

    }
    public static String[] inputValidate(String moveInput){

        String array[]=moveInput.split(",");
        return array;
    }
}
