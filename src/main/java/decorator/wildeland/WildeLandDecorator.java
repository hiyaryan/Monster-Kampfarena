package decorator.wildeland;

public abstract class WildeLandDecorator implements Environment {
    public abstract String determineTheDayWeatherForecast();

    public abstract String determineTheNightWeatherForecast();
}


// Day, 0d:1t:0c