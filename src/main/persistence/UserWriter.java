package persistence;

import model.User;
import org.json.JSONObject;

public class UserWriter extends Writer {
    public UserWriter(String destination) {
        super(destination);
    }

    public void write(User u) {
        JSONObject json = u.toJson();
        save(json.toString(TAB));
    }
}
