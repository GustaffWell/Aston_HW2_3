package ru.gustaff.teacher_register.repository;

import org.junit.After;
import org.junit.Before;

public abstract class BaseTest {

    @Before
    public void beforeTest() throws Exception {
        DbConnection.isTestProfile = true;
    }

    @After
    public void afterTest() throws Exception {
        DbConnection.isTestProfile = false;
    }
}
