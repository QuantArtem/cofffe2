public class Drink {
    private String name;

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

}