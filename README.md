# Elevator-simulator
SYSC 3303 Elevator control system and simulator team project. 

## Iteration-0
- README.txt explaining the names of your files, set up instructions, etc.
- Breakdown of Responsibilities of each team member for this iteration
- UML class diagram and sequence diagrams.
- Detailed set up and test instructions, including test files used
- Code (.java files, all required Eclipse files, etc.)

### Responsibility Breakdown

| Contributor  | Responsibility |
| ------------- | ------------- |
| Ryan F.  | Elevator, Misc. |
| Kaelan  | Sequence, UML, Misc. |
| Ryan G.  | Fixes, Floor, JUnit, Docs, Readme, Misc.|
| Xander | UDP, RecvData, Motor, Door, Misc.|
| Andrew | Scheduler, Buffer, Misc. |

### Set up instructions
1. Unzip file into folder

### Files
#### 1. FloorSubsystem
This file is the floor subsystem of the elevator simulation. 
#### 2. FloorTest
Ths file tests the floor methods. 
#### 3. ElevatorSubsystem
This file is the elevator subsystem of the elevator simulation. 
#### 4. ElevatorSubsystemTest
Ths file tests the elevatorsubsystem and elevator methods. 
#### 5. Scheduler
This file is the scheduler subsystem of the elevator simulation. 
#### 6. SchedulerTest
This file tests schedule's methods. 
#### 7. Button
This file includes the button classes for the elevator and the floor. 
#### 8. ArrivalSensor
This files (not complete) includes the arrival sensor class. 
#### 9. Lamp
This file includes the lamp classes of the floor and elevator.
#### 10. Main
This file when run starts the Elevator_subsystem, Floor_subsystem and Scheduler threads and showcases the information that is sent between the two. 
#### 11. inputFile
This file consists of the format which is indicated in the project requirements. It includes the date, floor number, direction and destination of each request. 
#### 12. Sequence_Diagram
Includes a sample sequence diagram that illustates an abstract model of our submission. 
#### 13. UML_Diagram
Includes the currentUML diagram of all the classes within our submission. 
#### 14. Buffer
This file holds an array of RecvData objects to be easily iterated through and acquired. 
#### 15. RecvData
This file holds the data structure that is passed from one subsystem to another
#### 16. UDP
This file creates sockets for the system that passes packet data to subsystems. 
