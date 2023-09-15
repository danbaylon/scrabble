package com.palo.it.model.types;


import com.palo.it.model.exception.ApplicationException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

@Getter
@Slf4j
public enum Points {

    ONE(1, new char[]{'A', 'E', 'I', 'O', 'U', 'L', 'N', 'S', 'T', 'R'}),
    TWO(2, new char[]{'D', 'G'}),
    THREE(3, new char[]{'B', 'C', 'M', 'P'}),
    FOUR(4, new char[]{'F', 'H', 'V', 'W', 'Y'}),
    SIX(6, new char[]{'K'}),
    EIGHT(8, new char[]{'J', 'X'}),
    TEN(10, new char[]{'Q', 'Z'});

    private int point;
    private char[] characters;

    Points(int point, char[] characters) {
        this.point = point;
        this.characters = characters;
    }

    public static Points getPointsByCharacters(char character) {
        log.debug("This is the entry char: {}", character);
        return Arrays.stream(Points.values())
                .filter(p ->
                        new String(p.characters).chars().mapToObj(c -> (char) c)
                                        .anyMatch(c -> Objects.equals(Character.toUpperCase(character), c)))
                .findAny()
                .orElse(null);
    }
}


