package model;

import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Integer> subtaskIdArray = new ArrayList<>();

    public Epic(String name, String description, Status status, Type type) {
        super(name, description, status, type);
    }

    public void addSubtaskId(int subtaskId){
        subtaskIdArray.add(subtaskId);
    }


    public ArrayList<Integer> getSubtaskIdArray() {
        return subtaskIdArray;
    }

    public void setSubtaskIdArray(ArrayList<Integer> subtaskIdArray) {
        this.subtaskIdArray = subtaskIdArray;
    }

    public void clearSubtaskArray() {
        subtaskIdArray.clear();
    }

    public void removeEpicSubtask(Integer idSubtask) {
        subtaskIdArray.remove(idSubtask);
    }
}
