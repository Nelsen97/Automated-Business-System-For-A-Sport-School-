package kg.nail.automatedbusinesssystemforasportsschool.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Source {

    SOCIAL_NETWORK("Соцсети"), FAMILY_OR_FRIENDS("Семья или друзья"), POSTERS("Постеры");

    private final String name;

    public static Source fromString(String value) {
        for (Source source : Source.values()) {
            if (source.name.equalsIgnoreCase(value)) {
                return source;
            }
        }
        throw new IllegalArgumentException("No constant with name " + value + " found for enum Source");
    }
}
