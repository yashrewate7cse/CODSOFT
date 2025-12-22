import java.util.Random;
import java.util.Scanner;
public class NumberGame{
    public static String userguess(int randomnumber){
        //Scanner scaninput = new Scanner(System.in);
        System.out.println("enter your guess for random number :");
        while (!inputscanning.hasNextInt()) {
            System.out.println("This is invalid input, please enter only numeric value or check your input again");
            inputscanning.next();         
        }
        int inputval= inputscanning.nextInt();
        if(0<=inputval && inputval<=100){
        if(inputval==randomnumber){
            System.out.println("you won this round keep the spirit up");
            return "good";
        }else if(inputval<randomnumber){
            System.out.println("try again, please guess higher number you are just right there");
            return "bad";
        }else{
            System.out.println("try again, please guess lower number you can catch it");
            return "bad";
        }
        }else{
            System.out.println("Oops, Out of range. Guess number must be between 0 to 100");
            return userguess(randomnumber);
        }
    }
    public static int randomvalues(){
        Random randomnum = new Random();
        int randomnumber=randomnum.nextInt(101);
        //System.out.println(randomnumber);
        return randomnumber;
    }
    public static void gamestart(){
        System.out.println("All the Best");
        int score=0;
        for (int a=1; a<=3; a++){
            System.out.println("Welcome to Round "+a);
            int randomnumber=randomvalues();
            for (int b=1; b<=4; b++){
              String resultval=userguess(randomnumber);
              if (resultval=="good"){
                switch (b) {
                    case 1:
                        score=score+100;
                        break;
                    case 2:
                        score=score+75;
                        break;
                    case 3:
                        score=score+50;
                        break;
                    case 4:
                        score=score+25;
                        break;
                }
                break;
              }
            }
            System.out.println("The correct number was "+randomnumber);
        }
        int scoore = score;
        System.out.println("Congragulate your final score is "+scoore+" points out of 300 points");
        System.out.println("Thank you for playing the game wish you good luck for your next game");
        playagain();
    }
    public static void playagain(){
        //Scanner scancontinue =new Scanner(System.in);
        System.out.println("Do you want to play again? (yes/no)");
        String usercontinue = inputscanning.nextLine();
        if(usercontinue.equals("yes")){
            gamestart();
        }else if(usercontinue.equals("no")){
            System.out.println("Sad to see you go, thank you for playing");
        }else{
            System.out.println("there must be minor mistake while typing, please type & check again");
            playagain();
        }
    }
    private static final Scanner inputscanning = new Scanner(System.in);
    public static void main(String[] args){
        System.out.println("i am yash welcome you to Number Game");
        System.out.println("Rules:");
        String[] rules={
            "There must be Total 3 rounds",
            "Each round has 4 tries to guess correct number",
            "After each round number changes",
            "Take a note that the number is between 0 to 100",
            "Score are only awarded if you win the round",
            "At 1 try=100, 2 try=75, 3 try=50 & last try=25"
        };
        for (int c=0; c<rules.length; c++){
            System.out.println((c+1)+"."+rules[c]);
        }
        gamestart();
        inputscanning.close();
    }
}
