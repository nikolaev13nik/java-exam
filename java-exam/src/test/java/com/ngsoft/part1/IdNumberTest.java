package com.ngsoft.part1;

import com.ngsoft.part1.IdNumber.IdNumberValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:spring-config.xml")
public class IdNumberTest {

	@Autowired
	IdNumberValidator idNumberValidator;

	@Test
	public void test() {

		assertTrue(idNumberValidator.isValid("027996917"));
		assertTrue(idNumberValidator.isValid("27996917"));
		assertFalse(idNumberValidator.isValid("B27996917"));
		assertFalse(idNumberValidator.isValid("219887024"));

	}

	@Test(expected = IllegalArgumentException.class)
	public void whenNullIsPassed() {
		idNumberValidator.isValid(null);
	}

}
