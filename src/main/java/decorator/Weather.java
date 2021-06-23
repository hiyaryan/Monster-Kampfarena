package decorator;

import java.util.Random;

public class Weather extends WildeLandDecorator {
    private Type type;

    private enum Type {
        NEUTRAL,
        SUNNY,
        WINDY,
        RAINY,
        SNOWY,
        STRANGE
    }

    public Weather(Environment environment) {
        super(environment);
        determineTheWeatherForecast();
    }

    @Override
    public Weather determineTheWeatherForecast() {
        int forecast = new Random().nextInt(5) + 1;

        switch (forecast) {
            case 1:
                type = Type.NEUTRAL;
                break;
            case 2:
                type = Type.SUNNY;
                break;
            case 3:
                type = Type.WINDY;
                break;
            case 4:
                type = Type.RAINY;
                break;
            case 5:
                type = Type.SNOWY;
                break;
            default:
                type = Type.STRANGE;
                break;
        }

        return this;
    }

    @Override
    public String howIsTheWeather(String time) {
        switch (type) {
            case NEUTRAL:
                return "Today's forecast: \n   " + time + " neutral";
            case SUNNY:
                return "Today's forecast: \n   " + time + " sunny";
            case WINDY:
                return "Today's forecast: \n   " + time + " windy";
            case RAINY:
                return "Today's forecast: \n   " + time + " rainy";
            case SNOWY:
                return "Today's forecast: \n   " + time + " snowy";
            default:
                return "Some strange weather out here.";
        }
    }
}
