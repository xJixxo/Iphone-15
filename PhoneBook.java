package Home_Project;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class 3/5
 * Phonebook class that contains arraylist of Contact objects which is the main phonebook of the user
 */
public class PhoneBook {
    /**
     * line breaker
     */
    String newLine = System.getProperty("line.separator");
    public ArrayList<Contact> phoneBook = new ArrayList<>();

    /**
     * method that add a new contact by the input of the user
     */

    public void addNewContact() {

        Scanner input = new Scanner(System.in);
        boolean added = false;
        String firstName = "";
        String middleName = "";
        String lastName = "";
        String phone = "";
        String company = "";

        while (!added) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the first name of the new contact: ");
                if(scanner.hasNext("[A-Za-z]*")) {
                    firstName = scanner.next();
                }else {
                    System.out.println("Error, please try again");
                    continue;
                }
            System.out.println("Enter the middle name of the new contact (if you don't have one just press ENTER): ");
                if(scanner.hasNext("[A-Za-z]*") || scanner.hasNext("")) {
                    middleName = scanner.next();
                }else{
                    System.out.println("Error, please try again");
                    continue;
                }
            System.out.println("Enter the last name of the new contact: ");
                if(scanner.hasNext("[A-Za-z]*")) {
                    lastName = scanner.next();
                }else {
                    System.out.println("Error, please try again");
                    continue;
                }
            System.out.println("Enter the phone of the new contact: ");
                if(scanner.hasNext()){
                    int num=scanner.nextInt();
                    if(num>0){
                        phone=String.valueOf(num);
                    }
                }else {
                    System.out.println("Error, please try again");
                    continue;
                }
            System.out.println("Enter the current company of the new contact: ");
                if(scanner.hasNext("[A-Za-z]*")) {
                    company = scanner.next();
                }else {
                    System.out.println("Error, please try again");
                    continue;
                }
            int i = 0;
            for (Contact contact : this.phoneBook) {
                if (contact.getFullName().equals(firstName + " " + middleName + " " + lastName)) {
                    System.out.println("Error, there is already a contact by that full name");
                    break;
                } else {
                    i++;
                }
                ;
            }
            if (i == this.phoneBook.size()) {
                Contact c1 = new Contact(firstName, middleName, lastName, phone, company);
                this.phoneBook.add(c1);
                added = true;
            } else if (!added) {
                System.out.println("Would you like to try again?" +
                        newLine + "[1] Yes" +
                        newLine + "[2] No" + newLine +
                        newLine + "Enter your input here: ");
                Scanner scan1 = new Scanner(System.in);
                if (scan1.nextLine() == "2") {
                    added = true;
                } else if (input.nextLine() == "1") {
                    continue;
                }

            }
        }
    }

    /**
     * method for locating a specific contact from the arraylist
     * @param name the first object out of the arraylist with that name will be returned
     * @return the info about that contact
     */
    public ArrayList<Contact> getContact(String name) {
        ArrayList<Contact> founds=new ArrayList<>();
        for (Contact contact:this.phoneBook){
            if (contact.getFullName().equals(name)){
                founds.add(contact);
            }
        }
        return founds;
    }

    public void addContact(Contact contact) {
        this.phoneBook.add(contact);
    }
}


