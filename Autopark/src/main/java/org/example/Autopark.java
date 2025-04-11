package org.example;

import java.util.ArrayList;
import java.util.List;

public class Autopark {
    private List<Auto> cars = new ArrayList<>();

    public void addAuto(Auto auto) {
        cars.add(auto);
    }

    public void removeAuto(Auto auto) {
        cars.remove(auto);
    }

    public List<Auto> getCars() {
        return cars;
    }
    public List<String> getModel(List<Auto> cars) {
        return cars.stream()
                .map(Auto::getModel)
                .toList();
    }

}
