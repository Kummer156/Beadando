package beadando.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserModelTest {

    UserModel user = new UserModel();

    @BeforeEach
    void setUp() {
        user.setId(0);
    }

    @Test
    void getId() {
        assertEquals(0,user.getId());
    }

    @Test
    void getName() {
    }

    @Test
    void getPassword() {
    }

    @Test
    void getEmail() {
    }

    @Test
    void getAddress() {
    }

    @Test
    void getPhonenumber() {
    }
}