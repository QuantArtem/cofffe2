import java.util.HashMap;
import java.util.Map;

public class DrinkRepository {
    private Map<String, Drink> database = new HashMap<>();


    public void create(Drink beverage) {
        database.put(beverage.getName(), beverage);
    }

    public Drink retrieve(String name) {
        return database.get(name);
    }

    public void update(String oldName, Drink updatedBeverage) {
        if (database.containsKey(oldName)) {
            database.remove(oldName);
            database.put(updatedBeverage.getName(), updatedBeverage);
        }
    }

    public void delete(String name) {
        database.remove(name);
    }

    public Iterable<Drink> getAll() {
        return database.values();
    }
}