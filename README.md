
# Boston University's Metropolitan College  
## CS622 Class Project:

### Jic Jac Joe 

![Version](https://img.shields.io/badge/version-0.0.1-blue.svg?style=flat )
![Platform](https://img.shields.io/badge/platform-CLI-blue.svg?style=flat )
[![Build Status](https://www.travis-ci.com/gchriswill/JicJacJoe.svg?token=8efyA1QMsv1rNCzSQkn1&branch=master)](https://www.travis-ci.com/gchriswill/JicJacJoe)

A tic-tac-toe game written in Java. 
Players the game in PvC and PvP modes via an IDE’s console, and/or CLI, and/or a client app. 
The game helps Java developers to release stress by allowing them to play a simple game, 
within the developer’s own workspace, against the program itself or against another player, 
to take a brake from work, engage with fellow teammates and have fun at the same time without ever leaving you workspace at all. 
The game’s title will be Jic-Jac-Joe.

### Overview Video:

**Overview video is coming soon...**  

[comment]: <> (An in-depth and 15 minutes-long for project overview and usage guide can be found [here]&#40;#&#41;.)

---

## Repository:

This repo holds the source code of Jic-Jac-Joe project for [C.W. Gonzalez Melendez]'s 
MET-CS 622 "Advanced Programming Techniques" class of the Software Development Master's degree 
at Boston University's Metropolitan College.

The purposes of this repo are:

- Providing commit history/changelog of the project, for its evaluation by the instructor.
- Provide versioning control features to the project.
- Use Github's Project, Issues, WIKI and repository management tools.
- Host all the source code remotely on Github as the `remote` repository.
- Host the project website with Github Pages.

---

# Project Details:

### _Project Info_:

**Term:** Spring-O1, 2021  
**Project:** Jic-Jac-Joe  
**Instructor:** [Eric J. Braude](https://www.bu.edu/met/profile/eric-j-braude/)  
**Facilitator:** Kuang-Jung Huang, "Michael"
**Institution:** [Boston University's Metropolitan College](https://www.bu.edu/met/)  
**Course:** [CS622 - Advanced Programming Techniques](http://www.bu.edu/csmet/academic-programs/courses/cs622/)  
**Program:** [Software Development M.Sc. (MSSD)](https://www.bu.edu/met/degrees-certificates/ms-software-development/)  
**Student:** Christopher W. Gonzalez Melendez, D.K.A. "[@gchriswill](https://github.com/gchriswill)" : Student/Engineer/Developer  

**Website:** Jic-Jac-Joe (**Website is currently in the works and coming soon...**)  
**Github Repository:** [Jic-Jac-Joe's Repository](https://github.com/gchriswill/JicJacJoe)

### _Current Status_:

**Performing development for module 2 requirements**

### _Branch Structure/Strategy_:

- master: The released source code for grading
    - module1: All requirements for 622's Module 1 (Skipped due to late delivery)
    - module2: All requirements for 622's Module 2 (GitHub Milestone 2)
    - module3: All requirements for 622's Module 3 (GitHub Milestone 3)
    - module4: All requirements for 622's Module 4 (GitHub Milestone 4)
    - module5: All requirements for 622's Module 5 (GitHub Milestone 5)
    - module6: All requirements for 622's Module 6 (GitHub Milestone 6)
    - feature/*: Branches with feature prefix will contain specific individual and single feature
    - release/*: Branches with release prefix will contain the requirements of a specific module completed and ready for testing  
    

Each branch contains the specific requirements for the state module and completes the designated GitHub Milestone.  
Each GitHub Milestone track all the respective GitHub Issues/requirements for the module/branch.  

### _IDE Platform_:

- [IntelliJ IDEA Ultimate 2020.3.2](https://www.jetbrains.com/idea/)

### _CI/CD Platform_:

- [Travis-CI + Maven + JUnit](https://www.travis-ci.com/github/gchriswill/JicJacJoe)
- Github Actions coming soon...

### _Build Tools_:  

- [Maven](http://maven.apache.org)
- [JUnit](https://junit.org/junit5/)

### _Dependencies_:  

- [Gson](https://github.com/google/gson)
- [JUnit](https://junit.org/junit5/)
- [JetBrains Annotations](https://github.com/JetBrains/java-annotations)

### _Development Devices_:

- MacBook Pro (15 Inch, 2017)

### _Deployment Targets_:

- CLI
- IDE's Console

### _Test Devices_:

- MacBook Pro (15 Inch, 2017)  

### _Google Drive Location_:

[Jic-Jac-Joe's Documents Folder Location](https://drive.google.com/drive/u/1/folders/0ABEHSS4VkZJqUk9PVA)  

The project's documents folder includes:

- Requirements/
- Resources/
- Diagrams/
- Flows/

---

# User Stories:  

- Jic-Jac-Joe should be able to run in IDE’s console and/or the CLI
- Jic-Jac-Joe should be able to welcome the players when ran initially
- Jic-Jac-Joe should be able to be played in 1 game mode: PvC (PvP for later)
- Jic-Jac-Joe should be able to start a new game session
- Jic-Jac-Joe should be able to display the game board graphically in the console and/or CLI
- Jic-Jac-Joe should be able to play itself against the player
- Jic-Jac-Joe should be able to determine the winner and looser
- Jic-Jac-Joe should be able to detect unintended input from the user
- Jic-Jac-Joe should be able to handle unintended input from the user
- Jic-Jac-Joe should be able to display error to user if it receives unintended input
- Jic-Jac-Joe should be able to display guidance to user if an error was encountered

# Feature Sets:

| Core          | Support       | Supplemental  |
| ------------- | ------------- | ------------- |
| Start         | Menu          | Credits       |
| Play          | Save          | Restart       |
| Win/Loose     | Load          | Exit          |
| End           |               |               |


# Milestones Breakdown for Modules:

Bellow, here are the Milestone cards on the and schedule for this project. 
Each milestone represents each module requirements goals for class CS 622.
For more information, please visit the Project and Wiki of this repository.

- [x] Milestone 1 (Skipped due to late delivery)
- [x] [Milestone 2](https://github.com/gchriswill/JicJacJoe/milestone/1)
- [ ] [Milestone 3](https://github.com/gchriswill/JicJacJoe/milestone/2)
- [ ] Milestone 4
- [ ] Milestone 5
- [ ] Milestone 6

--- 

# Running Jic Jac Joe:

### IMPORTANT NOTES  

The project has CI/CD integration already via Travis-CI using Maven build tool, this is for 
running and validating the project's test with JUnit in every update/commit pushed to the repository. 

### Running

Basically, the project should run as it is, without any additional configuration, in the CLI or IDE's console.  

To run the game in the CLI (a Unix Bash shell), navigate to the working directory and type this command in your unix terminal:  

`java -cp <LOCAL WORKING DIRECTORY>/out/production/JicJacJoe edu.bu.met.CS622.JicJacJoe.Main`

Don't forget to add your local path to the command.
Also, don't forget to build the project first...

Please note that player-VS-player mode will not be available for module 1.

There are 3 convenient commands available at different stages:  

- `Save` Available only during a current game session  
- `Restart` Available at all time except in the main menu
- `Exit` Available during all times




