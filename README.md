# tictactoe
Console Springboot based tictactoe game

To run game please git clone repo.

Open it in some sort of IDE, make sure to run build solution with
maven mvn clean install or mvn package if former doesn't work.

It is important to pass arguments to application
first parameter is number of players, second and third are
width and height respectively. But different than square sizes
of board might cause issues still.

After building up jar solution. Please double click jar and if that does not work
please open cmd position yourself in root of your project and run:
java -jar target\tictactoe-0.0.1-SNAPSHOT.jar 2 3 3
