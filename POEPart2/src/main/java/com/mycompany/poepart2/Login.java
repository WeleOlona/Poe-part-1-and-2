package com.mycompany.loginpoe;

import java.util.regex.Pattern;

public class Login {
    
    private String storedUsername;
    private String storedPassword;
    private String storedCellNumber;
    private String firstName;
    private String lastName;
    
    public Login() {
        this.storedUsername = "";
        this.storedPassword = "";
        this.storedCellNumber = "";
        this.firstName = "";
        this.lastName = "";
    }
    
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }
    
    public boolean checkPasswordComplexity(String password) {
        boolean hasLength = password.length() >= 8;
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;
        
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isUpperCase(c)) {
                hasCapital = true;
            }
            if (Character.isDigit(c)) {
                hasNumber = true;
            }
            if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }
        
        return hasLength && hasCapital && hasNumber && hasSpecial;
    }
    
 public boolean checkCellPhoneNumber(String cellNumber) {
    if (cellNumber.startsWith("+27")) {
        String numbersOnly = cellNumber.substring(3);
        if (numbersOnly.length() == 9 || numbersOnly.length() == 10) {
            return true;
        }
    }
    return false;
}
    
    public String registerUser(String username, String password, String cellNumber, 
                               String fName, String lName) {
        boolean validUsername = checkUserName(username);
        boolean validPassword = checkPasswordComplexity(password);
        boolean validCell = checkCellPhoneNumber(cellNumber);
        
        if (!validUsername) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        
        if (!validPassword) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        
        if (!validCell) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        
        this.storedUsername = username;
        this.storedPassword = password;
        this.storedCellNumber = cellNumber;
        this.firstName = fName;
        this.lastName = lName;
        
        return "User registered successfully.";
    }
    
    public boolean loginUser(String username, String password) {
        return this.storedUsername.equals(username) && this.storedPassword.equals(password);
    }
    
    public String returnLoginStatus(String username, String password) {
        if (loginUser(username, password)) {
            return "Welcome " + this.firstName + " " + this.lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
    
    public String getStoredUsername() {
        return storedUsername;
    }
    
    public String getStoredPassword() {
        return storedPassword;
    }
    
    public String getStoredCellNumber() {
        return storedCellNumber;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
}