package decorator;

public class ConcreteComponent implements ComponentBase {
    /**
     * This method should be implemented in a
     * ConcreteComponent and Decorator Base class
     */
    @Override
    public void operation() {
        System.out.println("Component operation");
    }
}
