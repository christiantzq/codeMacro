package com.kurisu.codemacro.actions;

import java.util.List;
import java.util.stream.IntStream;

public class RepeatingBlock implements Action {

    private List<Action> actionsBlock;
    private int totalRepetitions;

    public RepeatingBlock(List<Action> actionsBlock, int totalRepetitions) {
        this.actionsBlock = actionsBlock;
        this.totalRepetitions = totalRepetitions;
    }

    @Override
    public void doAction() {

        IntStream.range(0, totalRepetitions)
                .forEach(i -> actionsBlock.stream()
                        .forEach(Action::doAction));

    }

}
