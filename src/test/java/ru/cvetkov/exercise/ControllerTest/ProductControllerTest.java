package ru.cvetkov.exercise.ControllerTest;

import javafx.application.Application;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.cvetkov.exercise.service.PriceService;
import ru.cvetkov.exercise.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest( //Аннотация работает, создавая ApplicationContext , который будет использоваться в наших тестах.
        webEnvironment = SpringBootTest.WebEnvironment.MOCK, //здесь мы используем WebEnvironment.MOCK , чтобы контейнер работал в среде имитации сервлета
        classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(//помогает настроить расположение файлов свойств, специфичных для наших тестов.
        locations = "classpath:application-integrationtest.properties")//содержит сведения о настройке постоянного хранилища
public class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ProductService productService;

    @Autowired
    private PriceService priceService;

}
