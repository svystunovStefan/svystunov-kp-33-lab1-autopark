package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceAutoTest {
    private ServiceAuto service;
    private Auto auto;

    @BeforeEach
    public void setUp() {
        Manufacturer m = new Manufacturer("Toyota", "Japan");
        auto = new Auto("Corolla", 50, 5, m);
        Autopark park = new Autopark();
        park.addAuto(auto);
        service = new ServiceAuto(park);
    }

    @Test
    public void testDriveAuto() {
        service.driveAuto(auto, 100);
        assertEquals(45.0, auto.getFuel());
        assertEquals(100.0, auto.getMileage());
    }

    @Test
    public void testRefuelAuto() {
        auto.drive(100); // use 5L
        service.refuelAuto(auto, 3);
        assertEquals(48.0, auto.getFuel());
    }

    @Test
    public void testRefuelToFull() {
        auto.drive(100);
        service.refuelToFull(auto);
        assertEquals(50.0, auto.getFuel());
    }

    @Test
    public void testSortByMileage() {
        Manufacturer m = new Manufacturer("BMW", "Germany");
        Auto a2 = new Auto("X5", 60, 10, m);
        a2.drive(50); // 5L, 50km

        service.addAuto(a2);
        auto.drive(100); // 5L, 100km

        List<Auto> sorted = service.getSortedByMileage();
        assertEquals("Corolla", sorted.get(0).getModel());
    }

    @Test
    public void testAddAndRemoveAuto() {
        Auto a2 = new Auto("Test", 30, 3, new Manufacturer("Test", "Test"));
        service.addAuto(a2);
        assertTrue(service.getAllAutos().contains(a2));
        service.removeAuto(a2);
        assertFalse(service.getAllAutos().contains(a2));
    }

    @Test
    public void testDriveWithoutFuel() {
        auto.drive(900); // витратить 45л, залишиться 5л
        assertThrows(IllegalArgumentException.class, () -> auto.drive(200));
    }

    @Test
    public void testRefuelNegative() {
        assertThrows(IllegalArgumentException.class, () -> auto.refuel(-5));
    }

    @Test
    public void testToString() {
        String output = auto.toString();
        assertTrue(output.contains("Corolla"));
        assertTrue(output.contains("Toyota"));
        assertTrue(output.contains("пробіг: 0.0 км"));
        assertTrue(output.contains("паливо: 50.0/50.0"));
    }


    @Test
    public void testRefuelBeyondCapacity() {
        auto.refuel(10); // спроба перелити, бак вже повний
        assertEquals(50.0, auto.getFuel());
    }

    @Test
    public void testInitialAutoState() {
        assertEquals("Corolla", auto.getModel());
        assertEquals(0.0, auto.getMileage());
        assertEquals(50.0, auto.getFuel());
    }

}

