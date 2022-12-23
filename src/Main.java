import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        EmployeeBook employeeBook = new EmployeeBook();
        // набиваем сотрудников для работы
        for (int i = 0; i <= 8; i++) {
            //if (!employeeBook.addEmployee("ФАМИЛИЯ_ЫМЯ_ОТЧЕСТВО_" + i, ThreadLocalRandom.current().nextInt(1, 6), ThreadLocalRandom.current().nextInt(50000, 150000)))
            Employee employee = new Employee("ФАМИЛИЯ_ЫМЯ_ОТЧЕСТВО_" + i, ThreadLocalRandom.current().nextInt(1, 6), 10000 + i * 1000);
            if (!employeeBook.addEmployee(employee)) {
                System.out.println("Ошибка при добавлении сотрудника.");
            }
        }
        Menu menu = new Menu(employeeBook);
        menu.start();
    }
}