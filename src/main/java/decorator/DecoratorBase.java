package decorator;

public abstract class DecoratorBase implements ComponentBase {
    private ComponentBase component;

    public DecoratorBase(ComponentBase component) {
        this.component = component;
    }

    @Override
    public void operation() {
        component.operation();
    }
}
