package com.mycompany.loginpoe;

import java.util.Scanner;

public class LoginPOE {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login login = new Login();
        
        System.out.println("Registration");
        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();
        
        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();
        
        String username = "";
        boolean validUsername = false;
        while (!validUsername) {
            System.out.print("Enter username (max 5 chars, must contain _ ): ");
            username = scanner.nextLine();
            if (login.checkUserName(username)) {
                validUsername = true;
                System.out.println("Username successfully captured.");
            } else {
                System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
            }
        }
        
        String password = "";
        boolean validPassword = false;
        while (!validPassword) {
            System.out.print("Enter password (8+ char, 1 capital, 1 number, 1 special): ");
            password = scanner.nextLine();
            if (login.checkPasswordComplexity(password)) {
                validPassword = true;
                System.out.println("Password successfully captured.");
            } else {
                System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
            }
        }
        
        String cellNumber = "";
        boolean validCell = false;
        while (!validCell) {
            System.out.print("Enter cell phone number (e.g., +27831234567): ");
            cellNumber = scanner.nextLine();
            if (login.checkCellPhoneNumber(cellNumber)) {
                validCell = true;
                System.out.println("Cell phone number successfully added.");
            } else {
                System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
            }
        }
        
        String registrationResult = login.registerUser(username, password, cellNumber, firstName, lastName);
        System.out.println(registrationResult);
        
        if (registrationResult.equals("User registered successfully.")) {
            System.out.println("Login");
            String loginUsername = "";
            String loginPassword = "";
            boolean loggedIn = false;
            int attempts = 0;
            
            while (!loggedIn && attempts < 3) {
                System.out.print("Enter username: ");
                loginUsername = scanner.nextLine();
                System.out.print("Enter password: ");
                loginPassword = scanner.nextLine();
                
                System.out.println(login.returnLoginStatus(loginUsername, loginPassword));
                
                if (login.loginUser(loginUsername, loginPassword)) {
                    loggedIn = true;
                    
                   
                    System.out.println("Welcome to QuickChat!");
                    
                    System.out.print("How many messages do you want to send? ");
                    int numMessages = scanner.nextInt();
                    scanner.nextLine();
                    
                    Message messageSystem = new Message(numMessages);
                    int messagesCompleted = 0;
                    
                    boolean quit = false;
                    while (!quit) {
                        System.out.println("Menu");
                        System.out.println("1) Send Messages");
                        System.out.println("2) Show recently sent messages (Coming Soon)");
                        System.out.println("3) Quit");
                        System.out.print("Choose an option: ");
                        
                        int menuChoice = scanner.nextInt();
                        scanner.nextLine();
                        
                        if (menuChoice == 1) {
                            // Send messages - only loop until we reach numMessages
                            while (messagesCompleted < numMessages) {
                                System.out.println("Message " + (messagesCompleted + 1) + " of " + numMessages);
                                
                                System.out.print("Enter recipient cell number (e.g., +27718693002): ");
                                String recipient = scanner.nextLine();
                                
                                System.out.print("Enter your message (max 250 chars): ");
                                String messageText = scanner.nextLine();
                                
                                // Check if message length is valid first
                                String lengthCheck = messageSystem.checkMessageLength(messageText);
                                if (!lengthCheck.equals("Message ready to send.")) {
                                    System.out.println(lengthCheck);
                                    System.out.println("Please try again.");
                                    continue;
                                }
                                
                                // Check if recipient is valid
                                String recipientCheck = messageSystem.checkRecipientCell(recipient);
                                if (!recipientCheck.equals("Cell phone number successfully captured.")) {
                                    System.out.println(recipientCheck);
                                    System.out.println("Please try again.");
                                    continue;
                                }
                                
                                String msgID = messageSystem.generateMessageID();
                                String msgHash = messageSystem.createMessageHash(msgID, messagesCompleted, messageText);
                                
                                System.out.println("Message Details");
                                System.out.println("Message ID: " + msgID);
                                System.out.println("Message Hash: " + msgHash);
                                System.out.println("Recipient: " + recipient);
                                System.out.println("Message: " + messageText);
                                
                                System.out.println("Choose an option:");
                                System.out.println("1) Send Message");
                                System.out.println("2) Disregard Message");
                                System.out.println("3) Store Message to send later");
                                System.out.print("Your choice (1/2/3): ");
                                int choice = scanner.nextInt();
                                scanner.nextLine();
                                
                                String finalResult = messageSystem.processChoice(choice, msgID, msgHash, recipient, messageText);
                                System.out.println(finalResult);
                                
                                String json = messageSystem.storeMessageToJSON(msgID, msgHash, recipient, messageText, 
                                                                              (choice == 1 ? "Sent" : (choice == 3 ? "Stored" : "Discarded")));
                                System.out.println("json aved: " + json);
                                
                                messagesCompleted++;
                            }
                            
                            System.out.println("Total messages sent: " + messageSystem.returnTotalMessages());
                            System.out.println(messageSystem.printMessages());
                        } 
                        else if (menuChoice == 2) {
                            System.out.println("Coming Soon.");
                        } 
                        else if (menuChoice == 3) {
                            quit = true;
                            System.out.println("Goodbye!");
                        }
                        else {
                            System.out.println("Invalid choice. Please try again.");
                        }
                    }
                } else {
                    attempts++;
                    if (attempts < 3) {
                        System.out.println("Try again. Attempts left: " + (3 - attempts));
                    }
                }
            }
        }
        
        scanner.close();
    }
}