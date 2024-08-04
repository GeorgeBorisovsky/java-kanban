package model;

import java.util.ArrayList;

public class Epic extends Task {
    final private ArrayList<Subtask> subtaskArray = new ArrayList<>();

    public Epic(String name, String description, Status status, Type type) {
        super(name, description, status, type);
    }

    public void addSubtask(Subtask subtask){
        subtaskArray.add(subtask);
    }


    public ArrayList<Subtask> getSubtaskArray() {
        return subtaskArray;
    }

    public void clearSubtaskArray() {
        subtaskArray.clear();
    }

    public void deleteSubtaskArray(Integer idSubtask) {
        subtaskArray.remove(idSubtask);
    }
}
