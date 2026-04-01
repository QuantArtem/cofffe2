import java.util.HashMap;
import java.util.Map;

/**
 * Класс, реализующий CRUD операции для Напитка.
 */
public class DrinkRepository {
    private Map<String, Drink> database = new HashMap<>();

    // Create
    public void create(Drink beverage) {
        database.put(beverage.getName(), beverage);
    }

    // Retrieve
    public Drink retrieve(String name) {
        return database.get(name);
    }

    // Update
    public void update(String oldName, Drink updatedBeverage) {
        if (database.containsKey(oldName)) {
            database.remove(oldName);
            database.put(updatedBeverage.getName(), updatedBeverage);
        }
    }

    // Delete
    public void delete(String name) {
        database.remove(name);
    }

    // Достать все напитки (вспомогательный метод для консоли)
    public Iterable<Drink> getAll() {
        return database.values();
    }
}