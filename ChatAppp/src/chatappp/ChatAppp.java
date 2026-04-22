package chatappp;

import java.util.Scanner;

public class ChatAppp {
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        UserAuth auth = new UserAuth();
        
        // Variables to store user info
        String username;
        String password;
        String phone;
        String firstName;
        String lastName;
        
        // Welcome message
        System.out.println("========================================");
        System.out.println("         WELCOME TO CHATAPPP");
        System.out.println("========================================\n");
        
        // Ask for name
        System.out.print("Enter first name: ");
        firstName = input.nextLine();
        
        System.out.print("Enter last name: ");
        lastName = input.nextLine();
        
        System.out.println();
        
        // Keep asking until username is correct
        do {
            System.out.print("Enter username (must have _ and be 5 letters or less): ");
            username = input.nextLine();
            
            if (!auth.checkUserName(username)) {
                System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
            }
        } while (!auth.checkUserName(username));
        
        // Username is good
        System.out.println("Username successfully captured.\n");
        
        // Keep asking until password is strong enough
        do {
            System.out.print("Enter password (8+ letters, 1 capital, 1 number, 1 special like @ or !): ");
            password = input.nextLine();
            
            if (!auth.checkPasswordComplexity(password)) {
                System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
            }
        } while (!auth.checkPasswordComplexity(password));
        
        // Password is good
        System.out.println("Password successfully captured.\n");
        
        // Keep asking until phone number is correct
        do {
            System.out.print("Enter cell phone number (start with +27 then 9 digits): ");
            phone = input.nextLine();
            
            if (!auth.checkCellPhoneNumber(phone)) {
                System.out.println("Cell phone number incorrectly formatted or does not contain international code; please correct the number and try again.");
            }
        } while (!auth.checkCellPhoneNumber(phone));
        
        // Phone is good
        System.out.println("Cell phone number successfully added.\n");
        
        // Save everything
        String registrationResult = auth.registerUser(username, password, firstName, lastName, phone);
        System.out.println(registrationResult);
        
        // Now let them login
        System.out.println("\n========================================");
        System.out.println("              LOGIN SECTION");
        System.out.println("========================================\n");
        
        boolean loggedIn = false;
        
        // Keep asking until they get it right
        while (!loggedIn) {
            System.out.print("Enter username: ");
            String loginUser = input.nextLine();
            
            System.out.print("Enter password: ");
            String loginPass = input.nextLine();
            
            loggedIn = auth.loginUser(loginUser, loginPass);
            
            if (loggedIn) {
                System.out.println("\n" + auth.returnLoginStatus(true, firstName, lastName));
            } else {
                System.out.println("Username or password incorrect, please try again.\n");
            }
        }
        
        // All done
        System.out.println("\n========================================");
        System.out.println("       You are now logged into ChatAppp");
        System.out.println("========================================");
        
        input.close();
    }
}