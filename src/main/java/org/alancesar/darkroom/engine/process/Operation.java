package org.alancesar.darkroom.engine.process;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;

public class Operation {

    private static final ConvertCmd CMD = new ConvertCmd();

    private List<IMOperation> steps = new ArrayList<>();

    public void addStep(IMOperation step) {
        this.steps.add(step);
    }

    public void addStep(Operation operation) {
        this.steps.addAll(operation.getSteps());
    }

    public List<IMOperation> getSteps() {
        return Collections.unmodifiableList(steps);
    }

    public void execute() {
        for (IMOperation op : steps) {
            try {
                CMD.run(op);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
