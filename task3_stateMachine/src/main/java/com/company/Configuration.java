package com.company;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Configuration {
    private final int currentState;
    private final int currentIndex;
}
