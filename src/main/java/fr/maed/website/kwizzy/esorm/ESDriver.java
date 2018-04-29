package fr.maed.website.kwizzy.esorm;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Created by alexis on 25/10/17.
 * French author.
 */
public class ESDriver
{

    private int port;
    private String url;
    private String base;
    private String collectionName;

    public ESDriver(int port, String url, String base, String collectionName)
    {
        this.port = port;
        this.url = url;
        this.base = base;
        this.collectionName = collectionName;
        updateIndex();
    }

    public ESDriver(int port, String url, String base, String collectionName, boolean index)
    {
        this.port = port;
        this.url = url;
        this.base = base;
        this.collectionName = collectionName;
        if (index)
            updateIndex();
    }

    public ESDriver(int port, String url, String base, String collectionName, Consumer<ESDriver> updateIndexFn)
    {
        this.port = port;
        this.url = url;
        this.base = base;
        this.collectionName = collectionName;
        updateIndexFn.accept(this);
    }

    public int getPort()
    {
        return port;
    }

    public String getUrl()
    {
        return url;
    }

    public String getBase()
    {
        return base;
    }

    public String getCollectionName()
    {
        return collectionName;
    }

    /**
     * Update index by adding location propriety to geo_point.
     */
    private void updateIndex()
    {
        try {
            HttpResponse<JsonNode> accept = Unirest.put(url + ":" + port + "/" + base)
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body("{\n" +
                                  "  \"mappings\":\n" +
                                  "  {\n" +
                                  "    \"" + collectionName + "\":\n" +
                                  "    {\n" +
                                  "      \"properties\":\n" +
                                  "      {\n" +
                                  "        \"location\": { \"type\":\"geo_point\" },\n" +
                                  "        \"opened\" : {\"type\": \"date\", \"format\": \"epoch_millis\"}\n" +
                                  "      }\n" +
                                  "    }\n" +
                                  "  }\n" +
                                  "}")
                    .asJson();

        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add to {base}/{document} the dto, in gson format by new Gson().toJson({dto}).
     *
     * @param document a dto to add.
     */
    public Optional<JSONObject> addDocument(Object document)
    {
        try {
            return Optional.ofNullable(Unirest.post(url + ":" + port + "/" + base + "/" + collectionName)
                                               .header("accept", "application/json")
                                               .header("Content-Type", "application/json")
                                               .body(new Gson().toJson(document))
                                               .asJson().getBody().getObject());
        } catch (UnirestException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * Add to {base}/{document}/{id} the dto, in gson format by new Gson().toJson({dto}).
     *
     * @param document a dto to add.
     */
    public Optional<JSONObject> addDocument(Object document, String id)
    {
        try {
            return Optional.ofNullable(Unirest.post(url + ":" + port + "/" + base + "/" + collectionName + "/" + id)
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(new Gson().toJson(document))
                    .asJson().getBody().getObject());
        } catch (UnirestException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * Delete to {base}/{document}/_delete with the id, the id is _id of esOffers.
     *
     * @param id of the document to delete.
     */
    public Optional<JSONObject> deleteDocument(String id)
    {
        try {
            return Optional.ofNullable(Unirest.delete(url + ":" + port + "/" + base + "/" + collectionName + "/" + id)
                                               .header("accept", "application/json")
                                               .header("Content-Type", "application/json")
                                               .asJson().getBody().getObject());
        } catch (UnirestException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * Update to {base}/{document}/{id}/_update, update point.
     *
     * @param document
     */
    public Optional<JSONObject> updateDocument(Object document, String id)
    {
        try {
            return Optional.ofNullable(Unirest.post(url + ":" + port + "/" + base + "/" + collectionName + "/" + id + "/_update")
                                               .header("accept", "application/json")
                                               .header("Content-Type", "application/json")
                                               .body(new JSONObject().put("doc", new JSONObject(new Gson().toJson(document))))
                                               .asJson().getBody().getObject());

        } catch (UnirestException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * Build query with a post request to {base}/{document}/{id}/_search
     *
     * @param query query builder
     * @return full json query returned by elasticsearch.
     */
    public Optional<JSONObject> search(QueryBuilder query)
    {
        try {
            HttpResponse<JsonNode> returned = Unirest.post(url + ":" + port + "/" + base + "/" + collectionName + "/_search")
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(query.getJson())
                    .asJson();
            return Optional.ofNullable(returned.getBody().getObject());
        } catch (UnirestException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    private Optional<JSONObject> searchInBase(String field, String value)
    {
        try {
            HttpResponse<JsonNode> returned = Unirest.get(url + ":" + port + "/" + base + "/_search?q=" + field + ":" + value).asJson();
            return Optional.ofNullable(returned.getBody().getObject());
        } catch (UnirestException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    private Optional<JSONObject> searchInCollection(String field, String value)
    {
        try {
            HttpResponse<JsonNode> returned = Unirest.get(url + ":" + port + "/" + base + "/" + collectionName + "/_search?q=" + field + ":" + value).asJson();
            return Optional.ofNullable(returned.getBody().getObject());
        } catch (UnirestException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<JSONObject> deleteQuery(QueryBuilder query)
    {
        try {
            HttpResponse<JsonNode> returned = Unirest.post(url + ":" + port + "/" + base + "/" + collectionName + "/_delete_by_query")
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(query.getJson())
                    .asJson();
            return Optional.ofNullable(returned.getBody().getObject());
        } catch (UnirestException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * Return the result from elasticsearch request in a list of ElasticDto object
     *
     * @param query from QueryBuilder class
     * @return a list of ElasticDto
     */
    public List<ElasticDto> searchToEsDto(QueryBuilder query)
    {
        Optional<JSONObject> j = search(query);
        if (j.isPresent() && j.get().has("hits"))
            return getListFromSearch(j.get());
        return new ArrayList<>();
    }

    /**
     * Return the result from elasticsearch request in a list of ElasticDto object
     *
     * @param String field and string value
     * @return a list of ElasticDto
     */
    public List<ElasticDto> searchToEsDto(String field, String value, boolean onlyInCollection)
    {
        Optional<JSONObject> j;
        if (onlyInCollection)
            j = searchInCollection(field, value);
        else
            j = searchInBase(field, value);
        if (j.isPresent() && j.get().has("hits"))
            return getListFromSearch(j.get());
        return new ArrayList<>();
    }


    /**
     * Transform a list of elastictdto into a list of <U>
     *
     * @param query from QueryBuilder class
     * @param dto   your dto to be transformed by gson thanks to the getSource method in elasticdto
     * @param <U>   mapped by the dto class (dto parameter)
     * @return a list of <U> dto.
     */
    public <U> List<U> searchToDtos(QueryBuilder query, Class<U> dto)
    {
        return searchToEsDto(query).stream().map(e -> e.getSource(dto)).collect(Collectors.toList());
    }

    /**
     * Transform a list of elastictdto into a list of <U>
     *
     * @param field
     * @param value
     * @param onlyInCollection
     * @param dto   your dto to be transformed by gson thanks to the getSource method in elasticdto
     * @param <U>   mapped by the dto class (dto parameter)
     * @return a list of <U> dto.
     */
    public <U> List<U> searchToDtos(String field, String value, boolean onlyInCollection, Class<U> dto)
    {
        return searchToEsDto(field, value, onlyInCollection).stream().map(e -> e.getSource(dto)).collect(Collectors.toList());
    }


    /**
     * Get a list of dto that match with the query
     *
     * @param fromExecutedQuery from query builder
     * @return a list of dto
     */
    private List<ElasticDto> getListFromSearch(JSONObject fromExecutedQuery)
    {
        Gson gson = new Gson();
        JSONObject hits = fromExecutedQuery.getJSONObject("hits");
        if (hits.getInt("total") > 0) {
            List<ElasticDto> objects = new ArrayList<>();
            hits.getJSONArray("hits").forEach((Object e) -> {
                JSONObject jo = (JSONObject) e;
                JSONObject source = jo.getJSONObject("_source");
                jo.remove("_source");
                ElasticDto elasticDto = gson.fromJson(jo.toString(), ElasticDto.class);
                elasticDto.setSource(source);
                elasticDto.setTotal(hits.getInt("total"));

                objects.add(elasticDto);
            });
            return objects;
        }
        return new ArrayList<>();
    }

    /**
     * Return the document indexed at the {id} provided
     * Can be empty if elasticsearch didn't find the id
     *
     * @param id of the document get by field _id on elasticsearch
     * @return json returned by elasticsearch.
     */
    public Optional<JSONObject> fromId(String id)
    {
        try {
            HttpResponse<JsonNode> returned = Unirest.get(url + ":" + port + "/" + base + "/" + collectionName + "/" + id)
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .asJson();
            return Optional.ofNullable(returned.getBody().getObject());
        } catch (UnirestException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * Return the document indexed at the {id} provided
     * Can be empty if elasticsearch didn't find the id
     *
     * @param id  of the document get by field _id on elasticsearch
     * @param dto your dto transformed by Gson
     * @param <U> mapped by the dto class (dto parameter)
     * @return an optional <U>
     */
    public <U> Optional<U> fromId(String id, Class<U> dto)
    {
        Optional<JSONObject> j = fromId(id);
        if (j.isPresent() && !j.get().isNull("_source"))
            return Optional.ofNullable(new Gson().fromJson(j.get().getJSONObject("_source").toString(), dto));
        return Optional.empty();
    }

    /**
     * @param query the query you want to be executed
     * @return a integer of count of all documents searched.
     */
    public int count(QueryBuilder query)
    {
        try {
            HttpResponse<JsonNode> returned = Unirest.post(url + ":" + port + "/" + base + "/" + collectionName + "/_count")
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(query.getJson())
                    .asJson();
            return returned.getBody().getObject().getInt("count");
        } catch (UnirestException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void clearAllDocuments()
    {
        try {
            HttpResponse<JsonNode> response = Unirest.delete(url + ":" + port + "/" + base + "/").asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    public void addAll(List<Object> objects)
    {
        objects.forEach(object -> this.addDocument(object));
    }
}
