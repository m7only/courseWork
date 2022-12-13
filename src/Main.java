import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        EmployeeBook employeeBook = new EmployeeBook();
        // набиваем сотрудников для работы
        for (int i = 0; i <= 10; i++) {
            //if (!employeeBook.addEmployee("ФАМИЛИЯ_ЫМЯ_ОТЧЕСТВО_" + i, ThreadLocalRandom.current().nextInt(1, 6), ThreadLocalRandom.current().nextInt(50000, 150000)))
            if (!employeeBook.addEmployee("ФАМИЛИЯ_ЫМЯ_ОТЧЕСТВО_" + i, ThreadLocalRandom.current().nextInt(1, 6), 10000 + i * 1000)) {
                System.out.println("Ошибка при добавлении сотрудника.");
            }
        }

        boolean isExit = true;
        Scanner scanner = new Scanner(System.in);
        int menu = 0;
        int currentDepartment = 0;

        while (isExit) {
            String command;
            if (menu == 0) {
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
                command = scanner.nextLine();
                System.out.println();
                switch (command) {
                    case "0":
                        isExit = false;
                        break;
                    case "1":
                        employeeBook.listAll();
                        break;
                    case "2":
                        System.out.printf("Бюджет зарплат за месяц: %d\n", employeeBook.monthSalary());
                        break;
                    case "3":
                        System.out.printf("Данные сотрудника с минимальной зарплатой: %s\n", employeeBook.minSalaryEmployee());
                        break;
                    case "4":
                        System.out.printf("Данные сотрудника с максимальной зарплатой: %s\n", employeeBook.maxSalaryEmployee());
                        break;
                    case "5":
                        System.out.printf("Средняя зарплата: %.2f\n", employeeBook.averageSalary());
                        break;
                    case "6":
                        System.out.println("Список всех сотрудников: ");
                        employeeBook.listAllFullName();
                        break;
                    case "7":
                        System.out.printf("Введите, на сколько процентов повысить зарплату: ");
                        Scanner scannerPercent = new Scanner(System.in);
                        int percent = scannerPercent.nextInt();
                        if (employeeBook.raiseSalary(percent)) {
                            System.out.printf("Зарплата увеличена на %d процентов.\n", percent);
                        }
                        break;
                    case "8":
                        Scanner depScanner = new Scanner(System.in);
                        System.out.printf("Введите номер отдела: ");
                        currentDepartment = depScanner.nextInt();
                        menu = 8;
                        break;
                    case "9":
                        Scanner scannerLessSalary = new Scanner(System.in);
                        System.out.printf("Введите искомую сумму: ");
                        employeeBook.listLessSalary(scannerLessSalary.nextInt());
                        System.out.printf("\n");
                        break;
                    case "10":
                        Scanner scannerMoreSalary = new Scanner(System.in);
                        System.out.printf("Введите искомую сумму: ");
                        employeeBook.listMoreSalary(scannerMoreSalary.nextInt());
                        System.out.printf("\n");
                        break;
                    case "11":
                        Scanner scannerAdd = new Scanner(System.in);
                        System.out.println("Прием сотрудника на работу.");
                        System.out.printf("Введите ФИО: ");
                        String fullName = scannerAdd.nextLine();
                        System.out.printf("Введите зарплату: ");
                        int salary = scannerAdd.nextInt();
                        System.out.printf("Введите отдел: ");
                        int department = scannerAdd.nextInt();
                        if (employeeBook.addEmployee(fullName, department, salary)) {
                            System.out.printf("Сотрудник %s принят на работу в отдел %d с зарплатой %d.\n", fullName, department, salary);
                        } else {
                            System.out.println("Ошибка при приеме на работу. Возможно, штатное расписание заполнено.");
                        }
                        break;
                    case "12":
                        menu = 12;
                        break;
                    case "13":
                        System.out.println("Список сотрудников организации:");
                        employeeBook.listAll();
                        System.out.printf("Введие ФИО сотрудника, которому нужно измеить зарплату: ");
                        Scanner scannerChangeSalary = new Scanner(System.in);
                        String salaryChangeFullName = scannerChangeSalary.nextLine();
                        System.out.printf("Новую зарплату: ");
                        int changedSalary = scannerChangeSalary.nextInt();
                        if (employeeBook.changeSalary(salaryChangeFullName, changedSalary)) {
                            System.out.printf("Зарплата сотрудника %s изменена на %d", salaryChangeFullName, changedSalary);
                        } else {
                            System.out.println("Ошибка при изменении зарплаты");
                        }
                        break;
                    case "14":
                        System.out.println("Список сотрудников организации:");
                        employeeBook.listAll();
                        System.out.printf("Введие ФИО сотрудника, которому нужно измеить отдел: ");
                        Scanner scannerChangeDepartment = new Scanner(System.in);
                        String depChangeFullName = scannerChangeDepartment.nextLine();
                        System.out.printf("Введите отдел: ");
                        int changedDepartment = scannerChangeDepartment.nextInt();
                        if (employeeBook.changeDepartment(depChangeFullName, changedDepartment)) {
                            System.out.printf("Отдел сотрудника %s изменен на %d.\n", depChangeFullName, changedDepartment);
                        } else {
                            System.out.println("Ошибка при изменении отдела.\n");
                        }
                        break;
                    case "15":
                        employeeBook.listAllByDepartment();
                        break;
                    default:
                        System.out.println("Неверный выбор, попробуйте снова.\n");
                        break;
                }
            } else if (menu == 8) {
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
                command = scanner.nextLine();
                System.out.println();
                switch (command) {
                    case "0":
                        menu = 0;
                        break;
                    case "1":
                        employeeBook.listAll(currentDepartment);
                        break;
                    case "2":
                        System.out.printf("Данные сотрудника с минимальной зарплатой: %s\n", employeeBook.minSalaryEmployee(currentDepartment));
                        break;
                    case "3":
                        System.out.printf("Данные сотрудника с максимальной зарплатой: %s\n", employeeBook.maxSalaryEmployee(currentDepartment));
                        break;
                    case "4":
                        System.out.printf("Бюджет зарплат отдела %d за месяц: %d\n", currentDepartment, employeeBook.monthSalary(currentDepartment));
                        break;
                    case "5":
                        System.out.printf("Средняя зарплата: %.2f\n", employeeBook.averageSalary(currentDepartment));
                        break;
                    case "6":
                        System.out.printf("Введите, на сколько процентов повысить зарплату: ");
                        Scanner scannerPercent = new Scanner(System.in);
                        int percent = scannerPercent.nextInt();
                        if (employeeBook.raiseSalary(percent, currentDepartment)) {
                            System.out.printf("Зарплата отдела %d увеличена на %d процентов.\n", currentDepartment, percent);
                        }
                        break;
                    default:
                        System.out.println("Неверный выбор, попробуйте снова.");
                        break;
                }
            } else if (menu == 12) {
                System.out.println("Список сотрудников организации:");
                employeeBook.listAll();
                System.out.printf("Введите exit для перехода в предыдущее меню. Введие ID или ФИО увольняемого сотрудника: ");
                Scanner scannerRemove = new Scanner(System.in);
                String input = scannerRemove.nextLine();
                if (!input.equals("exit")) {
                    if (employeeBook.removeEmployee(input)) {
                        System.out.printf("Сотрудник %s уволен.\n\n", input);
                    } else {
                        System.out.printf("Ошибка при увольнении сотрудника %s.\n\n", input);
                    }
                } else menu = 0;
            } else menu = 0;
        }
    }
}