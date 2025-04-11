package org.example;

import jakarta.xml.bind.annotation.*;

import java.util.Objects;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Auto {
    private String model;
    private double mileage;
    private double fuel;
    private double fuelCapacity;
    private double fuelConsumption;
    private Manufacturer manufacturer;

    public Auto() {
    }

    public Auto(String model, double fuelCapacity, double fuelConsumption, Manufacturer manufacturer) {
        this.model = model;
        this.fuelCapacity = fuelCapacity;
        this.fuelConsumption = fuelConsumption;
        this.fuel = fuelCapacity;
        this.manufacturer = manufacturer;
        this.mileage = 0;
    }

    public void drive(double distance) {
        double neededFuel = distance * fuelConsumption / 100;
        if (neededFuel > fuel) {
            throw new IllegalArgumentException("Недостатньо палива.");
        }
        fuel -= neededFuel;
        mileage += distance;
    }

    public void refuel(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Кількість має бути додатною.");
        }

        if (amount > fuelCapacity - fuel) {
            fuel = fuelCapacity;
        } else {
            fuel += amount;
        }
    }
    public void refuelToFull() {
        fuel = fuelCapacity;
    }

    public double getFuel() {
        return fuel;
    }
    public double getMileage() {
        return mileage;
    }

    public String getModel() {
        return model;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Auto other)) return false;
        return Objects.equals(model, other.model) &&
                Objects.equals(manufacturer, other.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, manufacturer, mileage, fuel, fuelCapacity);
    }

    @Override
    public String toString() {
        return model + " - " + manufacturer + ", пробіг: " + mileage + " км, паливо: " + fuel + "/" + fuelCapacity;
    }
}