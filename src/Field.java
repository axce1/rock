import java.util.HashMap;

public class Field {
    private HashMap<String, String> state;

    public void setState(HashMap<String, String> state) {
        this.state = state;
    }

    public HashMap<String, String> getState() {
        return state;
    }
}
