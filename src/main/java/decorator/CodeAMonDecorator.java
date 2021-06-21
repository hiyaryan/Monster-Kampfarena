package decorator;

import factory.Monster;

public abstract class CodeAMonDecorator implements CodeAMon {
    private CodeAMon codeAMon;

    public CodeAMonDecorator(CodeAMon codeAMon) {
        this.codeAMon = codeAMon;
    }

    @Override
    public Monster getMonster() {
        return (Monster) codeAMon;
    }

    @Override
    public void operation() {
        codeAMon.operation();
    }
}