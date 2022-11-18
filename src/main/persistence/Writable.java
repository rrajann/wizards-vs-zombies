package persistence;

import org.json.JSONObject;

// is writable
public interface Writable {

    JSONObject toJson();
}
