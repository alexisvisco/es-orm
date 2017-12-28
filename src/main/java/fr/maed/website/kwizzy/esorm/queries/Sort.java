package fr.maed.website.kwizzy.esorm.queries;

import org.json.JSONArray;
import org.json.JSONObject;

public class Sort
{

    JSONArray arr = new JSONArray();

    public Sort addProperty(String field, SORT_TYPE type)
    {
        arr.put(new JSONObject().put(field, type.sort));
        return this;
    }

    /**
     * The order defaults to desc when sorting on the _score, and defaults to asc when sorting on anything else.
     *
     * @param field field to apply sorting
     * @return the current object.
     */
    public Sort defaultSort(String field)
    {
        arr.put(field);
        return this;
    }

    public JSONArray getArr()
    {
        return arr;
    }

    public enum SORT_TYPE
    {

        ASC("asc"), // 1 2 3
        DESC("desc"); // 3 2 1

        private String sort;

        SORT_TYPE(String sort)
        {
            this.sort = sort;
        }
    }
}
