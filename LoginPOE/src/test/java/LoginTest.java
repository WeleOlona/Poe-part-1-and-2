/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.loginpoe;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class LoginTest {
    
    @Test
    public void testUserNameCorrectlyFormatted() {
        Login myLogin = new Login();
        boolean result = myLogin.checkUserName("kyl_1");
        assertEquals(true, result);
    }
    
    @Test
    public void testUserNameIncorrectlyFormatted() {
        Login myLogin = new Login();
        boolean result = myLogin.checkUserName("kyle!!!!!!!");
        assertEquals(false, result);
    }
    
    @Test
    public void testPasswordMeetsRequirements() {
        Login myLogin = new Login();
        boolean result = myLogin.checkPasswordComplexity("Ch&sec@ke991");
        assertEquals(true, result);
    }
    
    @Test
    public void testPasswordDoesNotMeetRequirements() {
        Login myLogin = new Login();
        boolean result = myLogin.checkPasswordComplexity("password");
        assertEquals(false, result);
    }
    
    @Test
    public void testCellPhoneCorrectlyFormatted() {
        Login myLogin = new Login();
        boolean result = myLogin.checkCellPhoneNumber("+27838968976");
        assertEquals(true, result);
    }
    
    @Test
    public void testCellPhoneIncorrectlyFormatted() {
        Login myLogin = new Login();
        boolean result = myLogin.checkCellPhoneNumber("08966553");
        assertEquals(false, result);
    }
    
    @Test
    public void testRegisterUserUsernameErrorMessage() {
        Login myLogin = new Login();
        String result = myLogin.registerUser("kyle!!!!!!!", "Ch&sec@ke991", "+27831234567", "John", "Doe");
        assertEquals("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.", result);
    }
    
    @Test
    public void testRegisterUserPasswordErrorMessage() {
        Login myLogin = new Login();
        String result = myLogin.registerUser("kyl_1", "password", "+27831234567", "John", "Doe");
        assertEquals("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.", result);
    }
    
    @Test
    public void testRegisterUserCellErrorMessage() {
        Login myLogin = new Login();
        String result = myLogin.registerUser("kyl_1", "Ch&sec@ke991", "08966553", "John", "Doe");
        assertEquals("Cell phone number incorrectly formatted or does not contain international code.", result);
    }
    
    @Test
    public void testRegisterUserSuccess() {
        Login myLogin = new Login();
        String result = myLogin.registerUser("kyl_1", "Ch&sec@ke991", "+27831234567", "John", "Doe");
        assertEquals("User registered successfully.", result);
    }
    
    @Test
    public void testLoginSuccessful() {
        Login myLogin = new Login();
        myLogin.registerUser("kyl_1", "Ch&sec@ke991", "+27831234567", "Jane", "Smith");
        boolean result = myLogin.loginUser("kyl_1", "Ch&sec@ke991");
        
        assertEquals(true, result);
    }
    
    @Test
    public void testLoginFailed() {
        Login myLogin = new Login();
        myLogin.registerUser("kyl_1", "Ch&sec@ke991", "+27831234567", "Jane", "Smith");
        boolean result = myLogin.loginUser("wrong", "wrong");
        
        assertEquals(false, result);
    }
    
    @Test
    public void testReturnLoginStatusSuccessMessage() {
        Login myLogin = new Login();
        myLogin.registerUser("kyl_1", "Ch&sec@ke991", "+27831234567", "Jane", "Smith");
        String result = myLogin.returnLoginStatus("kyl_1", "Ch&sec@ke991");
        
        assertEquals("Welcome Jane, Smith it is great to see you again.", result);
    }
    
    @Test
    public void testReturnLoginStatusFailMessage() {
        Login myLogin = new Login();

        myLogin.registerUser("kyl_1", "Ch&sec@ke991", "+27831234567", "Jane", "Smith");
        String result = myLogin.returnLoginStatus("wrong", "wrong");
        assertEquals("Username or password incorrect, please try again.", result);
    }
}