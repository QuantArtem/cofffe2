/**
 * Класс Напиток.
 */
public class Drink {
    private String name;

    // Направленная ассоциация к интерфейсу Элемент.
    // "Напиток определяется исходя из последовательности действий"
    private Element rootElement;

    public Drink(String name, Element rootElement) {
        this.name = name;
        this.rootElement = rootElement;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Element getRootElement() {
        return rootElement;
    }

    public void setRootElement(Element rootElement) {
        this.rootElement = rootElement;
    }
}