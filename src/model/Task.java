package model;

public class Task {
    private String name;
    private String description;
    private int id;
    private Status status;
    private Type type;



    public Task(String name, String description, Status status, Type type) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
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




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Наименование = " + name +
                ", Description = " + description + "\n" +
                "id = " + id +
                ", status = " + status +
                ", type = " + type + "\n";
    }
}


