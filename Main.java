package Home_Project;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Class 1/5
 * Main - runner
 */
public class Main {
    /**
     * Method that recives any arraylist of contacts and print it
     * @param pb any array list of contacts you would like to be printed
     */
    static void printPhoneBook(ArrayList<Contact> pb) {
        String newLine = System.getProperty("line.separator");
        for (Contact contact : pb) {
            System.out.println(contact.getFullName() + " - " + contact.phoneNumber + " - " + contact.currentCompany + newLine);
        }

    }


    public static void main(String[] args) throws IOException {





        PhoneBook pb1 =new PhoneBook();
        boolean Exit=false;
        int trys=0;
        Scanner input=new Scanner(System.in);
        String newLine = System.getProperty("line.separator");
        while (!Exit) {
            System.out.println("Hello and Welcome to the main menu of your personal Phone Book." +
                    newLine + "Please enter the number of one of the options below :" +
                    newLine + "[1] Add a new Contact." +
                    newLine + "[2] Delete a contact by his name" +
                    newLine + "[3] Show me all the contacts by order" +
                    newLine + "[4] Locate a contact" +
                    newLine + "[5] Sort the Phone Book by name in ascending order" +
                    newLine + "[6] Sort the Phone Book by phone number in descending order" +
                    newLine + "[7] Arrange the phone book in reverse order" +
                    newLine + "[8] Remove duplicates (Contacts with the same name phone number)" +
                    newLine + "[9] Save the data of the phone book in a text file" +
                    newLine + "[10] Load data of a phone book from a text file" +
                    newLine + "[11] Exit"+
                    newLine + newLine+"Enter your input here: ");
            String num = input.nextLine();
            switch (num) {
                case "1" -> {
                    pb1.addNewContact();
                    System.out.println("Contact Has Been Added Succesfully");

                }
                case "2" -> {
                    boolean found=false;
                    while (!found) {
                        System.out.println("Enter the full name of the contact you would like to delete: ");
                        String n = input.nextLine();
                        for (int a = 0; a < pb1.phoneBook.size(); a++) {
                            if (pb1.phoneBook.get(a).getFullName().equals(n)) {
                                pb1.phoneBook.remove(a);
                                System.out.println("Contact Has Been Deleted Succesfully");
                                System.out.println("Press ENTER to return to the main menu");
                                Scanner scan=new Scanner(System.in);
                                scan.nextLine();
                                found=true;
                                break;
                            }
                        }
                        if (!found) {
                            System.out.println("There's no such contact"+
                                    newLine+"If the contact does not have middle name - try not add another space between the first and last name"+
                                    newLine+"Would you like to try again?"+
                                    newLine+"[1] Yes"+
                                    newLine+"[2] No"+newLine+
                                    newLine+"Enter your input here: ");
                            Scanner scan1=new Scanner(System.in);
                            if(scan1.nextLine()=="2") {
                                found=true;
                            } else if (input.nextLine()=="1") {
                                continue;
                            }

                            }
                        }

                    }
                    case "3"-> {
                        printPhoneBook(pb1.phoneBook);
                        System.out.println("Press ENTER to return to the main menu");
                        Scanner scan=new Scanner(System.in);
                        scan.nextLine();
                        }

                    case "4"-> {
                        boolean f=false;
                        while (!f) {
                            Scanner nameIn = new Scanner(System.in);
                            System.out.println("Enter the name of the contact you would like to locate: ");
                            String name = nameIn.nextLine();
                            ArrayList<Contact> founds = pb1.getContact(name);
                            if (founds.size() > 0) {
                                for (int i = 0; i < founds.size(); i++) {
                                    printPhoneBook(founds);
                                }
                                System.out.println("Press ENTER to return to main menu");
                                Scanner scanner = new Scanner(System.in);
                                scanner.nextLine();
                                f=true;
                            } else {
                                System.out.println("Sorry there is no such contact" +
                                        newLine + "Would you like to try again?" +
                                        newLine + "[1] Yes" +
                                        newLine + "[2] No" + newLine +
                                        newLine + "Enter your input here: ");
                                if (input.nextInt() == 1) {
                                    f = false;
                                } else if (input.nextInt() == 2) {
                                    f = true;
                                    break;
                                }
                            }
                        }
                }

                case "5" -> {

                    Collections.sort(pb1.phoneBook,new NameSorter());
                    printPhoneBook(pb1.phoneBook);
                    System.out.println("Press ENTER to return to the main menu");
                    Scanner scan=new Scanner(System.in);
                    scan.nextLine();


                }
                case "6" -> {
                    Collections.sort(pb1.phoneBook,new PhoneRevSorter());
                    printPhoneBook(pb1.phoneBook);
                    System.out.println("Press ENTER to return to the main menu");
                    Scanner scan=new Scanner(System.in);
                    scan.nextLine();
                }
                case "7" -> {

                    System.out.println("Before the reversion: "+newLine);
                    printPhoneBook(pb1.phoneBook);
                    Collections.reverse(pb1.phoneBook);
                    System.out.println("After the reversion: "+newLine);
                    printPhoneBook(pb1.phoneBook);
                    System.out.println("Press ENTER to return to the main menu");
                    Scanner inpt=new Scanner(System.in);
                    inpt.nextLine();
                }
                case "8" -> {
                    System.out.println("Looking for Duplicates");
                    ArrayList<Contact> dups=new ArrayList<>();
                    pb1.phoneBook.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting())).entrySet().stream().filter(e -> e.getValue() > 1L).map(e -> e.getKey()).collect(Collectors.toList()).forEach(contact -> dups.add(contact));
                    printPhoneBook(dups);
                    for (Contact c:dups) {
                        pb1.phoneBook.remove(c);
                    }
                    System.out.println("Press ENTER to return to the main menu");
                    Scanner inpit=new Scanner(System.in);
                    inpit.nextLine();
                }
                case "9" -> {
                    Scanner input1=new Scanner(System.in);
                    System.out.println("Enter the path of the folder you would like the file to be saved");
                    String path=input1.nextLine();
                    System.out.println("Enter the name you would like the file to be called(for a text file add .txt at the end) : ");
                    String fileName=input1.nextLine();
                    PrintWriter writer = null;
                    try {
                        writer = new PrintWriter(new FileOutputStream(path+"\\"+fileName));
                        for (Contact cont : pb1.phoneBook)
                            writer.println(cont.firstName+","+cont.middleName+","+cont.lastName+","+cont.phoneNumber+","+cont.currentCompany+newLine);
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Press ENTER to return to the main menu");
                    Scanner inpt=new Scanner(System.in);
                    inpt.nextLine();
                }
                case "10" -> {
                    Scanner path=new Scanner(System.in);
                    System.out.println("Enter the path for the file: ");
                    String filePath = path.nextLine();
                    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            String[] parts = line.split(",");
                            Contact contact = new Contact(parts[0], parts[1], parts[2],parts[3]);
                            pb1.addContact(contact);
                            System.out.println(contact.firstName+" has been added to the phonebook");
                        }
                        System.out.println(newLine+"Press ENTER to return to the main menu");
                        Scanner inpiit=new Scanner(System.in);
                        inpiit.nextLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                case "11" -> {
                    Exit = true;
                    System.out.println("Exiting the Phone Book");
                }
                default -> {
                    if (trys == 3) {
                        Exit = true;
                        System.out.println("Exiting the Phone Book");
                    } else {
                        System.out.println("Sorry the option you've chosen is not available");
                        trys++;
                        System.out.println("Press ENTER to return to the main menu");
                        Scanner inpat = new Scanner(System.in);
                        inpat.nextLine();
                    }
                }
            }

        }

    }
}
