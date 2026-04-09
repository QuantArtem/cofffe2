public class Boil extends Action {
    public Boil() {
        super("Кипячение");
    }
    @Override
    public void execute() { System.out.println("Выполняется: Вскипятить"); }
}