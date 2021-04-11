package com.prajwal;

import java.util.*;

public class Main {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
    public static void main(String[] args) {

        char [] [] gameBoard = {{'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
                                {'|', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|'},
                                {'|', '-', '-', '-', '+', '-', '-', '-', '+', '-', '-', '-', '|'},
                                {'|', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|'},
                                {'|', '-', '-', '-', '+', '-', '-', '-', '+', '-', '-', '-', '|'},
                                {'|', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|'},
                                {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'}};

        printGame(gameBoard);
        while (true) {
            System.out.println("Player's Turn");
            Scanner scan = new Scanner(System.in);
            System.out.print("Enter the next position : ");
            var playerPos = scan.nextInt();
            while (playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)){
                System.out.print("Position taken, Chose a different position : ");
                playerPos = scan.nextInt();
            }

            updatePos(gameBoard, playerPos, "player");

            printGame(gameBoard);
            System.out.println();

            var results = checkWinner();
            if (results.length() > 0) {
                System.out.println(results);
                break;
            }

            System.out.println(" CPU's Turn");
            Random rand = new Random();
            var cpuPos = rand.nextInt(9) + 1;
            while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)){
                cpuPos = rand.nextInt(9) + 1;
            }
            updatePos(gameBoard, cpuPos, "cpu");
            printGame(gameBoard);
            System.out.println();
            results = checkWinner();
            if (results.length() > 0) {
                System.out.println(results);
                break;
            }
        }
    }
    public static void printGame(char [][] gameBoard){
        for(char [] row : gameBoard){
            for(char c : row){
                System.out.print(c);
            }
            System.out.println();
        }
    }
    public static void updatePos(char [][] gameBoard, int pos, String user){
        var symbol = ' ';
        if(user.equals("player")){
            symbol = 'X';
            playerPositions.add(pos);
        }
        else if(user.equals("cpu")){
            symbol = 'O';
            cpuPositions.add(pos);
        }
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
            default:
                break;
        }
    }
    public static String checkWinner(){
        var topRow = Arrays.asList(1,2,3);
        var midRow = Arrays.asList(4,5,6);
        var botRow = Arrays.asList(7,8,9);
        var lefCol = Arrays.asList(1,4,7);
        var midCol = Arrays.asList(2,5,8);
        var rigCol = Arrays.asList(3,6,9);
        var dig1 = Arrays.asList(1,5,9);
        var dig2 = Arrays.asList(3,5,7);

        var winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(lefCol);
        winning.add(midCol);
        winning.add(rigCol);
        winning.add(dig1);
        winning.add(dig2);

        for (var l : winning){
            if(playerPositions.containsAll(l) ){
                return "************** \nCongrats u won \n**************";
            }
            else if(cpuPositions.containsAll(l)){
                return "************* \n  u lost :( \n*************";
            }
            else if(playerPositions.size() + cpuPositions.size() == 9){
                return "************* \n    Draw \n*************";
            }
        }

        return "";
    }
}
