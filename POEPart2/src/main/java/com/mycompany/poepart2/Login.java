/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loginmain;

/**
 *
 * @author lab_services_student
 */
 import java.util.regex.Pattern;
import java.util.regex.Matcher;

 public class Login {
  
    
   //store users details
    private String registeredUsername;
    private String registeredPassword;
    private String firstName;
    private String lastName;

    //ensure that the user name has an underscore and does not have more than 5 characters
    public boolean checkUserName(String username) {
        return username.contains("underscore(_)") && username.length() <= 5;
    }

    
   // must atleast have 8 characters,capital letter, ect 
    public boolean checkPasswordComplexity(String password) {
        String regex = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

   
    //needs to be of the right length and international code (South Africa strats with +27)
    public boolean checkCellPhoneNumber(String cellNumber) {        
        String regex = "^\\+27\\d{9}$";
        return Pattern.matches(regex, cellNumber);
    }

   // Lays out the status message depending on the validation logic.
    public String registerUser(String username, String password, String cell, String fName, String lName) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        } 
        
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        this.registeredUsername = username;
        this.registeredPassword = password;
        this.firstName = fName;
        this.lastName = lName;
        
        return "Successfully captured Username and Password.";
    }

   
    // Checks that entered details match the registered details.
    public boolean loginUser(String enteredUser, String enteredPass) {
        return enteredUser.equals(this.registeredUsername) && enteredPass.equals(this.registeredPassword);
    }

       // gives the success or failure message for the login attempt.
    public String returnLoginStatus(boolean isLoggedIn) {
        if (isLoggedIn) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    public boolean checkPhone(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
    

