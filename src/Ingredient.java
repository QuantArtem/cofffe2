/**
 * Абстрактный класс <<abstract>> Ингредиент.
 * Реализует (implements) интерфейс Элемент.
 */
public abstract class Ingredient implements Element {
    private double netMass; // Поле "Масса нетто" из диаграммы

    public Ingredient(double netMass) {
        this.netMass = netMass;
    }

    public double getNetMass() {
        return netMass;
    }

    public void setNetMass(double netMass) {
        this.netMass = netMass;
    }
}