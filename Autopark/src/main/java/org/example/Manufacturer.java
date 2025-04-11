package org.example;

import jakarta.xml.bind.annotation.*;

import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Manufacturer {
    private String name;
    private String country;

    public Manufacturer() {}

    public Manufacturer(String name, String country) {
        this.name = name;
        this.country = country;
    }

    @Override
    public String toString() {
        return name + " (" + country + ")";
    }
}
