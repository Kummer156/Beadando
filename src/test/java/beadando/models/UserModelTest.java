package beadando.models;

/*-
 * #%L
 * Beadando
 * %%
 * Copyright (C) 2016 - 2018 Faculty of Informatics, University of Debrecen
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */

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
