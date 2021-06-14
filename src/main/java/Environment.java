public class Environment {

    public Weather weather;
    public String buffedType;
    public String debuffedType;

    /**
     * Environment constructor.
     *
     * @param weather Weather
     */
    public Environment(Weather weather) {
        this.weather = weather;
        switch (weather) {
            case sunny:
                this.buffedType = "Fire";
                this.debuffedType = "Water";
                break;
            case rainy:
                this.buffedType = "Water";
                this.debuffedType = "Fire";
                break;
            case drought:
                this.buffedType = "Ground";
                this.debuffedType = "Normal";
                break;
            default:
                this.buffedType = "";
                this.debuffedType = "";
                break;
        }
    }

    public String getBuffedType() {
        return buffedType;
    }

    public String getDebuffedType() {
        return debuffedType;
    }

    public enum Weather {
        sunny, rainy, drought, neutral
    }
}