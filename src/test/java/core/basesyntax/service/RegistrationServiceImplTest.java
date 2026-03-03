package core.basesyntax.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import core.basesyntax.db.Storage;
import core.basesyntax.exceptions.RegistrationFailException;
import core.basesyntax.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RegistrationServiceImplTest {
    private User actual;

    @BeforeEach
    void setUp() {
        actual = new User();
        actual.setAge(19);
        actual.setLogin("JamesSmith");
        actual.setPassword("password123");
    }

    @Test
    void register_loginLength_Ok() {
        actual.setLogin("JamesS");
        User result = new RegistrationServiceImpl().register(actual);
        assertEquals("JamesS", result.getLogin());
        assertTrue(Storage.people.contains(actual));
    }

    @Test
    void register_userNull_notOk() {
        assertThrows(RegistrationFailException.class,
                () -> new RegistrationServiceImpl().register(null));
    }

    @Test
    void register_loginIsEmpty_notOk() {
        actual.setLogin("");
        assertThrows(RegistrationFailException.class,
                () -> new RegistrationServiceImpl().register(actual));
    }

    @Test
    void register_loginLength_notOk() {
        actual.setLogin("login");
        assertThrows(RegistrationFailException.class,
                () -> new RegistrationServiceImpl().register(actual));
    }

    @Test
    void register_loginNull_notOk() {
        actual.setLogin(null);
        assertThrows(RegistrationFailException.class,
                () -> new RegistrationServiceImpl().register(actual));
    }

    @Test
    void register_passwordIsEmpty_notOk() {
        actual.setPassword("");
        assertThrows(RegistrationFailException.class,
                () -> new RegistrationServiceImpl().register(actual));
    }

    @Test
    void register_ageLessZero_notOk() {
        actual.setAge(-1);
        assertThrows(RegistrationFailException.class,
                () -> new RegistrationServiceImpl().register(actual));
    }

    @Test
    void register_passwordNull_notOk() {
        actual.setPassword(null);
        assertThrows(RegistrationFailException.class,
                () -> new RegistrationServiceImpl().register(actual));
    }

    @Test
    void register_passwordLength_notOk() {
        actual.setPassword("passw");
        assertThrows(RegistrationFailException.class,
                () -> new RegistrationServiceImpl().register(actual));
    }

    @Test
    void register_passwordLength_Ok() {
        actual.setPassword("passw1");
        User result = new RegistrationServiceImpl().register(actual);
        assertEquals("passw1", result.getPassword());
        assertTrue(Storage.people.contains(actual));

    }

    @Test
    void register_ageZero_notOk() {
        actual.setAge(0);
        assertThrows(RegistrationFailException.class,
                () -> new RegistrationServiceImpl().register(actual));
    }

    @Test
    void register_ageNull_notOk() {
        actual.setAge(null);
        assertThrows(RegistrationFailException.class,
                () -> new RegistrationServiceImpl().register(actual));
    }

    @Test
    void register_ageLess_notOk() {
        actual.setAge(17);
        assertThrows(RegistrationFailException.class,
                () -> new RegistrationServiceImpl().register(actual));
    }

    @Test
    void register_age_Ok() {
        actual.setAge(18);
        User result = new RegistrationServiceImpl().register(actual);
        assertTrue(Storage.people.contains(actual));
        assertEquals(18, result.getAge());
    }

    @AfterEach
    void tearDown() {
        Storage.people.clear();
    }

    @Test
    void register_userExisting_notOk() {
        Storage.people.add(actual);
        assertThrows(RegistrationFailException.class,
                () -> new RegistrationServiceImpl().register(actual));
    }
}

