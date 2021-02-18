# HelloRobot

Hello Robot CLI application to parse commands and display the result.
These commands will tell the robot to go forwards or backward and turn left or right.  
These commands will be in the format <command><number>. 
Assume this Robot worked as he is in the graph, it default faces to the east. 

### Available commands:
* `F` - move forward 1 unit
* `B` - move backward 1 unit
* `R` - turn right 90 degrees
* `L` - turn left 90 degrees

Hello Robot able to receives a string of commands and will output the robot's distance from its starting point. 
This distance will be the minimum amount of units the robot will need to traverse to get back to its starting point. 

Test this app by unit testing method, black-box, white-box testing methods, and integrating method. 
Tested this app by valid/invalid user input and to see the output and any exception. 
The user input tested null, valid, incorrect format command, and this application able to catch the error and give user feedbacks ablut next step when meet the errors and exception. 

Idea of Design this app
During the process of design and program this app, I put the robot into the starting point and move along or over the x-axis and y-axis. 
Becasue the robot is in the start point and need to varify the initial direction, therefore set the robot face to east in default. 
