# Gym Workout Tracker 
- (catchier name pending)

## What
An application that will let users build their own workouts (equipment dependant) and 
track progress with different exercises over time

Core Features:
- data base of exercises (muscles worked, equipment needed)
- pre build workouts before going to the gym (list of exercises, sets, reps, RPE)
    - also allow users to store these for later reference
- edit/build workouts on the fly while at the gym 
- track weight used during exercise
- reminder about last weight used for a particular exercise

## Who
For people with moderate experience working out (not looking for guidance)
who want workout plans that they have control over

## Why
I have been looking for something similar on my phone and have not found an application that 
is exactly what I want. So, I have been thinking about just doing it myself for a while, and this 
project seems like the perfect excuse to finally commit to it. Obviously I cannot build an
android app like I would have wanted, but hopefully after sorting out all the logic & database management stuff, 
I can turn this idea into an app on my own time. More generally, I have been slowly committing 
more time to planning out workouts, and my current system of using google sheets is showing limitations
(such as having to manually look back to see what weight I used last time, or the complexities of building nice graphs
for weight progression). 

In addition, I think this is a project where I can make a really intuitive and useful design as I 
am the target audience. I know what makes me dislike other apps on the market, and I have a good
idea about how I want to use this app while working out. plus I have a decent amount of experience with different types
of exercises and using technology to aid my workouts.   

## User Stories
### PHASE 1
As a user, I want to be able to:
- create a user
- plan sets, reps, and rpe for an exercise
- make a workout plan (list of exercises)
- view workout plans

Things I did not do because I am bad at time management:
- record weight for each set (done!)
- change an exercise in a workout plan (done!)
### PHASE 2
As a user, I want to be able to:
- save a workout on file
- load a workout from file

A few other things I did from phase 1 as a clever way to procrastinate on doing actually useful things: 
- as a user I want to be able to see my workout history

### PHASE 3
Did it

### PHASE 4
Task 2: My active workout class extends the workout class with additional functionality which fulfills option 2
Task 3: 
- I would refactor the WorkingSet, Workout and ActiveWorkout classes to reduce coupling. For instance, the 
recording of weight and reps (currently hadled by the working set class) only matters for active workouts 
(maybe add an activeWorkingSet class)
- I think the panels of the UI also contain a lot of duplicate code (e.g. size and colour & use of tracker object) which
 could be abstracted away