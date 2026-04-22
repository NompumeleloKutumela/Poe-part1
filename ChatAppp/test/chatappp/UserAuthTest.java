package chatappp;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserAuthTest {
    
    private UserAuth auth;
    
    // This runs before each test
    @Before
    public void setUp() {
        auth = new UserAuth();
    }
    
    // Good username
    @Test
    public void testUsernameCorrectlyFormatted() {
        boolean result = auth.checkUserName("kyl_1");
        assertTrue(result);
        
        String registerResult = auth.registerUser("kyl_1", "Ch&sec@ke99!", "Kyle", "Smith", "+27838968976");
        assertTrue(registerResult.contains("Username successfully captured"));
    }
    
    // Bad username
    @Test
    public void testUsernameIncorrectlyFormatted() {
        boolean result = auth.checkUserName("kyle!!!!!!");
        assertFalse(result);
        
        String registerResult = auth.registerUser("kyle!!!!!!", "Ch&sec@ke99!", "Kyle", "Smith", "+27838968976");
        assertTrue(registerResult.contains("Username is not correctly formatted"));
    }
    
    // Good password
    @Test
    public void testPasswordMeetsComplexity() {
        boolean result = auth.checkPasswordComplexity("Ch&sec@ke99!");
        assertTrue(result);
        
        String registerResult = auth.registerUser("kyl_1", "Ch&sec@ke99!", "Kyle", "Smith", "+27838968976");
        assertTrue(registerResult.contains("Password successfully captured"));
    }
    
    //  Bad password
    @Test
    public void testPasswordDoesNotMeetComplexity() {
        boolean result = auth.checkPasswordComplexity("password");
        assertFalse(result);
        
        String registerResult = auth.registerUser("kyl_1", "password", "Kyle", "Smith", "+27838968976");
        assertTrue(registerResult.contains("Password is not correctly formatted"));
    }
    
    // Good phone
    @Test
    public void testCellPhoneCorrectlyFormatted() {
        boolean result = auth.checkCellPhoneNumber("+27838968976");
        assertTrue(result);
        
        String registerResult = auth.registerUser("kyl_1", "Ch&sec@ke99!", "Kyle", "Smith", "+27838968976");
        assertTrue(registerResult.contains("Cell phone number successfully added"));
    }
    
    // Bad phone
    @Test
    public void testCellPhoneIncorrectlyFormatted() {
        assertFalse(auth.checkCellPhoneNumber("08966553"));
        assertFalse(auth.checkCellPhoneNumber("+2783896"));
        assertFalse(auth.checkCellPhoneNumber("+278389689761"));
        assertFalse(auth.checkCellPhoneNumber("+267838968976"));
        
        String registerResult = auth.registerUser("kyl_1", "Ch&sec@ke99!", "Kyle", "Smith", "08966553");
        assertTrue(registerResult.contains("Cell phone number incorrectly formatted"));
    }
    
    // Login works
    @Test
    public void testLoginSuccessful() {
        auth.registerUser("kyl_1", "Ch&sec@ke99!", "Kyle", "Smith", "+27838968976");
        
        boolean result = auth.loginUser("kyl_1", "Ch&sec@ke99!");
        assertTrue(result);
        
        String message = auth.returnLoginStatus(true, "Kyle", "Smith");
        assertEquals("Welcome Kyle, Smith it is great to see you again.", message);
    }
    
    //Login fails
    @Test
    public void testLoginFailed() {
        auth.registerUser("kyl_1", "Ch&sec@ke99!", "Kyle", "Smith", "+27838968976");
        
        assertFalse(auth.loginUser("kyl_1", "wrongpass"));
        assertFalse(auth.loginUser("wronguser", "Ch&sec@ke99!"));
        
        String message = auth.returnLoginStatus(false, "Kyle", "Smith");
        assertEquals("Username or password incorrect, please try again.", message);
    }
    
    //  Check if data was saved correctly
    @Test
    public void testStoredUserData() {
        auth.registerUser("kyl_1", "Ch&sec@ke99!", "Kyle", "Smith", "+27838968976");
        
        assertEquals("kyl_1", auth.getStoredUsername());
        assertEquals("Ch&sec@ke99!", auth.getStoredPassword());
        assertEquals("Kyle", auth.getStoredFirstName());
        assertEquals("Smith", auth.getStoredLastName());
        assertEquals("+27838968976", auth.getStoredPhoneNumber());
    }
}