# Monster Kampfarena
> by Ryan Meneses <br>
> GitHub https://github.com/hiyaryan/ser316-summer2021-C-rmenese1-DP

## Description
> The monsters of the Wildes Land are in a constant battle for survival and only one species
> amongst them have the ability to determine which of them make it, the trainer—the only monster
> with the capacity to tame itself. The monsters depend on the trainers to tame them so that they
> may reach peak power to defend themselves, and the trainers on the monsters whom are the only
> ones physically capable of providing the trainer with resources for their own survival. When a
> monster forms a bond with a trainer they become a code-a-mon, only then does a growing power
> awaken within the monster that just might give them the ability to survive. Life and the very
> nature of survival has since become a spectacle to all those visiting the Wildes Land, with the
> best view at the colossal Kampfarena.

## About
### Git & GitHub
> This program uses git for version control using GitHub as the remote repository. Please clone
> the program locally from the GitHub repository listed above.
>
> Initial commit. The initial commit is based on the Mascotmon code which comes equipped with all
> the build and test tools. The original code is included for testing purposes but is removed in
> later commits to fit the requirements for Monster Kampfarena.

### Design Patterns
> The design patterns used in the Monster Kampfarena simulation are as suggested
    
    - Decorator Pattern
        - Initial monster transformation to a Code-a-mon
        - All other Code-a-mon evolutions
        - Day and night weather 
 
    - Factory Pattern
        - Build trainer entities
        - Builds Code-a-mon entities
      
    - Mediator Pattern
        - Mediates between day and night
        - Mediates the weather
        
        Note: 
            * The mediator pattern runs in a synchronized thread to main. While the simulation
              plays out, it refers to this thread to determine what actions it can perform next.
            
            * A simulation runs on an in-game clock. The clock reads day:time:counter [d:t:c]
                * 1 simulation has 8 days
                * 1 day = 4 times
                * 1 time = 16 counters
                * 1 counter tick = 1 real-world second
                
            * The simuation begins at [0d:1t:0c]
               * Day time is from 1t-2t (the game begins during the day)
               * Night time is from 3t-0t
               
            * The simulation ends at [7d:3t:15c]
               * A basic simulation with two trainers can end it two ways
                    1. Neither trainer can beat the other before [7d:3t:15c]
                    2. A trainer defeats all of another trainers Code-a-mon and finishes it
                       off with a final blow.
               
            * The weather is read twice a day
               * During the day at 1t
               * During the night at 3t
               
            * Depending on the time of the day, determines what a trainer may do
               * During the day a trainer may fight at the Kampfarena
               * During the night a trainer may heal its Code-a-mon
               
            * Depending on the weather, a Code-a-mon may be buffed or debuffed
               
    - Singleton Pattern
        - One player can play the game
        - One mediator can change mediate the Wilde Land
    
### Gradle
> Build and generate Checkstyle and SpotBugs report

    gradle build

> Run program

    gradle run

> Run JUnit tests and create report

    gradle test

> Run Jacoco test report

    gradle jacocoTestReport

> Clean build files

    gradle clean
    
### TravisCI
> This program uses TravisCI for continuous integration testings. Please view the GitHub
> repository for the test report or checkout the link below
>
> https://travis-ci.com/github/hiyaryan/ser316-summer2021-C-rmenese1-DP   

### JUnit Tests
> This program strives for 80% code coverage with JUnit 4 test suites for each class in the test
 package with a test for each method minus setters and getters.
>
> List of Test Suites
>
> * decorator
> * factory
> * kampfarena
> * mediator
> * narrator
> * main

### Screencast
> Please view the following link for a screen cast
>
> Screencast: https://youtu.be/R4XnfzDxY64