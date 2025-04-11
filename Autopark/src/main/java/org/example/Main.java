package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Autopark autopark = new Autopark();
        ServiceAuto service = new ServiceAuto(autopark);

        while (true) {
            System.out.println("\n--- Меню ---");
            System.out.println("1. Додати авто");
            System.out.println("2. Список авто");
            System.out.println("3. Їхати");
            System.out.println("4. Заправити");
            System.out.println("5. Експорт JSON");
            System.out.println("6. Імпорт JSON");
            System.out.println("0. Вихід");

            switch (scanner.nextLine()) {
                case "1" -> {
                    System.out.print("Марка: ");
                    String brand = scanner.nextLine();
                    System.out.print("Країна: ");
                    String country = scanner.nextLine();
                    System.out.print("Модель: ");
                    String model = scanner.nextLine();
                    System.out.print("Бак (л): ");
                    double cap = Double.parseDouble(scanner.nextLine());
                    System.out.print("Витрата (л/100км): ");
                    double cons = Double.parseDouble(scanner.nextLine());

                    Manufacturer m = new Manufacturer(brand, country);
                    Auto a = new Auto(model, cap, cons, m );
                    service.addAuto(a);
                    System.out.println("Додано!");
                }
                case "2" -> service.getAllAutos().forEach(System.out::println);
                case "3" -> {
                    Auto a = selectAuto(service, scanner);
                    System.out.print("Кілометри: ");
                    service.driveAuto(a, Double.parseDouble(scanner.nextLine()));
                    System.out.println("Поїхали!");
                }
                case "4" -> {
                    Auto a = selectAuto(service, scanner);
                    System.out.print("Скільки літрів?: ");
                    service.refuelAuto(a, Double.parseDouble(scanner.nextLine()));
                }
                case "5" -> {
                    try {
                        Exporter.exportToXml(autopark, "autos.xml");
                        System.out.println("Експортовано в autos.xml");
                    } catch (Exception e) {
                        System.out.println("Помилка експорту: " + e.getMessage());
                    }
                }
                case "6" -> {
                    try {
                        Autopark imported = Importer.importFromXml("autos.xml");
                        imported.getCars().forEach(service::addAuto);
                        System.out.println("Імпортовано з autos.xml");
                    } catch (Exception e) {
                        System.out.println("Помилка імпорту: " + e.getMessage());
                    }
                }
                case "0" -> {
                    System.out.println("До зустрічі!");
                    return;
                }
                default -> System.out.println("Невідомий варіант");
            }
        }
    }

    private static Auto selectAuto(ServiceAuto service, Scanner scanner) {
        List<Auto> autos = service.getAllAutos();
        for (int i = 0; i < autos.size(); i++) {
            System.out.println((i + 1) + ". " + autos.get(i));
        }
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        return autos.get(index);
    }
}
