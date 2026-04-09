import java.util.Scanner;

public class Main {
    private static DrinkRepository repository = new DrinkRepository();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\nСистема Управления Напитками");
            System.out.println("1.Создать новый напиток");
            System.out.println("2.Вывести информацию о напитке");
            System.out.println("3.Изменить название напитка");
            System.out.println("4.Удалить напиток");
            System.out.println("5.Показать все напитки");
            System.out.println("0.Выход");
            System.out.print("Выберите действие: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    createDynamicBeverage();
                    break;
                case "2":
                    retrieveBeverage();
                    break;
                case "3":
                    updateBeverage();
                    break;
                case "4":
                    deleteBeverage();
                    break;
                case "5":
                    showAllBeverages();
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Неверный ввод.");
            }
        }
    }

    private static void createDynamicBeverage() {
        System.out.print("\nВведите название для нового напитка: ");
        String name = scanner.nextLine();

        System.out.println("\nСоздание действия напитка");
        Action rootAction = chooseAction();

        if (rootAction == null) {
            System.out.println("Ошибка выбора действия. Отмена.");
            return;
        }
        populateAction(rootAction, 1);

        Drink beverage = new Drink(name, rootAction);
        repository.create(beverage);
        System.out.println("\nНапиток '" + name + "' успешно создан и сохранен!");
    }

    private static void populateAction(Action currentAction, int depth) {
        boolean filling = true;
        String indent = "  ".repeat(depth);

        while (filling) {
            System.out.println("\n" + indent + "Текущее действие: [" + currentAction.getClass().getSimpleName() + "]");
            System.out.println(indent + "1. Добавить ингредиент");
            System.out.println(indent + "2. Добавить вложенное действие");
            System.out.println(indent + "0. Завершить наполнение этого действия");
            System.out.print(indent + "Ваш выбор: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    Ingredient ing = chooseIngredient(indent);
                    if (ing != null) {
                        currentAction.addElement(ing);
                        System.out.println(indent + "Ингредиент добавлен.");
                    }
                    break;
                case "2":
                    System.out.println(indent + "Выбор вложенного действия");
                    Action subAction = chooseAction();
                    if (subAction != null) {
                        populateAction(subAction, depth + 1);
                        currentAction.addElement(subAction);
                        System.out.println(indent + "Вложенное действие добавлено.");
                    }
                    break;
                case "0":
                    filling = false;
                    break;
                default:
                    System.out.println(indent + "Неверный ввод.");
            }
        }
    }

    private static Action chooseAction() {
        System.out.println("Доступные действия:");
        System.out.println("1. Добавить\n2. Вскипятить\n3. Перемолоть");
        System.out.println("4. Перемешать\n5. Пролить\n6. Взбить");
        System.out.print("Выберите номер действия: ");

        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                return new Add();
            case "2":
                return new Boil();
            case "3":
                return new Grind();
            case "4":
                return new Mix();
            case "5":
                return new Pour();
            case "6":
                return new Whip();
            default:
                System.out.println("Неизвестное действие.");
                return null;
        }
    }

    private static Ingredient chooseIngredient(String indent) {
        System.out.println(indent + "Доступные ингредиенты:");
        System.out.println(indent + "1. Вода\n" + indent + "2. Кофейное зерно\n" + indent + "3. Сироп\n" + indent + "4. Молоко\n" + indent + "5. Лёд");
        System.out.print(indent + "Выберите номер: ");
        String choice = scanner.nextLine();

        System.out.print(indent + "Введите массу нетто (число): ");
        double mass;
        try {
            mass = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(indent + "Ошибка: масса должна быть числом.");
            return null;
        }

        switch (choice) {
            case "1":
                return new Water(mass);
            case "2":
                return new CoffeeBean(mass);
            case "3":
                return new Syrup(mass);
            case "4":
                return new Milk(mass);
            case "5":
                return new Ice(mass);
            default:
                System.out.println(indent + "Неизвестный ингредиент.");
                return null;
        }
    }

    private static void retrieveBeverage() {
        System.out.print("Введите название напитка для поиска: ");
        String name = scanner.nextLine();
        Drink beverage = repository.retrieve(name);

        if (beverage != null) {
            System.out.println("\nРецепт напитка: " + beverage.getName());
            printTree(beverage.getRootElement(), 0);
        } else {
            System.out.println("Напиток не найден.");
        }
    }

    private static void updateBeverage() {
        System.out.print("Введите старое название напитка: ");
        String oldName = scanner.nextLine();
        Drink beverage = repository.retrieve(oldName);

        if (beverage != null) {
            System.out.print("Введите новое название: ");
            String newName = scanner.nextLine();
            beverage.setName(newName);
            repository.update(oldName, beverage);
            System.out.println("Название обновлено.");
        } else {
            System.out.println("Напиток не найден.");
        }
    }

    private static void deleteBeverage() {
        System.out.print("Введите название напитка для удаления: ");
        String name = scanner.nextLine();
        if (repository.retrieve(name) != null) {
            repository.delete(name);
            System.out.println("Напиток удален.");
        } else {
            System.out.println("Напиток не найден.");
        }
    }

    private static void showAllBeverages() {
        System.out.println("\nСписок напитков в системе:");
        for (Drink b : repository.getAll()) {
            System.out.println("- " + b.getName());
        }
    }

    private static void printTree(Element element, int depth) {
        String indent = "  ".repeat(depth);

        if (element instanceof Action) {
            Action action = (Action) element;
            System.out.println(indent + "-- [Действие] " + action.getName());

            for (Element child : action.getElements()) {
                printTree(child, depth + 1);
            }
        } else if (element instanceof Ingredient) {
            Ingredient ingredient = (Ingredient) element;
            System.out.println(indent + "   -- [Ингредиент] " + ingredient.getName() +
                    " (Масса: " + ingredient.getNetMass() + ")");
        }
    }
}