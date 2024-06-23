package ru.gustaff.teacher_register.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.gustaff.teacher_register.TestConfiguration;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { TestConfiguration.class })
@WebAppConfiguration
public class AbstractServiceTest {
}
