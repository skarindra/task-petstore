# API Automation Test for Petstore

### Stacks
- Java 18
- Gradle 8.3
- Serenity BDD https://serenity-bdd.info (I use Serenity because it's much more simpler to configure rather than using pure Cucumber, it's like a wrapper for all automation libraries)

### The project directory structure
The project follows the standard directory structure used in most Serenity projects:
```Gherkin
+ src
  + test
    + java                          Test runners and supporting code
      + com.petstore
        + stepdefinitions           consist of class to define the action that can be done by each steps in the feature file.
        + util                      consist of classes with helper methods (Constant and DataUtils).
        - CucumberTestRunner.java   Cucumber runner
    + resources
      + features                    Feature files
      - junit-platform.properties   Here we can put all Cucumber related or Junit related settings
      - logback-test.xml            Logger setting
+ gradle
  + wrapper                         consist of gradle wrapped jar and properties
- build.gradle                      used to define project, tasks and dependency controller
- settings.gradle                   used to define all included submodules and to mark the directory root of a tree of modules
- gradlew and gradlew.bat           executable file to run the task with gradle wrapper
+ target
  + site
    + serenity
      - index.html                  Serenity Report file
  + logs
    -automation.log                 Log file for automation
```

### How to run the test
- Run all test :
    - ``./gradlew clean test``
    - This will run all the feature file.
    - Or you can run it from CucumberTestSuite class also.
- Run specific tags :
    - ``./gradlew clean test -Dcucumber.filter.tags="@tag-name"``
    - This will run the scenario that has the mentioned tags.

### How to see the report
- Open ``target/site/serenity/index.html``, it will open HTML report with all detailed test cases, test data, execution time and log (if there's any error)