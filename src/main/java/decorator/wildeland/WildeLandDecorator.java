package decorator.wildeland;

public abstract class WildeLandDecorator implements Environment {
    public abstract String determineTheDayWeatherForecast();

    public abstract String determineTheNightWeatherForecast();
}