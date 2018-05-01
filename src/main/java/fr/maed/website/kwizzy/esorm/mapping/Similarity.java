package fr.maed.website.kwizzy.esorm.mapping;

public enum Similarity {
    BM25("BM25"),
    CLASSIC("classic"),
    BOOLEAN("boolean")
    ;
    String value;

    Similarity(String value) {
        this.value = value;
    }
}
