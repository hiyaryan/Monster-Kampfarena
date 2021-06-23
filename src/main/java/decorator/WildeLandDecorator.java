package decorator;

public abstract class WildeLandDecorator implements Environment {
    private Environment environment;

    public WildeLandDecorator(Environment environment) {
        this.environment = environment;
    }

    @Override
    public Environment getEnvironment() {
        return environment;
    }

    public abstract Weather determineTheDayWeatherForecast();
    public abstract Weather determineTheNightWeatherForecast();
}
