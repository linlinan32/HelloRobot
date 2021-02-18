package com.company;

import java.util.Scanner;

public class Main {

    Boolean safari;//this attribute will give the guide, either move up down or left right

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

        String move=n.nextLine(); //take user input
        String array[] = inputValidate(move);//split user input by common and trim the spaces


        int moveX = 0, moveY = 0;
        int x = 1, y = 1;
        //assume the movement is located in the x-axis and y-axis movement
        //indicate the movement by (x,y)
        //indicate every turn around by L or R, the quadrant would change also

        for(int i=0; i<array.length;i++){

            Integer quadrant[]=inputMovement(array[i]);
            x=quadrant[0];
            y=quadrant[0];


            System.out.println(inputMovement(array[i])[1]);


            //here is to calculate the x and y position by taking user input
        }

    }

    public void setSafari(Boolean safari)
    {
        this.safari=safari;

    }
    public Boolean getSafari()
    {
        return safari;

    }

    /**
     *
     * @param moveInput
     * @return
     */
    public static String[] inputValidate(String moveInput){

        String array[]=moveInput.trim().split(",");
        return array;
    }
    public static Integer[] inputMovement(String movement)
    {
        Integer x[]={0,0};

        if(movement.contains("L") ||movement.contains("l") ){
           Integer direction= Integer.parseInt(
                    movement.substring(1)) % 4;

            return inputTurn(direction);
        }
        if(movement.contains("R") ||movement.contains("r") ){
            Integer direction=  Integer.parseInt(
                    movement.substring(1)) % 4;

            return inputTurnRight(direction);
        }
        if(movement.contains("F") ||movement.contains("f") ){
            Integer direction= Integer.parseInt(
                    movement.substring(1)) % 4;

            return inputTurn(direction);
        }
        if(movement.contains("R") ||movement.contains("r") ){
            Integer direction=  Integer.parseInt(
                    movement.substring(1)) % 4;

            return inputTurnRight(direction);
        }


        return x;

    }

    /**
     *
     * @param direction
     * this method give input of no of turn left
     * assume the initial position is faced to the 0+ x-axis
     * @return
     */
    public static Integer[] inputTurn(Integer direction){

        Integer x[]={0,0};
        //turn left
        if(direction==1){
             x[0]=1;
            x[1]=1;//turn once from quadrant 1 x-axis to y-axis, forward is positive
            return x;
        }
        if(direction==2){
            x[0]=-1;
            x[1]=1;//turn once from y-axis to quadrant 2 x-axis, forward is negative
            return x;
        }
        if(direction==3){
            x[1]=-1;
            x[0]=1;//turn once from quadrant 2 x-axis to 3 quadrant y-axis, forward is negative
            return x;
        }
        if(direction==4){
            x[0]=1;
            x[1]=1;//turn once from quadrant 3 y-axis to 1 x-axis, forward is positive
            return x;
        }
        return x;
    }

    /**
     *
     * @param direction
     * this method give the input of no of turn right
     * @return
     */
    public static Integer[] inputTurnRight(Integer direction){

        Integer x[]={0,0};
        //turn right
        if(direction==1){
            x[1]=-1;
            x[0]=1;//face to y-axis, forward is 0-
            return x;
        }
        if(direction==2){
            x[0]=-1;
            x[1]=1;//face to x-axis, forward is 0-
            return x;
        }
        if(direction==3){
            x[0]=1;
            x[1]=1;//face to y-axis, forward is 0+
            return x;
        }
        if(direction==4){
            x[0]=1;
            x[1]=1;//face to x-axis, forward is 0+
            return x;
        }
        return x;
    }
}
