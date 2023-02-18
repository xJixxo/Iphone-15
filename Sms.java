package Home_Project;

import java.util.ArrayList;
import java.util.Scanner;

public class Sms extends PhoneBook {

    Scanner scanner;

    public Sms(ArrayList<Contact> pb) {
        this.phoneBook = pb;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        boolean Exit = false;
        while (!Exit) {
            System.out.println("Hello and Welcome to the main menu of your personal SMS app." +
                    newLine + "Please enter the number of one of the options below :" +
                    newLine + "[1] Send a message to someone" +
                    newLine + "[2] Delete a chat with someone" +
                    newLine + "[3] View the chat with someone" +
                    newLine + "[4] Locate chats with sentence that have been send" +
                    newLine + "[5] View every chat you have" +
                    newLine + "[6] Exit" +
                    newLine + newLine + "Enter your input here: ");
            String num = scanner.nextLine();
            switch (num) {
                case "1" -> {

                }
                case "2" -> {

                }
                case "3" -> {

                }
                case "4" -> {

                }
                case "5" -> {

                }
                case "6" -> {
                    Exit = true;
                    System.out.println("Exiting the SMS");
                }
            }
        }
    }
}