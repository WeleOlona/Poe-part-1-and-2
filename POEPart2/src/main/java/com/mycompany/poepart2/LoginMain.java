/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.loginmain;

/**
 *
 * @author lab_services_student
 */
import java.util.Scanner;
public class LoginMain {

        public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Login authSystem = new Login();

        System.out.println("--- User Registration ---");
        
        // 1. Capture Registration Details
        System.out.print("Enter First Name: ");
        String fName = input.nextLine();       
        System.out.print("Enter Last Name: ");
        String lName = input.nextLine();        
        System.out.print("Enter Username (must contain underscore(_) and be <= 5 characters): ");
        String user = input.nextLine();        
        System.out.print("Enter Password (8+ characters, Uppercase, Number, Special Characters): ");
        String pass = input.nextLine();
        System.out.print("Enter Cell Phone Number (e.g., +27838968976): ");
        String cell = input.nextLine();

        //continue and display message
        String regMessage = authSystem.registerUser(user, pass, cell, fName, lName);
        System.out.println("\n" + regMessage);

       //if the registration is a success
        if (regMessage.equals("Username and Password successfully captured.")) {
            
            System.out.println("\n--- User Login ---");
            
            System.out.print("Enter Username: ");
            String loginUser = input.nextLine();
            
            System.out.print("Enter Password: ");
            String loginPass = input.nextLine();

         //check if the credentiials are valid
            boolean loginSuccess = authSystem.loginUser(loginUser, loginPass);
            
          //show final status message
            String statusMessage = authSystem.returnLoginStatus(loginSuccess);
            System.out.println("\n" + statusMessage);
            
        } else {
            System.out.println("\nRegistration failed. Please restart the application and follow the formatting rules.");
        }

        input.close();
    }
}
    

