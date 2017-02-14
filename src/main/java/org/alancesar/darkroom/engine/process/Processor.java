package org.alancesar.darkroom.engine.process;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Processor {

    private List<Process> processes;

    public Processor(Process process) {
        processes = new ArrayList<>();
        processes.add(process);
    }

    public Processor andThen(Process process) {
        processes.add(process);
        return this;
    }
    
    public void execute(File file) {
        File input = file;
        
        for (Process process : processes) {
            input = process.execute(input);
        }
    }

}
