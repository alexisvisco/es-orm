package fr.maed.website.kwizzy.esorm.mapping;

public enum IndexOption {
    DOCS("docs"),
    FREQS("freqs"),
    POSITIONS("positions"),
    OFFSETS("offsets"),
    ;
    String value;

    IndexOption(String value) {
        this.value = value;
    }
}
