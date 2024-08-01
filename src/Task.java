import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Task {
    private String name;
    private String description;
    private int id;
    private Status status;
    private Type type;


    @Override
    public String toString() {
        return "Наименование = " + name +
                ", Description = " + description + "\n" +
                "id = " + id +
                ", status = " + status +
                ", type = " + type + "\n";
    }

    public Task(String name, String description, int id, Status status, Type type) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.status = status;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public Type getType() {
        return type;
    }

    public void setStatus(Status newStatus) {
        this.status = newStatus;
    }
}

class Epic extends Task {

    public Epic(String name, String description, int id, Status status, Type type) {
        super(name, description, id, status, type);
    }


}


class Subtask extends Task {
    private int epicNumber;

    public Subtask(String name, String description, int id, Status status, Type type, int epicNumber) {
        super(name, description, id, status, type);
        this.epicNumber = epicNumber;
    }


    public int getEpicNumber() {
        return epicNumber;
    }

    public void setEpicNumber(int epicNumber) {
        this.epicNumber = epicNumber;
    }


}

