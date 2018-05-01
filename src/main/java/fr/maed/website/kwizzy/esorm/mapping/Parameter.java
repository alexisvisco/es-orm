package fr.maed.website.kwizzy.esorm.mapping;

import org.json.JSONObject;

public class Parameter {
    JSONObject jo;

    Parameter(JSONObject jo) {
        this.jo = jo;
    }

    public Parameter analyser(String value) {
        jo.put("analyser", value);
        return this;
    }

    public Parameter normalizer(String value) {
        jo.put("normalizer", value);
        return this;
    }

    public Parameter boost(double value) {
        jo.put("boost", value);
        return this;
    }

    public Parameter coerce(boolean value) {
        jo.put("coerce", value);
        return this;
    }

    public Parameter copyTo(String field) {
        jo.put("copy_to", field);
        return this;
    }

    public Parameter docValue(boolean value) {
        jo.put("doc_value", value);
        return this;
    }

    public Parameter enabled(boolean value) {
        jo.put("enabled", value);
        return this;
    }

    public Parameter eagerGlobalOrdinals(boolean value) {
        jo.put("eager_global_ordinals", value);
        return this;
    }

    public Parameter fieldData(boolean value) {
        jo.put("fielddata", value);
        return this;
    }

    public Parameter format(String value) {
        jo.put("format", value);
        return this;
    }

    public Parameter ignoreAbove(int value) {
        jo.put("ignore_above", value);
        return this;
    }

    public Parameter ignoreMalformated(boolean value) {
        jo.put("ignore_malformed", value);
        return this;
    }

    public Parameter index(boolean value) {
        jo.put("index", value);
        return this;
    }

    public Parameter norms(boolean value) {
        jo.put("norms", value);
        return this;
    }

    public Parameter indexOptions(IndexOption option) {
        jo.put("index_options", option.value);
        return this;
    }

    public Parameter fields(Fields fields) {
        jo.put("fields", fields);
        return this;
    }

    public Parameter nullValue(Object value) {
        jo.put("null_value", value);
        return this;
    }

    public Parameter positionIncrementGap(int value) {
        jo.put("position_increment_gap", value);
        return this;
    }

    public Parameter properties(Fields field) {
        jo.put("properties", field);
        return this;
    }

    public Parameter searchAnalyser(String value) {
        jo.put("search_analyzer", value);
        return this;
    }

    public Parameter similarity(Similarity sim) {
        jo.put("similarity", sim.value);
        return this;
    }

    public Parameter termVector(TermVector vec) {
        jo.put("term_vector", vec);
        return this;
    }
}
