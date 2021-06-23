package decorator;

public interface Environment {
    Environment getEnvironment();

    String howIsTheWeather(String time);
}
