import java.util.ArrayList;
import java.util.List;

/**
 * Абстрактный класс <<abstract>> Действие.
 * Реализует интерфейс Элемент.
 */
public abstract class Action implements Element {
    // Агрегация (ромб) к Элемент с пометкой {ordered}.
    // Использование List идеально подходит для сохранения упорядоченности действий.
    protected List<Element> elements = new ArrayList<>();

    public void addElement(Element element) {
        this.elements.add(element);
    }

    public void removeElement(Element element) {
        this.elements.remove(element);
    }

    public List<Element> getElements() {
        return elements;
    }

    // Метод Выполнить() из диаграммы
    public abstract void execute();
}