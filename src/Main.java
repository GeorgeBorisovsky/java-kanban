import model.*;
import service.TaskManager;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();


        Task dog;
        Task bicikle;

        Epic dogWalking;
        Subtask dogDressCode;
        Subtask dogPlayGame;


        Epic bicikleChekPoint;
        Subtask bicikleWheel;

        Epic testepic;

        dog = new Task("Задача 1", "гулять с собакой", Status.NEW, Type.TASK);
        bicikle = new Task("Задача 2", "кататься на велосипеде", Status.NEW, Type.TASK);
        dogWalking = new Epic("Эпик 1", "description", Status.NEW, Type.EPIC);
        bicikleChekPoint = new Epic("Эпик 2", "description", Status.NEW, Type.EPIC);
        testepic = new Epic("Эпик 3", "description", Status.NEW, Type.EPIC);
        dogDressCode = new Subtask("Подзадача 1 Эпик 1", "description", Status.NEW, Type.SUBTASK, 3);
        dogPlayGame = new Subtask("Подзадача 2 Эпик 1", "description", Status.NEW, Type.SUBTASK, 3);
        bicikleWheel = new Subtask("Подзадача 1 Эпик 2", "description", Status.NEW, Type.SUBTASK, 4);

        // добавление задач
        taskManager.addNewTask(dog);
        taskManager.addNewTask(bicikle);
        taskManager.addNewEpic(dogWalking);
        taskManager.addNewEpic(bicikleChekPoint);
        taskManager.addNewSubtask(dogDressCode);
        taskManager.addNewSubtask(dogPlayGame);
        taskManager.addNewSubtask(bicikleWheel);
        taskManager.addNewEpic(testepic);



        // печать всех задач
        System.out.println("печать всех задач" + taskManager.printAllTasks());
        System.out.println("печать всех эпиков" + taskManager.printAllEpics());
        System.out.println("печать всех подзадач" + taskManager.printAllSubtask());

        //получение списка подзадач по Id эпика
        System.out.println("получение списка подзадач по Id эпика");
        System.out.println(taskManager.printArraySubtaskId(3));

        //смена статуса задач с проверкой эпика
        System.out.println("+++++ смена статуса task +++++");
        Task taskUpdate = taskManager.getTaskId(1);
        taskUpdate.setStatus(Status.IN_PROGRESS);
        taskManager.updateTask(taskUpdate);
        System.out.println(taskManager.getTaskId(1));

        System.out.println("смена статуса epic");
        Epic epicUpdate = taskManager.getEpicId(3);
        epicUpdate.setStatus(Status.IN_PROGRESS);
        taskManager.updateEpic(epicUpdate);
        System.out.println(taskManager.getEpicId(3));
        System.out.println(taskManager.printArraySubtaskId(3));

        System.out.println("смена статуса subtask");
        Subtask subtaskUpdate = taskManager.getSubtaskId(5);
        subtaskUpdate.setStatus(Status.DONE);
        taskManager.updateSubtask(subtaskUpdate);
        System.out.println(taskManager.getSubtaskId(5));
        System.out.println(taskManager.getEpicId(3));
        System.out.println(taskManager.printArraySubtaskId(3));
        subtaskUpdate = taskManager.getSubtaskId(6);
        subtaskUpdate.setStatus(Status.DONE);
        taskManager.updateSubtask(subtaskUpdate);
        System.out.println(taskManager.getSubtaskId(5));
        System.out.println(taskManager.getEpicId(3));
        System.out.println(taskManager.printArraySubtaskId(3));

        //Смена названия task
        System.out.println("Смена названия");
        Task updateNameTask = taskManager.getTaskId(1);
        updateNameTask.setName("Лягушка");
        taskManager.updateTask(updateNameTask);
        System.out.println(taskManager.getTaskId(1));

        //получить по ID
        System.out.println("получить по ID");
        System.out.println(taskManager.getTaskId(1));


        //очистка эпика по ID с проверкой очистки сабтаск
        System.out.println("удалить Epic по ID");
        taskManager.deleteEpic(3);
        System.out.println(taskManager.printAllEpics());
        System.out.println(taskManager.printAllSubtask());

        //очистка сбатаска по ID
        System.out.println("удалить Subtask по ID");
        taskManager.deleteSubtask(7);
        System.out.println(taskManager.printAllSubtask());


        //очистка всего
        System.out.println("Полная очистка");
        taskManager.deleteAll();
        taskManager.printAllTasks();
        taskManager.printAllEpics();
        taskManager.printAllSubtask();

    }
}