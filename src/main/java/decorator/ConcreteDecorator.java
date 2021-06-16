package decorator;

public class ConcreteDecorator extends DecoratorBase {
    public ConcreteDecorator(ComponentBase component) {
        super(component);
    }

    @Override
    public void operation() {
        super.operation();
        System.out.println("Modified");
    }
}
