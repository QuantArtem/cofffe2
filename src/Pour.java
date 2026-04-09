public class Pour extends Action {
    public Pour() {
        super("Пролив");
    }
    @Override
    public void execute() { System.out.println("Выполняется: Пролить"); }
}