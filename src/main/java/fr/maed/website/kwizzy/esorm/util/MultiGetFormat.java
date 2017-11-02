package fr.maed.website.kwizzy.esorm.util;

import com.google.gson.Gson;
import fr.maed.website.kwizzy.esorm.ElasticDto;
import org.json.JSONObject;

public class MultiGetFormat {

    private String _index;
    private String _type;
    private String _id;

    public MultiGetFormat(String _index, String _type, String _id) {
        this._index = _index;
        this._type = _type;
        this._id = _id;
    }

    public String get_index() {
        return _index;
    }

    public void set_index(String _index) {
        this._index = _index;
    }

    public String get_type() {
        return _type;
    }

    public void set_type(String _type) {
        this._type = _type;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public static MultiGetFormat fromElasticDto(ElasticDto e) {
        return new MultiGetFormat(e.getIndex(), e.getType(), e.getId());
    }

    public JSONObject toJson()
    {
        return new JSONObject(new Gson().toJson(this));
    }
}
