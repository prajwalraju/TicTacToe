package com.prajwal;

import java.util.*;

public class Main {
    //Array list that holds the player and cpu positions
    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    // Main method
    public static void main(String[] args) {

        // Creating a 2D array to store the game Board
        char [] [] gameBoard = {{'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
                                {'|', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|'},
                                {'|', '-', '-', '-', '+', '-', '-', '-', '+', '-', '-', '-', '|'},
                                {'|', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|'},
                                {'|', '-', '-', '-', '+', '-', '-', '-', '+', '-', '-', '-', '|'},
                                {'|', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|'},
                                {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'}};

        // Print the empty Game Board
        printGame(gameBoard);

        // Loop through the game untill someone wins the game
        while (true) {

            ////////////// Player's Turn ///////////////////

            // Ask Player to input the position in which the x has to be placed on game Board
            System.out.println("Player's Turn");
            System.out.print("Enter the next position : ");
            Scanner scan = new Scanner(System.in);

            // Store the player position in variable playerPos
            var playerPos = scan.nextInt();

            // Ask player to enter a different position if the position entered is already occupied
            while (playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)){
                System.out.print("Position taken, Chose a different position : ");
                playerPos = scan.nextInt();
            }

            // Update the gameBoard and show it to the users
            updatePos(gameBoard, playerPos, "player");
            printGame(gameBoard);
            System.out.println();

            // Check if there are any winners
            var results = checkWinner();

            // If any Users have won exit the loop
            if (results.length() > 0) {
                System.out.println(results);
                break;
            }

            ////////////// CPU's Turn ///////////////////

            // generate a random position to place O on the game Board
            System.out.println(" CPU's Turn");
            Random rand = new Random();
            var cpuPos = rand.nextInt(9) + 1;

            // make cpu to use a different position if the position is already occupied
            while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)){
                cpuPos = rand.nextInt(9) + 1;
            }

            // Update the gameBoard and show it to the users
            updatePos(gameBoard, cpuPos, "cpu");
            printGame(gameBoard);
            System.out.println();

            // Check if there are any winners
            results = checkWinner();

            // If any Users have won exit the loop
            if (results.length() > 0) {
                System.out.println(results);
                break;
            }
        }
    }

    // Method to Print the game Board
    public static void printGame(char [][] gameBoard){

        // Loop through each ROW in the gameBoard
        for(char [] row : gameBoard){

            // Loop through each item in the ROW
            for(char c : row){

                // Print each item in the ROW
                System.out.print(c);
            }

            // Print a new line at the end of a ROW
            System.out.println();
        }
    }

    // Method to update the user position on the Game Board
    public static void updatePos(char [][] gameBoard, int pos, String user){

        // Declaring the symbol
        var symbol = ' ';

        // Assign the proper symbol based on the user
        if(user.equals("player")){
            symbol = 'X';

            // add the position of the player to the playerPosition array
            playerPositions.add(pos);
        }
        else if(user.equals("cpu")){
            symbol = 'O';

            // add the position of the CPU to the cpuPosition array
            cpuPositions.add(pos);
        }

        // insert the appropriate symbol in the gameBoard based on the user Position
        switch (pos){
            case 1:
                gameBoard[1][2] = symbol;
                break;
            case 2:
                gameBoard[1][6] = symbol;
                break;
            case 3:
                gameBoard[1][10] = symbol;
                break;
            case 4:
                gameBoard[3][2] = symbol;
                break;
            case 5:
                gameBoard[3][6] = symbol;
                break;
            case 6:
                gameBoard[3][10] = symbol;
                break;
            case 7:
                gameBoard[5][2] = symbol;
                break;
            case 8:
                gameBoard[5][6] = symbol;
                break;
            case 9:
                gameBoard[5][10] = symbol;
                break;

            // break if default
            default:
                break;
        }
    }

    // Method to chek is any user has won the game
    public static String checkWinner(){

        // declare the winning conditions
        var topRow = Arrays.asList(1,2,3);
        var midRow = Arrays.asList(4,5,6);
        var botRow = Arrays.asList(7,8,9);
        var lefCol = Arrays.asList(1,4,7);
        var midCol = Arrays.asList(2,5,8);
        var rigCol = Arrays.asList(3,6,9);
        var dig1 = Arrays.asList(1,5,9);
        var dig2 = Arrays.asList(3,5,7);

        // create a list of winning conditions
        var winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(lefCol);
        winning.add(midCol);
        winning.add(rigCol);
        winning.add(dig1);
        winning.add(dig2);

        // loop through the winning conditions
        for (var l : winning){

            // check if the Player has satisfied any winning conditions
            if(playerPositions.containsAll(l) ){
                return "************** \nCongrats u won \n**************";
            }

            // check if the CPU has satisfied any winning conditions
            else if(cpuPositions.containsAll(l)){
                return "************* \n  u lost :( \n*************";
            }

            // if all the positions are filled in the gameBoard declare it as a Draw match
            else if(playerPositions.size() + cpuPositions.size() == 9){
                return "************* \n    Draw \n*************";
            }
        }

        return "";
    }
}
