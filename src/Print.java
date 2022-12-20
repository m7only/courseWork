import java.util.LinkedList;

public class Print {
    public void printMainMenu() { // Вывод главного меню
        System.out.println();
        System.out.println("Введите пункт меню:");
        System.out.println("1. Получить список всех сотрудников со всеми данными.");
        System.out.println("2. Посчитать сумму затрат на зарплаты в месяц.");
        System.out.println("3. Найти сотрудника с минимальной зарплатой.");
        System.out.println("4. Найти сотрудника с максимальной зарплатой.");
        System.out.println("5. Подсчитать среднее значение зарплат.");
        System.out.println("6. Получить Ф. И. О. всех сотрудников (вывести в консоль).");
        System.out.println("7. Индексация зарплаты всех сотрудников.");
        System.out.println("8. Выбор отдела.");
        System.out.println("9. Найти сотрудников с зарплатой меньше введеной.");
        System.out.println("10. Найти сотрудников с зарплатой больше введеной.");
        System.out.println("11. Принять сотрудника на работу.");
        System.out.println("12. Уволить сотрудника.");
        System.out.println("13. Изменить зарплату сотруднику.");
        System.out.println("14. Перевести сотрудника в другой отдел.");
        System.out.println("15. Список сотрудников по отделам.");
        System.out.println("0. Выход.");
        System.out.printf("Введите пункт меню: ");
    }

    public void printDepartmentMenu(int currentDepartment) { // Вывод меню отдела
        System.out.println();
        System.out.printf("Отдел %d. Введите пункт меню:\n", currentDepartment);
        System.out.println("1. Получить список сотрудников отдела.");
        System.out.println("2. Найти сотрудника с минимальной зарплатой.");
        System.out.println("3. Найти сотрудника с максимальной зарплатой.");
        System.out.println("4. Посчитать сумму затрат на зарплаты по отделу в месяц.");
        System.out.println("5. Подсчитать среднее значение зарплат.");
        System.out.println("6. Индексация зарплаты сотрудников отдела.");
        System.out.println("0. Вернуться в предыдущее меню.");
        System.out.printf("Введите пункт меню: ");
    }

    public void printError(int errorCode) { // Печать текста ошибки
        switch (errorCode) {
            case 1:
                printMessage("Ошибка при вводе зарплаты, попробуйте снова.%n");
                break;
            case 2:
                printMessage("Ошибка при выборе отдела. Возможно, такого отдела не существует. Попробуйте снова.%n");
                break;
            case 3:
                printMessage("Ошибка при приеме на работу. Возможно, штатное расписание заполнено или выбран несуществующий отдел.%n");
                break;
            case 4:
                printMessage("Ошибка при увольнении сотрудника.%n");
                break;
            default:
                printMessage("Ошибка, попробуйте снова.%n");
                break;
        }
    }

    public void printMessage(String message) { // Вывод в консоль - отделить модель от представления
        System.out.printf(message);
    }

    public void printAllEmployee(EmployeeBook employeeBook) { // Вывод в консоль всех данных по сотрудникам
        for (Employee emp : employeeBook.listAll()) {
            if (emp != null) {
                System.out.println(emp);
            }
        }
    }

    public void printAllEmployee(EmployeeBook employeeBook, int currentDepartment) { // Вывод всех данных по сотрудникам в указанном отделе
        LinkedList<Employee> list = employeeBook.listAll(currentDepartment);
        if (!list.isEmpty()) {
            for (Employee emp : employeeBook.listAll(currentDepartment)) {
                if (emp != null) {
                    System.out.printf("id=%d, %s, Зарплата=%d%n", emp.getId(), emp.getFullName(), emp.getSalary());
                }
            }
        } else {
            System.out.println("  В отделе нет сотрудников.");
        }
    }

    public void printAllEmployeeByDepartment(EmployeeBook employeeBook) { // Вывод сотрудников с распределением по отделам
        for (int currentDepartment = 1; currentDepartment <= EmployeeBook.DEPARTMENT_QUANTITY; currentDepartment++) {
            System.out.println("Состав сотрудников отдела " + currentDepartment + ":");
            printAllEmployee(employeeBook, currentDepartment);
        }
    }

    public void printMonthSalary(EmployeeBook employeeBook) { // Вывод суммы всех зарплат
        System.out.printf("Бюджет зарплат за месяц: %d%n", employeeBook.getMonthSalary());
    }

    public void printMonthSalary(EmployeeBook employeeBook, int currentDepartment) { // Вывод суммы всех зарплат по отделу
        System.out.printf("Бюджет зарплат в отеделе %d за месяц: %d%n", currentDepartment, employeeBook.getMonthSalary(currentDepartment));
    }

    public void printMinSalaryEmployee(EmployeeBook employeeBook) { // Вывод данных сотрудника с минимальной зарплатой
        System.out.printf("Данные сотрудника с минимальной зарплатой: %s%n", employeeBook.getMinSalaryEmployee());
    }

    public void printMaxSalaryEmployee(EmployeeBook employeeBook) { // Вывод данных сотрудника с максимальной зарплатой
        System.out.printf("Данные сотрудника с максимальной зарплатой: %s%n", employeeBook.getMaxSalaryEmployee());
    }

    public void printMinSalaryEmployee(EmployeeBook employeeBook, int currentDepartment) { // Вывод данных сотрудника с минимальной зарплатой в отделе
        System.out.printf("Данные сотрудника с минимальной зарплатой в отделе %d: %s%n", currentDepartment, employeeBook.getMinSalaryEmployee(currentDepartment));
    }

    public void printMaxSalaryEmployee(EmployeeBook employeeBook, int currentDepartment) { // Вывод данных сотрудника с максимальной зарплатой в отделе
        System.out.printf("Данные сотрудника с максимальной зарплатой в отделе %d: %s%n", currentDepartment, employeeBook.getMaxSalaryEmployee(currentDepartment));
    }

    public void printAverageSalary(EmployeeBook employeeBook) { // Вывод средней зарплаты
        System.out.printf("Средняя зарплата: %.2f\n", employeeBook.getAverageSalary());
    }

    public void printAverageSalary(EmployeeBook employeeBook, int currentDepartment) { // Вывод средней зарплаты в отделе
        System.out.printf("Средняя зарплата в отделе %d: %.2f%n", currentDepartment, employeeBook.getAverageSalary(currentDepartment));
    }

    public void printAllFullName(EmployeeBook employeeBook) { // Вывод ФЫО всех сотрудников
        System.out.println("Список всех сотрудников: ");
        LinkedList<String> allFullName = employeeBook.getAllFullName();
        if (!allFullName.isEmpty()) {
            allFullName.forEach(System.out::println);
        } else {
            System.out.println("На предприятии отсутствуют сотрудники.");
        }
    }

    public void printLessSalary(int rateLess, EmployeeBook employeeBook) { // Вывод данных сотрудников с зарплатой больше указанной
        System.out.printf("Сотрудники, зарплата которых меньше %d:%n", rateLess);
        LinkedList<Employee> listLessSalary = employeeBook.listLessSalary(rateLess);
        if (!listLessSalary.isEmpty()) {
            listLessSalary.forEach((employee -> System.out.printf("id=%d, %s, зарплата: %d%n", employee.getId(), employee.getFullName(), employee.getSalary())));
        } else {
            System.out.printf("Отсутствуют сотрудники, зарплата которых меньше %d.%n", rateLess);
        }
    }

    public void printMoreSalary(int rateMore, EmployeeBook employeeBook) { // Вывод данных сотрудников с зарплатой больше указанной
        System.out.printf("Сотрудники, зарплата которых больше %d:%n", rateMore);
        LinkedList<Employee> listMoreSalary = employeeBook.listMoreSalary(rateMore);
        if (!listMoreSalary.isEmpty()) {
            listMoreSalary.forEach((employee -> System.out.printf("id=%d, %s, зарплата: %d%n", employee.getId(), employee.getFullName(), employee.getSalary())));
        } else {
            System.out.printf("Отсутствуют сотрудники, зарплата которых больше %d.%n", rateMore);
        }
    }
}
