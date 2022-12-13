import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        EmployeeBook employeeBook = new EmployeeBook();
        // набиваем сотрудников для работы
        for (int i = 0; i <= 8; i++) {
            //if (!employeeBook.addEmployee("ФАМИЛИЯ_ЫМЯ_ОТЧЕСТВО_" + i, ThreadLocalRandom.current().nextInt(1, 6), ThreadLocalRandom.current().nextInt(50000, 150000)))
            if (!employeeBook.addEmployee("ФАМИЛИЯ_ЫМЯ_ОТЧЕСТВО_" + i, ThreadLocalRandom.current().nextInt(1, 6), 10000 + i * 1000)) {
                System.out.println("Ошибка при добавлении сотрудника.");
            }
        }

        boolean isExit = true; // по false - вываливаемся из цикла на выход
        Scanner scanner = new Scanner(System.in);
        int currentMenu = 0; // для выбора отображаемого меню, 0 - главное меню
        int currentDepartment = 0; // выбранный для работы отдел

        while (isExit) { // цикл формирования меню
            String command;
            if (currentMenu == 0) { // Главное меню
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
                    case "0": // Выход
                        isExit = false;
                        break;
                    case "1": // Вывод всех сотрудников со всеми данными, .toString
                        employeeBook.listAll();
                        break;
                    case "2": // Сумма всех зарплат за месяц
                        System.out.printf("Бюджет зарплат за месяц: %d\n", employeeBook.monthSalary());
                        break;
                    case "3": // Поиск сотрудника с минимальной зарплатой
                        System.out.printf("Данные сотрудника с минимальной зарплатой: %s\n", employeeBook.minSalaryEmployee());
                        break;
                    case "4": // Поиск сотрудника с максимальной зарплатой
                        System.out.printf("Данные сотрудника с максимальной зарплатой: %s\n", employeeBook.maxSalaryEmployee());
                        break;
                    case "5": // Вывод средней зарплаты всех сотрудников
                        System.out.printf("Средняя зарплата: %.2f\n", employeeBook.averageSalary());
                        break;
                    case "6": // ФИО всех сотрудников
                        System.out.println("Список всех сотрудников: ");
                        employeeBook.listAllFullName();
                        break;
                    case "7": // Индексация зарплаты на указываемый процент
                        System.out.printf("Введите, на сколько процентов повысить зарплату: ");
                        int percent;
                        if ((percent = getInt()) != -1) {
                            if (employeeBook.raiseSalary(percent)) {
                                System.out.printf("Зарплата увеличена на %d процентов.\n", percent);
                            } else {
                                System.out.println("Ошибка при изменении зарплаты, попробуйте снова.");
                            }
                        } else {
                            System.out.println("Ошибка при изменении зарплаты, попробуйте снова.");
                        }
                        break;
                    case "8": // Выбор отдела для перехода в меню отдела
                        System.out.printf("Введите номер отдела: ");
                        if ((currentDepartment = getInt()) != -1) {
                            if (currentDepartment > 0 && currentDepartment <= EmployeeBook.departmentQuantity) {
                                currentMenu = 8;
                            } else {
                                System.out.println("Такого отдела не существует. Попробуйте снова.");
                            }
                        } else {
                            System.out.println("Ошибка при выборе отдела. Попробуйте снова.");
                        }

                        break;
                    case "9": // Сотрудники с ЗП меньше введеной
                        int rateLess;
                        System.out.printf("Введите искомую сумму: ");
                        if ((rateLess = getInt()) != -1) {
                            employeeBook.listLessSalary(rateLess);
                            System.out.printf("\n");
                        } else {
                            System.out.println("Ошибка при вводе суммы. Попробуйте снова.");
                        }
                        break;
                    case "10": // Сотрудники с ЗП больше введеной
                        int rateMore;
                        System.out.printf("Введите искомую сумму: ");
                        if ((rateMore = getInt()) != -1) {
                            employeeBook.listMoreSalary(rateMore);
                            System.out.printf("\n");
                        } else {
                            System.out.println("Ошибка при вводе суммы. Попробуйте снова.");
                        }
                        break;
                    case "11": // Добавить сотрудника
                        Scanner scannerAdd = new Scanner(System.in);
                        int salary;
                        System.out.println("Прием сотрудника на работу.");
                        System.out.printf("Введите ФИО: ");
                        String fullName = scannerAdd.nextLine();
                        System.out.printf("Введите зарплату: ");
                        if ((salary = getInt()) != -1) {
                            System.out.printf("Введите отдел: ");
                            int department;
                            if ((department = getInt()) != -1 && department > 0 && department <= EmployeeBook.departmentQuantity) {
                                if (employeeBook.addEmployee(fullName, department, salary)) {
                                    System.out.printf("Сотрудник %s принят на работу в отдел %d с зарплатой %d.\n", fullName, department, salary);
                                } else {
                                    System.out.println("Ошибка при приеме на работу. Возможно, штатное расписание заполнено.");
                                }
                            } else {
                                System.out.println("Ошибка при вводе отдела. Такого отдела не существует.");
                            }
                        } else {
                            System.out.println("Ошибка при вводе зарплаты. Попробуйте снова.");
                        }
                        break;
                    case "12": // Удалить сотрудника
                        currentMenu = 12;
                        break;
                    case "13": // Изменить зарплату сотрудника
                        System.out.println("Список сотрудников организации:");
                        employeeBook.listAll();
                        System.out.printf("Введие ФИО сотрудника, которому нужно измеить зарплату: ");
                        Scanner scannerChangeSalary = new Scanner(System.in);
                        String salaryChangeFullName = scannerChangeSalary.nextLine();
                        System.out.printf("Введите новую зарплату: ");
                        int changedSalary;
                        if ((changedSalary = getInt()) != -1) {
                            if (employeeBook.changeSalary(salaryChangeFullName, changedSalary)) {
                                System.out.printf("Зарплата сотрудника %s изменена на %d\n", salaryChangeFullName, changedSalary);
                            } else {
                                System.out.println("Ошибка при изменении зарплаты.");
                            }
                        } else {
                            System.out.println("Ошибка при вводе зарплаты. Попробуйте снова.");
                        }
                        break;
                    case "14": // Изменить отдел сотрдника
                        System.out.println("Список сотрудников организации:");
                        employeeBook.listAll();
                        System.out.printf("Введие ФИО сотрудника, которому нужно измеить отдел: ");
                        Scanner scannerChangeDepartment = new Scanner(System.in);
                        String depChangeFullName = scannerChangeDepartment.nextLine();
                        System.out.printf("Введите отдел: ");
                        int changedDepartment;
                        if ((changedDepartment = getInt()) != -1 && changedDepartment > 0 && changedDepartment <= EmployeeBook.departmentQuantity) {
                            if (employeeBook.changeDepartment(depChangeFullName, changedDepartment)) {
                                System.out.printf("Отдел сотрудника %s изменен на %d.\n", depChangeFullName, changedDepartment);
                            } else {
                                System.out.println("Ошибка при изменении отдела. Возможно, такого отдела не существует или введено не верное ФИО.\n");
                            }
                        } else {
                            System.out.println("Ошибка при вводе отдела. Попробуйте снова.");
                        }
                        break;
                    case "15": // Список сотрудников по отделам
                        employeeBook.listAllByDepartment();
                        break;
                    default: // Некорректный ввод
                        System.out.println("Неверный выбор, попробуйте снова.\n");
                        break;
                }
            } else if (currentMenu == 8) { // Меню для работы с выбранным отделом
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
                    case "0": // Возврат в главное меню
                        currentMenu = 0;
                        break;
                    case "1": // Вывести сотрудников отдела
                        employeeBook.listAll(currentDepartment);
                        break;
                    case "2": // Поиск сотрудника с минимальной зарплатой
                        System.out.printf("Данные сотрудника с минимальной зарплатой: %s\n", employeeBook.minSalaryEmployee(currentDepartment));
                        break;
                    case "3": // Поиск сотрудника с максимальной зарплатой
                        System.out.printf("Данные сотрудника с максимальной зарплатой: %s\n", employeeBook.maxSalaryEmployee(currentDepartment));
                        break;
                    case "4": // Вывод суммы затрат по отделу
                        System.out.printf("Бюджет зарплат отдела %d за месяц: %d\n", currentDepartment, employeeBook.monthSalary(currentDepartment));
                        break;
                    case "5": // Вывод средней зарплаты
                        System.out.printf("Средняя зарплата: %.2f\n", employeeBook.averageSalary(currentDepartment));
                        break;
                    case "6": // Индексация зарплаты в отделе
                        System.out.printf("Введите, на сколько процентов повысить зарплату: ");
                        int percent;
                        if ((percent = getInt()) != -1) {
                            if (employeeBook.raiseSalary(percent, currentDepartment)) {
                                System.out.printf("Зарплата отдела %d увеличена на %d процентов.\n", currentDepartment, percent);
                            } else {
                                System.out.println("Ошибка при изменении зарплаты. Попробуйте снова.");
                            }
                        } else {
                            System.out.println("Ошибка при вводе процента. Попробуйте снова.");
                        }
                        break;
                    default: // Некорректный ввод
                        System.out.println("Неверный выбор, попробуйте снова.");
                        break;
                }
            } else if (currentMenu == 12) { // Меню удаления сотрудника
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
                } else currentMenu = 0;
            } else currentMenu = 0;
        }
    }

    public static int getInt() { // Проверка введенного в консоли значения на число. Зацикливаем, пока юзверь не введет число.
        Scanner scanner = new Scanner(System.in);
        boolean inputCheck = true;
        while (inputCheck) {
            String input = scanner.nextLine();
            if (EmployeeBook.isInteger(input)) {
                inputCheck = false;
                return Integer.parseInt(input);
            } else {
                inputCheck = true;
                System.out.printf("Не верный ввод, попробуйте снова. Введите число: ");
            }
        }
        return -1;
    }
}