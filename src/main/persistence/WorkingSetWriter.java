package persistence;

import model.WorkingSet;
import org.json.JSONObject;

public class WorkingSetWriter extends Writer {

    public WorkingSetWriter(String destination) {
        super(destination);
    }

    public void write(WorkingSet ws) {
        JSONObject json = ws.toJson();
        save(json.toString(TAB));
    }
}
