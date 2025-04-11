package org.example;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceAuto {
    private final Autopark autopark;

    public ServiceAuto(Autopark autopark) {
        this.autopark = autopark;
    }
    public void driveAuto(Auto auto, double distance) {
        auto.drive(distance);
    }

    public void refuelAuto(Auto auto, double amount) {
        auto.refuel(amount);
    }

    public void refuelToFull(Auto auto) {
        auto.refuelToFull();
    }

    public List<Auto> getSortedByMileage() {
        return autopark.getCars().stream()
                .sorted(Comparator.comparingDouble(Auto::getMileage).reversed())
                .collect(Collectors.toList());
    }

    public List<Auto> getAllAutos() {
        return autopark.getCars();
    }

    public void addAuto(Auto auto) {
        autopark.addAuto(auto);
    }

    public void removeAuto(Auto auto) {
        autopark.removeAuto(auto);
    }
}
