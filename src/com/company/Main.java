package com.company;

import java.util.Scanner;

public class Main {


    private static Boolean positive = true;
    private static Boolean safari = false;//this attribute will give the guide, either move up down or left right
    private static Integer moveUnit = 0;// the number of unit in movement
    private static Integer quadrant;// the initial robot is facing to the east
    private static Integer moves=0;// this attribute makes sure the robot move not only turn around

    public static void main(String[] args) {
        // write your code here
        Scanner n = new Scanner(System.in);
        System.out.println("*********************************************");
        System.out.println("Welcome to Robot Game");
        System.out.println("Here is your Robot, now it is facing to the EAST");
        System.out.println("You need to move to play the game, the robot can only turn 90 degrees at a time, so it cannot go directly back home.\n" +
                "It must go in north, south, east, west directions. You can move by having commands F, B, R, L, and number of units");
        System.out.println(" For example, L1, F1, R1, B1...\n " +
                "* `F1` - move forward 1 unit\n" +
                "* `B1` - move backward 1 unit\n" +
                "* `R1` - turn right 90 degrees\n" +
                "* `L1` - turn left 90 degrees\n" +
                "Now you can enter the series of movement, <command>+<number>"
        );
        String move = n.nextLine(); //take user input
        String[] array = inputValidate(move);//split user input by common and trim the spaces

        int moveX = 0, moveY = 0;
        quadrant = 0;
        //assume the movement is located in the x-axis and y-axis movement
        //indicate the movement by (x,y)
        //indicate every turn around by L or R, the quadrant would change also
        boolean play = true;//when play is true, game would keep going till user win or quit
        while (play) {

            if(move.isEmpty()){
                System.out.println("You need to enter the series of movement, <command>+<number> to play this game"
                        );
                System.out.println("You can input now");
                move = n.nextLine(); //take user input
                array = inputValidate(move);//split user input by common and trim the spaces

            }
            else{
            //looping the commands
            for (String s : array) {

                System.out.println(s.trim());
                inputMovement(s.trim());
                if (safari && positive) {
                    moveY += moveUnit;//face to the north and move forward
                }
                if (safari && !positive) {
                    moveY -= moveUnit;//face to the south and move forward
                }
                if (!safari && positive) {
                    moveX += moveUnit;//face to the east and move forward
                }
                if (!safari && !positive) {
                    moveX -= moveUnit;//face to the east and move forward
                }

            }

            if (moveX == 0 && moveY == 0 && moves!=0) {
                //robot back to the start point
                System.out.println("Success, You've returned to the start point");
                System.out.println("*********************************************");
                play = false;
            }
            if (moves==0){
                System.out.println("Not yet, You need to start the game either move forward or backward, then return start point");
                System.out.println("Now you can enter Robot's movements");
                move = n.nextLine(); //take user input
                array = inputValidate(move);//split user input by common and trim the spaces
            }
            else if(moveX != 0 || moveY != 0) {
                System.out.println("Not yet, You left a few steps to return start point");
                Integer minimum=Math.abs(moveX)+Math.abs(moveY); //minimum unit equals to the x-axis units plus y-axis units
                System.out.println("the minimum amount of distance to get back to the starting point is "+minimum);
                System.out.println("Enter Y/y to continue, enter others to quit the game");
                String decision = n.nextLine().toUpperCase();
                if (decision.equals("Y")) {

                    System.out.println("Now you can enter the series of movement ");
                    move = n.nextLine(); //take user input
                    array = inputValidate(move);//split user input by common and trim the spaces

                } else {
                    play = false;
                    System.out.println("*********************************************");

                }
            }}
        }

    }

    public static void setSafari(Boolean safari1) {
        safari = safari1;

    }

    public Boolean getSafari() {
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


    public static Integer getMoves() {
        return moves;
    }

    public static void setMoves(Integer moves) {
        Main.moves = moves;
    }

    /**
     * this method is to split the user input String by ,
     *
     * @param input
     * @return
     */
    public static String[] inputValidate(String input) {

        String moveInput = input.toUpperCase();
        return moveInput.trim().split(",");

    }

    /**
     * this method input the split command and number
     * count the number by its direction
     * define its movement units
     * catch exception in case user not input the format of command and number
     * @param movement
     */
    public static void inputMovement(String movement) throws NumberFormatException {
        try {
            if (movement.contains("L") || movement.contains("l")) {
                Integer direction = Integer.parseInt(
                        movement.substring(1)) % 4;//extract integer
                quadrant += direction; //add the number of 90 degrees and
                inputTurn(quadrant); // which way the robot would face
                setMoveUnit(0);// set the Move unit is zero, L no need to move
                System.out.println("Robot received the command and turn over 90 degrees from LEFT " + Integer.parseInt(
                        movement.substring(1)) + " times");

            }
            if (movement.contains("R") || movement.contains("r")) {
                Integer direction = Integer.parseInt(
                        movement.substring(1)) % 4;//extract integer
                quadrant = Math.abs(quadrant - direction); //add the number of 90 degrees and
                setMoveUnit(0); // set the Move unit is zero, L no need to move
                inputTurnRight(quadrant);  // which way the robot would face
                System.out.println("Robot received the command and turn over 90 degrees from RIGHT " + Integer.parseInt(
                        movement.substring(1)) + " times");
            }
            if (movement.contains("F") || movement.contains("f")) {

                setMoveUnit(Integer.parseInt(
                        movement.substring(1)));//extract integer, moving positive unit
                setMoves(100000); //move is 100000 if the robot is moving
                System.out.println("Robot received the command and moved FORWARD " + Integer.parseInt(
                        movement.substring(1)) + " unit(s)");

            }
            if (movement.contains("B") || movement.contains("b")) {

                setMoveUnit(-Integer.parseInt(
                        movement.substring(1))); //extract integer, moving negative unit
                System.out.println("Robot received the command and moved BACKWARD  " + Integer.parseInt(
                        movement.substring(1)) + " unit(s)");
                setMoves(100000); //move is 100000 if the robot is moving
            } else if (!movement.contains("B") && !movement.contains("L") && !movement.contains("F") && !movement.contains("R")) {
                System.out.println("Your Robot cannot follow your instruction");
                System.out.println("Please follow the rule to enter commands follow by rules, for example \n" +
                        " * `F1` - move forward 1 unit\n" +
                        "* `B1` - move backward 1 unit\n" +
                        "* `R1` - turn right 90 degrees\n" +
                        "* `L1` - turn left 90 degrees");
            }

        } catch (Exception e) {
            System.out.println("Please Follow the input rule with one character and number of movement \n " +
                    "e.g. <Commoand> + <Number>");

        }

    }

    /**
     * @param direction this method give input of no of turn left
     *                  assume the initial position is faced to the 0+ x-axis
     */
    public static void inputTurn(Integer direction) {


        //turn left
        if (direction == 1) {
            setPositive(true);//face to y-axis, forward is 0+
            setSafari(true);
            return;
        }
        if (direction == 2) {
            setPositive(false);//face to x-axis, forward is 0-
            setSafari(false);
            return;
        }
        if (direction == 3) {
            setPositive(false); //face to y-axis, forward is 0-
            setSafari(true);
            return;
        }
        if (direction == 4) {
            setPositive(true);//face to x-axis, forward is 0+
            setSafari(false);
        }
    }

    /**
     * @param direction this method give the input of no of turn right
     */
    public static void inputTurnRight(Integer direction) {

        //turn right
        if (direction == 1) {
            setPositive(false); //face to y-axis, forward is 0-
            setSafari(true);
            return;
        }
        if (direction == 2) {
            setPositive(false);//face to x-axis, forward is 0-
            setSafari(false);
            return;
        }
        if (direction == 3) {
            setPositive(true);//face to y-axis, forward is 0+
            setSafari(true);
            return;
        }
        if (direction == 4) {
            setPositive(true);//face to x-axis, forward is 0+
            setSafari(false);
        }
    }
}
