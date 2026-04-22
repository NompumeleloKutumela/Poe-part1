package chatappp;

import java.util.regex.Pattern;

public class UserAuth {
    
    // Where we store the user's info
    private String storedUsername;
    private String storedPassword;
    private String storedFirstName;
    private String storedLastName;
    private String storedPhoneNumber;
    
    // This checks if username is good
    // Rule: must have _ and be 5 letters or less
    public boolean checkUserName(String username) {
        if (username == null) return false;
        return username.contains("_") && username.length() <= 5;
    }
    
    // This checks if password is strong
    // Rules: 8+ chars, capital letter, number, special character
    public boolean checkPasswordComplexity(String password) {
        if (password == null) return false;
        
        boolean longEnough = password.length() >= 8;
        boolean hasCapital = password.matches(".*[A-Z].*");
        boolean hasNumber = password.matches(".*[0-9].*");
        boolean hasSpecial = password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\",./<>?~`].*");
        
        return longEnough && hasCapital && hasNumber && hasSpecial;
    }
    
    // This checks if phone number is a valid SA number
    // Rule: starts with +27 and has 9 digits after
    // I found this pattern online: https://regex101.com/
    public boolean checkCellPhoneNumber(String phone) {
        if (phone == null) return false;
        return Pattern.matches("^\\+27[0-9]{9}$", phone);
    }
    
    // This registers the user if all info is correct
    public String registerUser(String username, String password, String firstName, String lastName, String phone) {
        StringBuilder message = new StringBuilder();
        boolean allGood = true;
        
        // Check username
        if (!checkUserName(username)) {
            message.append("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.\n");
            allGood = false;
        } else {
            message.append("Username successfully captured.\n");
        }
        
        // Check password
        if (!checkPasswordComplexity(password)) {
            message.append("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.\n");
            allGood = false;
        } else {
            message.append("Password successfully captured.\n");
        }
        
        // Check phone
        if (!checkCellPhoneNumber(phone)) {
            message.append("Cell phone number incorrectly formatted or does not contain international code; please correct the number and try again.\n");
            allGood = false;
        } else {
            message.append("Cell phone number successfully added.\n");
        }
        
        // If everything is good, save the user
        if (allGood) {
            storedUsername = username;
            storedPassword = password;
            storedFirstName = firstName;
            storedLastName = lastName;
            storedPhoneNumber = phone;
            return "Username successfully captured.\nPassword successfully captured.\nCell phone number successfully added.\nUser registered successfully.";
        } else {
            return message.toString().trim();
        }
    }
    
    // This checks if the login details match what we saved
    public boolean loginUser(String username, String password) {
        if (username == null || password == null) return false;
        return username.equals(storedUsername) && password.equals(storedPassword);
    }
    
    // This returns the welcome message or error message
    public String returnLoginStatus(boolean status, String firstName, String lastName) {
        if (status) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
    
    // Getter methods  used by tests to check if data was saved
    public String getStoredUsername() { return storedUsername; }
    public String getStoredPassword() { return storedPassword; }
    public String getStoredFirstName() { return storedFirstName; }
    public String getStoredLastName() { return storedLastName; }
    public String getStoredPhoneNumber() { return storedPhoneNumber; }
}