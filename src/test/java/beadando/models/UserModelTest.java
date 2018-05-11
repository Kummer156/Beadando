package beadando.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserModelTest {

    private UserModel user = new UserModel();

    @Before
    public void setUp() throws Exception {
        user.setId(0);
        user.setName("User");
        user.setPassword("Pw");
        user.setEmail("TestEmail");
        user.setAddress("TestAddress");
        user.setPhonenumber("phone");
    }

    @Test
    public void getId() {
        assertEquals(0,user.getId());
    }

    @Test
    public void getName() {
        assertEquals("User",user.getName());
    }

    @Test
    public void getPassword() {
        assertEquals("Pw",user.getPassword());
    }

    @Test
    public void getEmail() {
        assertEquals("TestEmail",user.getEmail());
    }

    @Test
    public void getAddress() {
        assertEquals("TestAddress",user.getAddress());
    }

    @Test
    public void getPhonenumber() {
        assertEquals("phone",user.getPhonenumber());
    }
}