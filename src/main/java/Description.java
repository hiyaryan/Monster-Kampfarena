public class Description {

    protected String description;

    /**
     * Constructor.
     *
     * @param name Name
     */
    public Description(Mascotmon.Name name) {
        String n = name.toString();
        switch (n) {
            case "ALBERT":
                description = "The Alligator";
                break;
            case "RALPHIE":
                description = "The Buffalo";
                break;
            case "SPARKY":
                description = "The Sun Devil";
                break;
            case "BULLY":
                description = "The Bull Dog";
                break;
            default:
                description = "New mascot";
                break;
        }
    }
}