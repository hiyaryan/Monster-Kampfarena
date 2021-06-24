package decorator.wildeland;

import java.util.Random;

public class Weather extends WildeLandDecorator {
    private Day day;
    private Night night;
    private String weather;

    private enum Day {
        NEUTRAL,
        SUNNY,
        WINDY,
        RAINY,
        SNOWY,
        STRANGE
    }

    private enum Night {
        NEUTRAL,
        TWILIGHT,
        WINDY,
        RAINY,
        SNOWY,
        STRANGE
    }

    public Weather(String date) {
        if(date.contains("Day")) {
            this.weather = determineTheDayWeatherForecast();

        } else if(date.contains("Night")) {
            this.weather = determineTheNightWeatherForecast();

        } else {
            System.out.println("Dark Hour");
        }
    }

    @Override
    public String determineTheDayWeatherForecast() {
        int forecast = new Random().nextInt(5) + 1;

        switch (forecast) {
            case 1:
                day = Day.NEUTRAL;
                break;
            case 2:
                day = Day.SUNNY;
                break;
            case 3:
                day = Day.WINDY;
                break;
            case 4:
                day = Day.RAINY;
                break;
            case 5:
                day = Day.SNOWY;
                break;
            default:
                day = Day.STRANGE;
                break;
        }

        return day.toString();
    }

    @Override
    public String determineTheNightWeatherForecast() {
        int forecast = new Random().nextInt(5) + 1;

        switch (forecast) {
            case 1:
                night = Night.NEUTRAL;
                break;
            case 2:
                night = Night.TWILIGHT;
                break;
            case 3:
                night = Night.WINDY;
                break;
            case 4:
                night = Night.RAINY;
                break;
            case 5:
                night = Night.SNOWY;
                break;
            default:
                night = Night.STRANGE;
                break;
        }

        return night.toString();
    }

    @Override
    public String howIsTheWeather(String date) {
        if(date.contains("Day")) {
            switch (day) {
                case NEUTRAL:
                    return "Today's forecast: \n   " + date + " neutral\n";
                case SUNNY:
                    return "Today's forecast: \n   " + date + " sunny\n";
                case WINDY:
                    return "Today's forecast: \n   " + date + " windy\n";
                case RAINY:
                    return "Today's forecast: \n   " + date + " rainy\n";
                case SNOWY:
                    return "Today's forecast: \n   " + date + " snowy\n";
                default:
                    return "Some strange weather out here.";
            }

        } else if(date.contains("Night")) {
            switch (night) {
                case NEUTRAL:
                    return "Tonight's forecast: \n   " + date + " neutral\n";
                case TWILIGHT:
                    return "Tonight's forecast: \n   " + date + " twilight\n";
                case WINDY:
                    return "Tonight's forecast: \n   " + date + " windy\n";
                case RAINY:
                    return "Tonight's forecast: \n   " + date + " rainy\n";
                case SNOWY:
                    return "Tonight's forecast: \n   " + date + " snowy\n";
                default:
                    return "Some strange weather out here.";
            }

        } else {
            return "and time stood still.";
        }
    }

    @Override
    public String getWeather() {
        return this.weather;
    }
}
