package com.company;

import java.util.Scanner;

public class Main {


    private static Boolean positive=true;
    private static Boolean safari=false;//this attribute will give the guide, either move up down or left right
    private  static Integer moveUnit=0;// the number of unit in movement

    private static Integer turn=0;// the incremental number of turn around
    private static Integer quadrant;// the initial robot is facing to the east

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
        String[] array = inputValidate(move);//split user input by common and trim the spaces

        int moveX = 0, moveY = 0;
        quadrant=0;
        //assume the movement is located in the x-axis and y-axis movement
        //indicate the movement by (x,y)
        //indicate every turn around by L or R, the quadrant would change also
        boolean play=true;
        while(play) {
            for (String s : array) {

                System.out.println(s.trim());
                inputMovement(s.trim());
                if (safari && positive) {
                    moveY += moveUnit;
                }
                if (safari && !positive) {
                    moveY -= moveUnit;
                }
                if (!safari && positive) {
                    moveX += moveUnit;
                }
                if (!safari && !positive) {
                    moveX -= moveUnit;
                }
                //here is to calculate the x and y position by taking user input
            }

            if (moveX == 0 && moveY == 0) {
                System.out.println("Success, You've returned to the start point");
                play=false;
            } else {
                System.out.println("moveX: " + moveX + ", moveY: " + moveY);
                System.out.println("Not yet, You left a few steps to return start point");
                System.out.println("Enter Y to continue, enter other to quit the game");
                String decision=n.nextLine();
                if(decision.equals("Y")){
                    play=true;
                    System.out.println("Now you can enter the series of movement, For example \n " +
                            "* `F1` - move forward 1 unit\n" +
                            "* `B1` - move backward 1 unit\n" +
                            "* `R1` - turn right 90 degrees\n" +
                            "* `L1` - turn left 90 degrees");
                     move=n.nextLine(); //take user input
                    array= inputValidate(move);//split user input by common and trim the spaces

                }
                else {
                    play=false;
                }
            }
        }

    }

    public static void setSafari(Boolean safari1)
    {
        safari=safari1;

    }
    public Boolean getSafari()
    {
        return safari;

    }

    public Boolean getPositive() {
        return positive;
    }

    public static void setPositive(Boolean positive1) {
        positive = positive1;
    }

    public Integer getMoveUnit() {
        return moveUnit;
    }

    public static void setMoveUnit(Integer moveUnit1) {
        moveUnit = moveUnit1;
    }
    public static Integer getQuadrant() {
        return quadrant;
    }

    public static void setQuadrant(Integer quadrant) {
        Main.quadrant = quadrant;
    }

    public static Integer getTurn() {
        return turn;
    }

    public static void setTurn(Integer turn) {
        Main.turn = turn;
    }

    /**
     *
     * @param input
     * @return
     */
    public static String[] inputValidate(String input){

       String moveInput=input.toUpperCase();
        return moveInput.trim().split(",");
    }

    /**
     * this method input the split command with number
     * count the number by its direction
     * @param movement
     */
    public static void inputMovement(String movement) throws NumberFormatException
    {
try {
    if (movement.contains("L") || movement.contains("l")) {
        Integer direction = Integer.parseInt(
                movement.substring(1)) % 4;
        quadrant += direction;
        inputTurn(quadrant);
        setMoveUnit(0);
        System.out.println("Robot received the command and turn over 90 degrees from LEFT " + Integer.parseInt(
                movement.substring(1))+" times");

    }
    if (movement.contains("R") || movement.contains("r")) {
        Integer direction = Integer.parseInt(
                movement.substring(1)) % 4;
        quadrant = Math.abs(quadrant - direction);
        setMoveUnit(0);
        inputTurnRight(quadrant);
        System.out.println("Robot received the command and turn over 90 degrees from RIGHT " + Integer.parseInt(
                movement.substring(1))+" times");
    }
    if (movement.contains("F") || movement.contains("f")) {

        setMoveUnit(Integer.parseInt(
                movement.substring(1)));
        System.out.println("Robot received the command and moved FORWARD " + Integer.parseInt(
                movement.substring(1))+" unit(s)");

    }
    if (movement.contains("B") || movement.contains("b")) {

        setMoveUnit(-Integer.parseInt(
                movement.substring(1)));
        System.out.println("Robot received the command and moved BACKWARD  " + Integer.parseInt(
                movement.substring(1))+" unit(s)");
    } else if (!movement.contains("B") && !movement.contains("L") && !movement.contains("F") && !movement.contains("R")) {
        System.out.println("Your Robot cannot follow your instruction");
        System.out.println("Please follow the rule to enter commands follow by rules, for example \n" +
                " * `F1` - move forward 1 unit\n" +
                "* `B1` - move backward 1 unit\n" +
                "* `R1` - turn right 90 degrees\n" +
                "* `L1` - turn left 90 degrees");
    }

}catch (Exception e){
    System.out.println("Please Follow the input rule with one character and number of movement \n " +
            "e.g. <Commoand> + <Number>");

}

    }

    /**
     *
     * @param direction
     * this method give input of no of turn left
     * assume the initial position is faced to the 0+ x-axis
     */
    public static void inputTurn(Integer direction){


        //turn left
        if(direction==1){
            setPositive(true);//face to y-axis, forward is 0+
            setSafari(true);
            return;
        }
        if(direction==2){
            setPositive(false);//face to x-axis, forward is 0-
            setSafari(false);
            return;
        }
        if(direction==3){
            setPositive(false); //face to y-axis, forward is 0-
            setSafari(true);
            return;
        }
        if(direction==4){
            setPositive(true);//face to x-axis, forward is 0+
            setSafari(false);
        }
    }

    /**
     *
     * @param direction
     * this method give the input of no of turn right
     */
    public static void inputTurnRight(Integer direction){

        //turn right
        if(direction==1){
            setPositive(false); //face to y-axis, forward is 0-
            setSafari(true);
            return;
        }
        if(direction==2){
            setPositive(false);//face to x-axis, forward is 0-
            setSafari(false);
            return;
        }
        if(direction==3){
            setPositive(true);//face to y-axis, forward is 0+
            setSafari(true);
            return;
        }
        if(direction==4){
            setPositive(true);//face to x-axis, forward is 0+
            setSafari(false);
        }
    }
}
