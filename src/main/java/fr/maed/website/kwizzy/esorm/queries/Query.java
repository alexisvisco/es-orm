package fr.maed.website.kwizzy.esorm.queries;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by alexis on 25/10/17.
 * French author.
 */
public abstract class Query
{
    protected JSONObject obj = new JSONObject();
    protected JSONArray arr = new JSONArray();

    public Query()
    {
        init();
    }

    protected abstract void init();

    public JSONObject toJson()
    {
        return obj;
    }

    public JSONArray toJsonArr()
    {
        return arr;
    }

    public void setParam(String key, Object value) {
        obj.put(key, value);
    }
}
