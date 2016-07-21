package com.tomkasp.fitnow.profile;

import org.joda.time.DateTime;
import org.junit.Test;

/**
 * @author Tomasz Kasprzycki (A042191)
 */
public class BodySizeTest {

    @Test
    public void testDateEqualsMethod() {
        //given
        DateTime firstDate = new DateTime()
            .withHourOfDay(0)
            .withMinuteOfHour(0)
            .withSecondOfMinute(0).withMillisOfSecond(0);

        DateTime secondDate = new DateTime()
            .withHourOfDay(0)
            .withMinuteOfHour(0)
            .withSecondOfMinute(0)
            .withMillisOfSecond(0);

        //when

        //then
    }

    @Test
    public void testDateNotEqualsMethod() {
        //given
        DateTime firstDate = new DateTime()
            .withDayOfMonth(1)
            .withHourOfDay(0)
            .withMinuteOfHour(0)
            .withSecondOfMinute(0).withMillisOfSecond(0);

        DateTime secondDate = new DateTime()
            .withDayOfMonth(2)
            .withHourOfDay(0)
            .withMinuteOfHour(0)
            .withSecondOfMinute(0)
            .withMillisOfSecond(0);

    }

}
