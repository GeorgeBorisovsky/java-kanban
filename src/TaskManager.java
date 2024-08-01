import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private int idCount;



    protected final HashMap<Integer, Task> taskMap = new HashMap<>();
    protected final HashMap<Integer, Epic> epicMap = new HashMap<>();
    protected final HashMap<Integer, Subtask> subtaskMap = new HashMap<>();
    protected final ArrayList<ArrayList<Object>> treeEpicChildArray = new ArrayList<>();


    public TaskManager() {
        this.idCount = 0;

    }

    public int getTaskID() {
        return ++idCount;
    }

    public int getEpicID() {
        return ++idCount;
    }

    public int getSubtaskID() {
        return ++idCount;
    }


    public void addNewTask(Task task) {
        if (task != null) {
            taskMap.put(task.getId(), task);
        }
    }

     void updateTask(Task updateTask) {
        if (updateTask != null && taskMap.containsKey(updateTask.getId())) {
            taskMap.put(updateTask.getId(), updateTask);
        }
     }

    public void addNewEpic(Epic epic) {
        if (epic != null) {
            epicMap.put(epic.getId(), epic);
        }
    }

    public void updateEpic(Epic epic) {
        if(epic != null && epicMap.containsKey(epic.getId())) {
            epicMap.put(epic.getId(), epic);
        }
    }

    public void addNewSubtask(Subtask subtask) {
        if (subtask != null) {
            subtaskMap.put(subtask.getId(), subtask);
        }

    }

    public void updateSubtask(Subtask subtask) {
        if(subtask != null && subtaskMap.containsKey(subtask.getId())) {
            subtaskMap.put(subtask.getId(), subtask);

            System.out.println(subtask.getEpicNumber());


        }
    }

    public Task getTaskId(int id) {
        return taskMap.get(id);
    }
    public Epic getEpicId(int id) {
        return epicMap.get(id);
    }
    public Subtask getSubtaskId(int id) {
        return subtaskMap.get(id);
    }


    public void deleteTask(int id){
        taskMap.remove(id);
    }

    public void printAllTasks() {
        if (!taskMap.isEmpty()) {
            System.out.println("Список всех задач: " );
            for (HashMap.Entry<Integer, Task> entry : taskMap.entrySet()) {
                System.out.println(entry.getValue());
            }
        } else {
            System.out.println("Список задач пуст");
        }
    }
    public void printAllEpics() {
        if (!epicMap.isEmpty()) {
            System.out.println("Список всех эпиков:");
            for (HashMap.Entry<Integer, Epic> entry : epicMap.entrySet()) {
                System.out.println(entry.getValue());
            }
        } else {
            System.out.println("Список эпиков пуст");
        }
    }

    public void printAllSubtask() {
        if (!subtaskMap.isEmpty()) {
            System.out.println("Список всех подазадач:");
            for(HashMap.Entry<Integer, Subtask> entry : subtaskMap.entrySet()) {
                System.out.println(entry.getValue());
            }
        } else {
            System.out.println("Список подзадач пуст");
        }

    }

    public void changeStatusTask(Task name, Status status) {
        Integer taskID = name.getId();
        if (taskMap.containsKey(taskID)) {
            name.setStatus(status);
            System.out.println("Статус задачи изменен на "+ name.getStatus());
        } else {
            System.out.println("Такой задачи не существует");
        }
    }
    public void changeStatusEpic(Epic name, Status status) {
        Integer epicID = name.getId();
        if (epicMap.containsKey(epicID)) {
            name.setStatus(status);
            System.out.println("Статус задачи изменен на "+ name.getStatus());
        } else {
            System.out.println("Такой задачи не существует");
        }
    }
    public void changeStatusSubtask(Subtask name, Status status) {
        Integer sabtaskID = name.getId();
        Integer sabtaskEpikID = name.getEpicNumber();
        if (subtaskMap.containsKey(sabtaskID)) {
            name.setStatus(status);
            System.out.println("Статус задачи изменен на "+ name.getStatus());
            Epic step = epicMap.get(sabtaskEpikID);
            step.setStatus(status);
        } else {
            System.out.println("Такой задачи не существует");
        }
    }

    public void deleteAll() {
        System.out.println("Удаление всех задач");
        treeEpicChildArray.clear();
        taskMap.clear();
        subtaskMap.clear();
        epicMap.clear();
    }
}
