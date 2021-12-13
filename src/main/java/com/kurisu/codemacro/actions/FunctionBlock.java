package com.kurisu.codemacro.actions;

import java.util.List;
import java.util.Map;

public class FunctionBlock implements Action {

    private List<Action> actionsBlock;
    private Map<String, Object> parameters;

    public FunctionBlock(List<Action> actionsBlock){
        this.actionsBlock = actionsBlock;
        this.parameters = parameters;
    }
    



    @Override
    public void doAction() {
        // TODO Auto-generated method stub
        
    }
    
}
