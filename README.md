## Maze Game

A simple maze game with a collection of paths  where you (a.k.a the hero) must find  the exit room. During your journey you will find enemies (goblins, zombies, etc) that will attack you.

The game is a Maven-based Java project  that was build for the command line, but it could be easily  extended to use a GUI since the core is  loosely coupled to the view layer. 

#Prerequisites

Java 8
Maven >= 3.3

## Run it

```
#1. compile it
mvn clean install
```

```
#2. run it
java -jar target/mazegame.jar 
```

```
#coverage report (see target\site\cobertura\index.html)
mvn cobertura:cobertura
```

## Source code packages

```
com.mazegame.core.*: contains the core logic to control the current state of the game. It exposes some API method via the Game class.
```

```
com.mazegame.view.*: contains the logic to interact with the user (command line menus in this case). It uses a single instance of the Game class to get/update the current state of the game.
```

## Extend it

* Use a different board: implement the IBoardProducer interface and inject it into your Game instance.
```
 Game game = Game.newInstance();
 game.setBoardProducer(MyBoardProducer.getInstance());
 game.initState(); 
 
```

* Add a new weapon: implement the IWeapon interface.(Update CreateHeroView to show an option with the new weapon).

* Add a new enemy attack: implement the IEnemyAttackBehavior interface and assign it to an instance of Enemy class:
``` 
  Enemy enemy = Enemy.newInstance("New enemy", initialHp, new MyEnemyBehavior());
```

* Update or add a new fight algorithm:  implement the interface IFightManager and inject it into your Game instance. 
```
 Game game = Game.newInstance();
 game.setFightManager(MyFightManager.getInstance());
 game.initState(); 
 
```

* Add a new way to print the map: implement the interface IPrinterMapStrategy.
```
 Game game = Game.newInstance();
 IPrinterMapStrategy myStrategy = new PrinterMapSimpleTableStrategy(game.getCurrentState());
 myStrategy.print();
 
```
