package com.company;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;


@Getter
@RequiredArgsConstructor
class Transition {
    private final int startState;
    private ArrayList<Integer> endStates = new ArrayList<>();
    private final char symbol;
    void addEndState(Integer endState){
        endStates.add(endState);
    }
}