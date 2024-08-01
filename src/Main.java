public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();


        Task dog;
        Epic dogWalking;
        Subtask dogDressCode;
        Subtask dogPlayGame;

        Task bicikle;
        Epic bicikleChekPoint;
        Subtask bicikleWheel;

        dog = new Task("Dog", "walking the dog", taskManager.getTaskID(), Status.NEW, Type.TASK);
        bicikle = new Task("Bicycle", "to ride bike", taskManager.getTaskID(), Status.NEW, Type.TASK);
        dogWalking = new Epic("dogWalking", "description", taskManager.getEpicID(), Status.NEW, Type.EPIC);
        bicikleChekPoint = new Epic("bicikleChekPoint", "description", taskManager.getEpicID(), Status.NEW, Type.EPIC);
        dogDressCode = new Subtask("dogDressCode", "description", +
                taskManager.getSubtaskID(), Status.NEW, Type.SUBTASK, dogWalking.getId());
        dogPlayGame = new Subtask("dogPlayGame", "description", +
                taskManager.getSubtaskID(), Status.NEW, Type.SUBTASK, dogWalking.getId());
        bicikleWheel = new Subtask("bicikleWheel", "description", +
                taskManager.getSubtaskID(), Status.NEW, Type.SUBTASK, bicikleChekPoint.getId());



        // добавление задач
        taskManager.addNewTask(dog);
        taskManager.addNewTask(bicikle);
        taskManager.addNewEpic(dogWalking);
        taskManager.addNewEpic(bicikleChekPoint);
        taskManager.addNewSubtask(dogDressCode);
        taskManager.addNewSubtask(dogPlayGame);
        taskManager.addNewSubtask(bicikleWheel);
        // печать всех задач
        System.out.println();
        System.out.println("=TASK PROGRAM=");
        taskManager.printAllTasks();
        taskManager.printAllEpics();
        taskManager.printAllSubtask();
        //смена статуса task
        taskManager.changeStatusTask(dog, Status.IN_PROGRESS);
        taskManager.printAllTasks();
        //изменение task
        Task updateTask = new Task("DogLove", "walking the dog", 1, Status.NEW, Type.TASK);
        taskManager.updateTask(updateTask);
        //получить по ID
        System.out.println(taskManager.getTaskId(1));
        // удалить по ID
        taskManager.deleteTask(2);
        taskManager.printAllTasks();
        // обновить статус subtask
        System.out.println(taskManager.getSubtaskId(5));
        taskManager.changeStatusSubtask(dogDressCode, Status.IN_PROGRESS);
        System.out.println(taskManager.getSubtaskId(5));
        System.out.println(taskManager.getEpicId(3));





        //очистка всего
        taskManager.deleteAll();
        taskManager.printAllTasks();
        taskManager.printAllEpics();
        taskManager.printAllSubtask();



    }
}