package com.pokemongo.utilities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TimeConverterTest {

    private TimeConverter testTimeConverter;

    @Before
    public void setUp() throws Exception {
        testTimeConverter = new TimeConverter();
    }

    @After
    public void tearDown() throws Exception {
        testTimeConverter = null;
    }

    @Test
    public void testConvertToDatabaseColumn() throws Exception {
        // Preparing variables for testing and converting
        Timestamp expectedResult = Timestamp.valueOf("2000-01-01 12:00:00");
        LocalDateTime localDateTimeToConvert = LocalDateTime.of(2000, 1, 1, 12, 0);
        Timestamp actualTimestamp = testTimeConverter.convertToDatabaseColumn(localDateTimeToConvert);

        // The actual tests
        assertNotNull(actualTimestamp);
        assertEquals(expectedResult, actualTimestamp);
    }

    @Test
    public void testConvertToEntityAttribute() throws Exception {
        // Preparing variables for testing and converting
        LocalDateTime expectedResult = LocalDateTime.of(2000, 1, 1, 12, 0);
        Timestamp timestampToConvert = Timestamp.valueOf("2000-01-01 12:00:00");
        LocalDateTime actualLocalDateTime = testTimeConverter.convertToEntityAttribute(timestampToConvert);

        // The actual tests
        assertNotNull(actualLocalDateTime);
        assertEquals(expectedResult, actualLocalDateTime);
    }

}