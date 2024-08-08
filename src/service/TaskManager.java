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
            epicMap.get(epicId).addSubtaskId(subtaskId);
            updateEpicStatus(epicId);

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

            Epic epic1 = epicMap.get(epic.getId());
            if (epic1 == null) {
                return;
            }
            epic1.setName(epic.getName());
            epic1.setDescription(epic.getDescription());
        }
    }

    public void updateSubtask(Subtask subtask) {
        if (subtask != null && subtaskMap.containsKey(subtask.getId())) {
            subtaskMap.put(subtask.getId(), subtask);

            updateEpicStatus(subtask.getEpicId());
        }
    }

    //смена статуса Epic исходя из статуса Subtask
    private void updateEpicStatus(int epicId) {
        ArrayList<Integer> subtaskIdArray = epicMap.get(epicId).getSubtaskIdArray();
        ArrayList<Subtask> subtasksArray = new ArrayList<>();
        for (int id : subtaskIdArray) {
            subtasksArray.add(subtaskMap.get(id));
        }
        boolean selectorDone = true;
        boolean selectorNew = true;


        if (subtaskIdArray.isEmpty()) {
            epicMap.get(epicId).setStatus(Status.NEW);
        } else {
            for (Subtask subtask : subtasksArray) {
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
    public ArrayList<Subtask> printArraySubtaskId(int idEpic) {
        ArrayList<Integer> subtaskIdArray = epicMap.get(idEpic).getSubtaskIdArray();
        ArrayList<Subtask> subtasksArray = new ArrayList<>();
        for (int id : subtaskIdArray) {
            subtasksArray.add(subtaskMap.get(id));
        }
        return subtasksArray;
    }


    //блок очистки
    //очистка по ID
    public void deleteTask(int id) {
        taskMap.remove(id);
    }

    public void deleteEpic(int id) {
        for (Integer subtaskId : epicMap.get(id).getSubtaskIdArray()) {
            subtaskMap.remove(subtaskId);
        }
        epicMap.remove(id);
    }

    public void deleteSubtask(int id) {
        int epicId = subtaskMap.get(id).getEpicId();
        epicMap.get(epicId).removeEpicSubtask(id);
        subtaskMap.remove(id);
        updateEpicStatus(epicId);
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
            epic.setStatus(Status.NEW);
        }
    }

    public void deleteAll() {
        System.out.println("Удаление всех задач");
        taskMap.clear();
        subtaskMap.clear();
        epicMap.clear();
    }
}
