/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lab_services_student
 */
package com.mycompany.loginpoe;

import java.util.Random;

public class Message {
    
    private int messageCount;
    private String[] storedMessageIDs;
    private String[] storedMessageHashes;
    private String[] storedRecipients;
    private String[] storedMessages;
    private String[] storedStatus;
    private int currentIndex;
    
    public Message(int maxMessages) {
        this.messageCount = 0;
        this.storedMessageIDs = new String[maxMessages];
        this.storedMessageHashes = new String[maxMessages];
        this.storedRecipients = new String[maxMessages];
        this.storedMessages = new String[maxMessages];
        this.storedStatus = new String[maxMessages];
        this.currentIndex = 0;
    }
    
    public String generateMessageID() {
        Random rand = new Random();
        String messageID = "";
        
        for (int i = 0; i < 10; i++) {
            int digit = rand.nextInt(10);
            messageID = messageID + digit;
        }
        
        return messageID;
    }
    
    public boolean checkMessageID(String messageID) {
        if (messageID.length() <= 10) {
            return true;
        } else {
            return false;
        }
    }
    
    public String checkRecipientCell(String cellNumber) {
        if (cellNumber == null || cellNumber.length() < 3) {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
        
        if (cellNumber.startsWith("+27")) {
            String restOfNumber = cellNumber.substring(3);
            if (restOfNumber.length() == 9 || restOfNumber.length() == 10) {
                boolean allDigits = true;
                for (int i = 0; i < restOfNumber.length(); i++) {
                    char c = restOfNumber.charAt(i);
                    if (c < '0' || c > '9') {
                        allDigits = false;
                    }
                }
                if (allDigits) {
                    return "Cell phone number successfully captured.";
                }
            }
        }
        
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }
    
    public String checkMessageLength(String message) {
        if (message.length() <= 250) {
            return "Message ready to send.";
        } else {
            int extraChars = message.length() - 250;
            return "Message exceeds 250 characters by " + extraChars + "; please reduce the size.";
        }
    }
    
    public String createMessageHash(String messageID, int messageNumber, String message) {
        // Get first two numbers of message ID
        String firstTwo = messageID.substring(0, 2);
        
        // Get first word of message
        String firstWord = "";
        for (int i = 0; i < message.length(); i++) {
            if (message.charAt(i) == ' ') {
                break;
            }
            firstWord = firstWord + message.charAt(i);
        }
        
        // Get last word of message
        String lastWord = "";
        for (int i = message.length() - 1; i >= 0; i--) {
            if (message.charAt(i) == ' ') {
                break;
            }
            lastWord = lastWord + message.charAt(i);
        }
        
        // Reverse the last word because we collected it backwards
        String reversedLastWord = "";
        for (int i = lastWord.length() - 1; i >= 0; i--) {
            reversedLastWord = reversedLastWord + lastWord.charAt(i);
        }
        
        // Combine everything in uppercase
        String hash = firstTwo + ":" + messageNumber + ":" + firstWord + reversedLastWord;
        hash = hash.toUpperCase();
        
        return hash;
    }
    
    public String sendMessage(String recipient, String message, int messageNum) {
        String messageID = generateMessageID();
        String lengthCheck = checkMessageLength(message);
        
        if (!lengthCheck.equals("Message ready to send.")) {
            return lengthCheck;
        }
        
        String recipientCheck = checkRecipientCell(recipient);
        if (!recipientCheck.equals("Cell phone number successfully captured.")) {
            return recipientCheck;
        }
        
        String messageHash = createMessageHash(messageID, messageNum, message);
        
        System.out.println("Message Details");
        System.out.println("Message ID: " + messageID);
        System.out.println("Message Hash: " + messageHash);
        System.out.println("Recipient: " + recipient);
        System.out.println("Message: " + message);
        
        System.out.println("Choose an option:");
        System.out.println("1) Send Message");
        System.out.println("2) Disregard Message");
        System.out.println("3) Store Message to send later");
        
        return "waiting for choice";
    }
    
    public String processChoice(int choice, String messageID, String messageHash, 
                                String recipient, String message) {
        
        if (choice == 1) {
            storedMessageIDs[currentIndex] = messageID;
            storedMessageHashes[currentIndex] = messageHash;
            storedRecipients[currentIndex] = recipient;
            storedMessages[currentIndex] = message;
            storedStatus[currentIndex] = "Sent";
            currentIndex++;
            messageCount++;
            return "Message successfully sent.";
        } 
        else if (choice == 2) {
            return "Press 0 to delete the message.";
        } 
        else if (choice == 3) {
            storedMessageIDs[currentIndex] = messageID;
            storedMessageHashes[currentIndex] = messageHash;
            storedRecipients[currentIndex] = recipient;
            storedMessages[currentIndex] = message;
            storedStatus[currentIndex] = "Stored";
            currentIndex++;
            return "Message successfully stored.";
        }
        else {
            return "Invalid choice.";
        }
    }
    
    public String printMessages() {
        if (messageCount == 0) {
            return "No messages have been sent yet.";
        }
        
        String allMessages = "All Sent Messages";
        for (int i = 0; i < messageCount; i++) {
            allMessages = allMessages + "Message " + (i+1) + ":\n";
            allMessages = allMessages + "ID: " + storedMessageIDs[i] + "\n";
            allMessages = allMessages + "Hash: " + storedMessageHashes[i] + "\n";
            allMessages = allMessages + "Recipient: " + storedRecipients[i] + "\n";
            allMessages = allMessages + "Message: " + storedMessages[i] + "\n";
            allMessages = allMessages + "Status: " + storedStatus[i] + "\n";
        }
        return allMessages;
    }
    
    public int returnTotalMessages() {
        return messageCount;
    }
    
    // Simple JSON storage method (first year friendly)
    public String storeMessageToJSON(String messageID, String messageHash, 
                                      String recipient, String message, String status) {
        String jsonMessage = "{";
        jsonMessage = jsonMessage + "\"messageID\":\"" + messageID + "\",";
        jsonMessage = jsonMessage + "\"messageHash\":\"" + messageHash + "\",";
        jsonMessage = jsonMessage + "\"recipient\":\"" + recipient + "\",";
        jsonMessage = jsonMessage + "\"message\":\"" + message + "\",";
        jsonMessage = jsonMessage + "\"status\":\"" + status + "\"";
        jsonMessage = jsonMessage + "}";
        
        return jsonMessage;
    }
}