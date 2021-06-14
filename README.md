# Monster Kampfarena
> by Ryan Meneses <br>
> GitHub https://github.com/hiyaryan/ser316-summer2021-C-rmenese1-DP

## Description
> The monsters of Kampfarena are in a constant battle for survival and only one species amongst
> them have the ability to determine which of them make it, the trainer. The trainers are
> the only monsters whom can tame themselves yet depend on other monsters to provide them
> resources for their own survival. When a monster forms a bond with a trainer they become a
> code-a-mon, only then does a power awaken within the monster that just might give them the
> ability to survive.

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
      This pattern is used for code-e-mon evolutions
 
    - Factory pattern
      Builds codemon and trainer entities
      
    - Mediator pattern
      Mediates the simulation where for each tick an event occurs.
    
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
> * \<SUITE-1\>
> * \<SUITE-2\>
> * \<SUITE-3\>

### Screencast
> Please view the following link for a short screen cast
>
> \<VIDEO-LINK\>