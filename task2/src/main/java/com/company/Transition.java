package com.company;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
class Transition {
    private int startState;
    private int endState;
    private char symbol;
}