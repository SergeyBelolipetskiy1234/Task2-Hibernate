package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        User userOne = new User("Petr", "Benedictovich", (byte) 33);
        User userTwo = new User("Angela", "Davidson", (byte) 22);
        User userThree = new User("Neo", "Matrixovich", (byte) 40);
        User userFour = new User("Puh", "Vinny", (byte) 10);

        userService.createUsersTable();

        userService.saveUser(userOne.getName(), userOne.getLastName(), userOne.getAge());
        System.out.println("User с именем – " + userOne.getName() + " добавлен в базу данных");
        userService.saveUser(userTwo.getName(), userTwo.getLastName(), userTwo.getAge());
        System.out.println("User с именем – " + userTwo.getName() + " добавлен в базу данных");
        userService.saveUser(userThree.getName(), userThree.getLastName(), userThree.getAge());
        System.out.println("User с именем – " + userThree.getName() + " добавлен в базу данных");
        userService.saveUser(userFour.getName(), userFour.getLastName(), userFour.getAge());
        System.out.println("User с именем – " + userFour.getName() + " добавлен в базу данных");
        userService.removeUserById(73);

        List<User> user = userService.getAllUsers();
        user.forEach(System.out::println);

        userService.cleanUsersTable();

        userService.dropUsersTable();

    }
}
