package org.miguquis.music.dev_Interface;

import org.json.JSONObject;

public interface Format {
    public boolean equals(Object obj);
    public String toString();
    public JSONObject toJSONObject();
}
