# Spark-Gradle-Template
A barebones project with scala, apache spark built using gradle. Can be used when you need a spark skeleton project for fast bootstrapping.

## Libraries Included
- Spark - 2.1.0
- Scala - 2.11.8

## Build and Demo process

### Build
`./gradlew clean build`
### Run
`./gradlew run`
### All Together
`./gradlew clean run`


## What the demo does?
Take a look at *src->main->scala->template->spark* directory

We have two Items here. 

The trait `InitSpark` which is extended by any class that wants to run spark code. This trait has all the code for initialization. I have also supressed the logging to only error levels for less noise.

The file `Main.scala` has the executable class `Main`. 
In this class, I do 4 things

- Print spark version.
- Find sum from 1 to 100 (inclusive).
- Read a csv file into a structured `DataSet`. 
- Find average age of persons from the csv.
 
## Issues or Suggestions

- Raise one on github
- Send me a mail -> fahemad3+github @ gmail dot com (Remove the spaces and dot = .)
