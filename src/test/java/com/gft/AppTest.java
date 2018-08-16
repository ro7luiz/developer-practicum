package com.gft;

import com.gft.enums.TimeOfDay;
import org.junit.Test;

import static org.junit.Assert.*;

public class AppTest {

    @Test
    public void testShouldHaveTimeOfDay() {
        App app = new App();
        assertTrue("Wrong time of day", app.containsTimeOfDay(new String[] {"morning", "1", "3"}));
        assertTrue("Wrong time of day", app.containsTimeOfDay(new String[] {"night", "1", "3", "2"}));
        assertFalse("Wrong time of day", app.containsTimeOfDay(new String[] {"niiiight", "1", "2', '2"}));
        assertFalse("Wrong time of day", app.containsTimeOfDay(new String[] {"moooooorning, 1, 3, 3"}));
    }


    @Test
    public void testShouldHaveTimeOfDayAndAtLeastOneSelectedDish() {
        App app = new App();
        assertEquals(2, app.getOnlyDishes(new String[]{"morning", "1", "3"}).length);
        assertEquals(1, app.getOnlyDishes(new String[]{"morning", "1"}).length);
        assertNotEquals(0, app.getOnlyDishes(new String[]{"morning", "1"}).length);
    }

    @Test
    public void testShouldReturnTimeOfDay() {
        App app = new App();
        assertEquals(TimeOfDay.MORNING.asLowerCase(), app.getTimeOfDay(new String[]{"morning", "1", "3"}));
        assertEquals(TimeOfDay.NIGHT.asLowerCase(), app.getTimeOfDay(new String[]{"night", "1", "3"}));
        assertNotEquals(TimeOfDay.MORNING.asLowerCase(), app.getTimeOfDay(new String[]{"night", "1", "3"}));
    }

    @Test
    public void testShouldGetOccurrencesOfCoffee() {
        App app = new App();
        assertEquals(3, app.getCoffeeOccurrences(new String[]{"morning", "1", "2", "3", "3", "3"}));
        assertEquals(1, app.getCoffeeOccurrences(new String[]{"morning", "1", "2", "3", }));
    }

    @Test
    public void testShouldGetOccurrencesOfPotato() {
        App app = new App();
        assertEquals(3, app.getPotatoOccurrences(new String[]{"night", "1", "2", "2", "2", "3", "3", "3"}));
        assertEquals(1, app.getPotatoOccurrences(new String[]{"night", "1", "2", "3", }));
    }

    @Test
    public void testPrintDishesByNameAtMorning() {
        App app = new App();

        assertArrayEquals(new String[]{ "eggs", "toast", "coffee"}, app.printDishes(new String[]{"morning", "1", "2", "3"}));
        assertArrayEquals(new String[]{ "eggs", "toast", "coffee"}, app.printDishes(new String[]{"morning", "2", "1", "3"}));
        assertArrayEquals(new String[]{ "eggs", "toast", "coffee", "error"}, app.printDishes(new String[]{"morning", "1", "2", "3", "4"}));
        assertArrayEquals(new String[]{ "eggs", "toast", "coffee(3x)"}, app.printDishes(new String[]{"morning", "1", "2", "3", "3", "3"}));

        assertArrayEquals(new String[]{ "eggs", "toast", "coffee(2x)"}, app.printDishes(new String[]{"morning", "1", "1", "2", "3", "3"}));
        assertArrayEquals(new String[]{ "eggs", "toast", "coffee(2x)"}, app.printDishes(new String[]{"morning", "1", "3", "3", "2"}));

    }

    @Test
    public void testPrintDishesByNameAtNight() {
        App app = new App();

        assertArrayEquals(new String[]{ "steak", "potato", "wine"}, app.printDishes(new String[]{"night", "1", "2", "3"}));
        assertArrayEquals(new String[]{ "steak", "potato", "wine"}, app.printDishes(new String[]{"night", "2", "1", "3"}));
        assertArrayEquals(new String[]{ "steak", "potato", "wine", "cake"}, app.printDishes(new String[]{"night", "1", "2", "3", "4"}));
        assertArrayEquals(new String[]{ "steak", "potato(2x)", "cake"}, app.printDishes(new String[]{"night", "1", "2", "2", "4"}));

        assertArrayEquals(new String[]{ "steak", "potato", "wine"}, app.printDishes(new String[]{"night", "1", "1", "2", "3", "3"}));
        assertArrayEquals(new String[]{ "steak", "potato(2x)", "wine"}, app.printDishes(new String[]{"night", "1", "1", "3", "3", "2", "2"}));

    }
}
