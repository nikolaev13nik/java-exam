package com.ngsoft.part1;

import com.ngsoft.part1.services.TodoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:spring-config.xml")
public class ToDoTest {

    @Autowired
    TodoService todoService;


    @Test
    public void testCount() {
        long size = todoService.countAll();

        assertEquals(200, size);
    }

    @Test
    public void testDone() {
        long done = todoService.countDone();
        assertEquals(90, done);
    }

    @Test
    public void testByUser() {
        long items = todoService.countItemsByUser(1);
        assertEquals(20, items);
    }


    @Test
    public void testCountFiltered() {  	
        long count = todoService.countFiltered(2, true, "em");
        assertEquals(3, count);
    }


}
