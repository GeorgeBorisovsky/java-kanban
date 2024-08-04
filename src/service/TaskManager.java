package service;

import model.*;

import java.util.ArrayList;
import java.util.HashMap;


public class TaskManager {
    private int idCount;

    protected final HashMap<Integer, Task> taskMap = new HashMap<>();
    protected final HashMap<Integer, Epic> epicMap = new HashMap<>();
    protected final HashMap<Integer, Subtask> subtaskMap = new HashMap<>();


    public TaskManager() {
        this.idCount = 0;

    }

    private int generateId() {
        return ++idCount;
    }

    //добавдение задач
    public void addNewTask(Task task) {
        if (task != null) {
            int taskId = generateId();
            task.setId(taskId);
            taskMap.put(taskId, task);
        }
    }

    //добавление эпиков
    public void addNewEpic(Epic epic) {
        if (epic != null) {
            int epicId = generateId();
            epic.setId(epicId);
            epicMap.put(epicId, epic);
        }
    }

    //добавление подзадач
    public void addNewSubtask(Subtask subtask) {
        if (subtask != null) {
            int subtaskId = generateId();
            subtask.setId(subtaskId);
            subtaskMap.put(subtaskId, subtask);

            int epicId = subtask.getEpicId();
            epicMap.get(epicId).addSubtask(subtask);

        }
    }

    //обновление задач
    public void updateTask(Task task) {
        if (task != null && taskMap.containsKey(task.getId())) {
            int id = task.getId();
            Task task1 = taskMap.get(id);
            if (task1 == null) {
                return;
            }
            taskMap.put(id, task);
        }
    }

    public void updateEpic(Epic epic) {
        if (epic != null && epicMap.containsKey(epic.getId())) {
            int id = epic.getId();
            Epic epic1 = epicMap.get(epic.getId());
            if (epic1 == null) {
                return;
            }
            epicMap.put(id, epic);
            //смена статусов всех подзадач как у эпика
            for (Subtask subtask : epic.getSubtaskArray()) {
                subtask.setStatus(epic.getStatus());
            }

        }
    }

    public void updateSubtask(Subtask subtask) {
        if (subtask != null && subtaskMap.containsKey(subtask.getId())) {
            subtaskMap.put(subtask.getId(), subtask);

            updateEpicStatus(subtask.getEpicId());
        }
    }

    //смена статуса Epic исходя из статуса Suubtask
    public void updateEpicStatus(int epicId) {
        ArrayList<Subtask> changeS = getEpicId(epicId).getSubtaskArray();
        boolean selectorDone = true;
        boolean selectorNew = true;


        if (changeS.isEmpty()) {
            epicMap.get(epicId).setStatus(Status.NEW);
        } else {
            for (Subtask subtask : changeS) {
                if (!subtask.getStatus().equals(Status.DONE)) {
                    selectorDone = false;
                }
                if (!subtask.getStatus().equals(Status.NEW)) {
                    selectorNew = false;
                }
                if (!selectorNew && !selectorDone) {
                    break;
                }

            }
            if (selectorDone) {
                epicMap.get(epicId).setStatus(Status.DONE);
            } else if (selectorNew) {
                epicMap.get(epicId).setStatus(Status.NEW);
            } else {
                epicMap.get(epicId).setStatus(Status.IN_PROGRESS);
            }
        }
    }


    //получение задачи по ID
    public Task getTaskId(int id) {
        return taskMap.get(id);
    }

    public Epic getEpicId(int id) {
        return epicMap.get(id);
    }

    public Subtask getSubtaskId(int id) {
        return subtaskMap.get(id);
    }


    //печать списков
    public ArrayList<Task> printAllTasks() {
        if (taskMap.isEmpty()) {
            System.out.println("Список задач пуст");
        }
        return new ArrayList<>(taskMap.values());
    }

    public ArrayList<Epic> printAllEpics() {
        if (epicMap.isEmpty()) {
            System.out.println("Список эпиков пуст");
        }
        return new ArrayList<>(epicMap.values());
    }

    public ArrayList<Subtask> printAllSubtask() {
        if (subtaskMap.isEmpty()) {
            System.out.println("Список подзадач пуст");
        }
        return new ArrayList<>(subtaskMap.values());
    }

    //получение списка подзадач по Id эпика
    public ArrayList<Subtask> printArraySubtaskId(int id) {
        if (!epicMap.containsKey(id)) {
            return null;
        }
        return epicMap.get(id).getSubtaskArray();
    }


    //блок очистки
    //очистка по ID
    public void deleteTask(int id) {
        taskMap.remove(id);
    }

    public void deleteEpic(int id) {
        for (Subtask subtask : getEpicId(id).getSubtaskArray()){
            subtaskMap.remove(subtask.getId());
        }
        epicMap.remove(id);
    }

    public void deleteSubtask(int id) {
        int epicId = subtaskMap.get(id).getEpicId();
        epicMap.get(epicId).deleteSubtaskArray(id);
        subtaskMap.remove(id);
    }

    // очистка структур
    public void clearTasks() {
        taskMap.clear();
    }

    public void clearEpics() {
        epicMap.clear();
        subtaskMap.clear();
    }

    public void clearSubtasks() {
        subtaskMap.clear();
        for (Epic epic : epicMap.values()) {
            epic.clearSubtaskArray();
            updateEpicStatus(epic.getId());
        }
    }

    public void deleteAll() {
        System.out.println("Удаление всех задач");
        taskMap.clear();
        subtaskMap.clear();
        epicMap.clear();
    }
}
