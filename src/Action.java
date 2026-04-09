import java.util.ArrayList;
import java.util.List;

public abstract class Action implements Element {
    private String name;
    @Override
    public String getName() {
        return name;
    }
    protected List<Element> elements = new ArrayList<>();
    public Action(String name) {
        this.name = name;
    }
    public void addElement(Element element) {
        this.elements.add(element);
    }

    public List<Element> getElements() {
        return elements;
    }

    public abstract void execute();
}