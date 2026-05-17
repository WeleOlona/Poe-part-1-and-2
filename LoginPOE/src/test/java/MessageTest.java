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



public class MessageTest {
    
    private Message message;
    
   
    
    @Test
    public void testCheckMessageLength_Success() {
        String shortMessage = "Hi Mike, can you join us for dinner tonight?";
        String result = message.checkMessageLength(shortMessage);
        assertEquals("Message ready to send.", result);
    }
    
    @Test
    public void testCheckMessageLength_Failure() {
        String longMessage = "";
        for (int i = 0; i < 300; i++) {
            longMessage = longMessage + "a";
        }
        String result = message.checkMessageLength(longMessage);
        assertTrue(result.contains("exceeds 250 characters by 50"));
    }
    
    @Test
    public void testCheckRecipientCell_Success() {
        String result = message.checkRecipientCell("+27718693002");
        assertEquals("Cell phone number successfully captured.", result);
    }
    
    @Test
    public void testCheckRecipientCell_Failure() {
        String result = message.checkRecipientCell("08575975889");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", result);
    }
    
    @Test
    public void testCreateMessageHash() {
        String messageID = "1234567890";
        int messageNumber = 0;
        String messageText = "Hi Mike, can you join us for dinner tonight?";
        
        String hash = message.createMessageHash(messageID, messageNumber, messageText);
        
        // First two numbers of ID are "12", message number is 0, first word "HI", last word "TONIGHT?"
        assertEquals("12:0:HITONIGHT?", hash);
    }
    
    @Test
    public void testGenerateMessageID() {
        String messageID = message.generateMessageID();
        assertEquals(10, messageID.length());
        assertTrue(messageID.matches("\\d+"));
    }
    
    @Test
    public void testProcessChoice_Send() {
        String result = message.processChoice(1, "1234567890", "12:0:HITONIGHT", "+27718693002", "Test message");
        assertEquals("Message successfully sent.", result);
    }
    
    @Test
    public void testProcessChoice_Disregard() {
        String result = message.processChoice(2, "1234567890", "12:0:HITONIGHT", "+27718693002", "Test message");
        assertEquals("Press 0 to delete the message.", result);
    }
    
    @Test
    public void testProcessChoice_Store() {
        String result = message.processChoice(3, "1234567890", "12:0:HITONIGHT", "+27718693002", "Test message");
        assertEquals("Message successfully stored.", result);
    }
    
    @Test
    public void testReturnTotalMessages() {
        message.processChoice(1, "1111111111", "11:0:HITEST", "+27711111111", "Test 1");
        message.processChoice(1, "2222222222", "22:1:HITEST2", "+27722222222", "Test 2");
        assertEquals(2, message.returnTotalMessages());
    }
    
    @Test
    public void testStoreMessageToJSON() {
        String json = message.storeMessageToJSON("12345", "12:0:HITEST", "+27712345678", "Hello", "Sent");
        assertTrue(json.contains("\"messageID\":\"12345\""));
        assertTrue(json.contains("\"status\":\"Sent\""));
    }
}