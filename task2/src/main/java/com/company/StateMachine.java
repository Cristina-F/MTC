package com.company;

import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.IOException;
import java.io.Reader;
import java.util.*;

@RequiredArgsConstructor
public class StateMachine {
    private final Reader reader;
    @Setter
    private Reader word;
    private int currentState = 0;
    private ArrayList<Integer> endStates = new ArrayList<>();
    private ArrayList<Transition> transitions = new ArrayList<>();

    public boolean run()  {
        try {
            if ( endStates.isEmpty()) parser();
            return process();
        } catch (IOException | StateMachineExeption e) {
            return false;
        }
    }

    private void parser() throws StateMachineExeption{
        Scanner in = new Scanner(reader);
        String[] ends = in.nextLine().split(" ");
        for (String finalCond : ends) {
            endStates.add(Integer.valueOf(finalCond));
        }
        while (in.hasNextLine()) {
            String[] transition = in.nextLine().split(" ");
            if (transition.length != 3)
                throw new StateMachineExeption("Bad transition");
            int startState = Integer.parseInt(transition[0]);
            char symbol = transition[1].charAt(0);
            int endState = Integer.parseInt(transition[2]);
            transitions.add(new Transition(startState, endState, symbol));
        }
    }

    private boolean process() throws IOException {
        int currentSymbol;
        while ((currentSymbol = word.read()) != -1) {
            Transition transition = new Transition(currentState, -1, (char)currentSymbol);
            boolean isFound = false;
            for (Transition transition1: transitions){
                if (transition1.getStartState() == transition.getStartState() &&
                    transition1.getSymbol() == transition.getSymbol()){
                    currentState = transition1.getEndState();
                    isFound = true;
                }
            }
            if(!isFound){
                return false;
            }
        }
        return endStates.contains(currentState);
    }
}
