package fr.maed.website.kwizzy.esorm;

import com.google.gson.Gson;
import org.json.JSONObject;

/**
 * Created by alexis on 25/10/17.
 * French author.
 */
public class ElasticDto
{
    private String _index;
    private String _type;
    private String _score;
    private String _id;
    private int _total;

    private JSONObject _source;

    public String getIndex()
    {
        return _index;
    }

    public String getScore()
    {
        return _score;
    }

    public String getId()
    {
        return _id;
    }

    public int getTotal() { return _total; }

    public <T> T getSource(Class<T> dto) {
        return new Gson().fromJson(_source.toString(), dto);
    }

    public void setSource(JSONObject source) {
        this._source = source;
    }

    public JSONObject toMultiGetFormat()
    {
        return new JSONObject().put("_index", _index).put("_id", _id).put("_type", _type);
    }

    @Override
    public String toString() {
        return "ElasticDto{" +
                "_index='" + _index + '\'' +
                ", _type='" + _type + '\'' +
                ", _score='" + _score + '\'' +
                ", _id='" + _id + '\'' +
                ", _source=" + _source +
                '}';
    }
}
