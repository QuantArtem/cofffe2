public abstract class Ingredient implements Element {
    private String name;
    private double netMass;

    public Ingredient(String name, double mass) {
        this.name = name;
        this.netMass = mass;
    }

    @Override
    public String getName() {
        return name;
    }
    public double getNetMass()
    {
        return netMass;
    }

}