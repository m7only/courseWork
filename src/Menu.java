import java.util.Scanner;

public class Menu {

    private EmployeeBook employeeBook;
    private Print print;
    private boolean isExit; // по false - вываливаемся из цикла на выход
    private Scanner scanner;
    private int currentMenu; // для выбора отображаемого меню, 0 - главное меню
    private int currentDepartment; // выбранный для работы отдел

    public Menu(EmployeeBook employeeBook) {
        this.employeeBook = employeeBook;
        this.print = new Print();
        this.isExit = true;
        this.scanner = new Scanner(System.in);
        this.currentMenu = 0;
        this.currentDepartment = 0;
    }

    public void start() {
        while (isExit) { // цикл формирования меню
            String command;
            if (currentMenu == 0) { // Главное меню
                print.printMainMenu();
                command = scanner.nextLine();
                print.printMessage("%n");
                switch (command) {
                    case "0": // Выход
                        isExit = false;
                        break;
                    case "1": // Вывод всех сотрудников со всеми данными, .toString
                        print.printAllEmployee(employeeBook);
                        break;
                    case "2": // Сумма всех зарплат за месяц
                        print.printMonthSalary(employeeBook);
                        break;
                    case "3": // Поиск сотрудника с минимальной зарплатой
                        print.printMinSalaryEmployee(employeeBook);
                        break;
                    case "4": // Поиск сотрудника с максимальной зарплатой
                        print.printMaxSalaryEmployee(employeeBook);
                        break;
                    case "5": // Вывод средней зарплаты всех сотрудников
                        print.printAverageSalary(employeeBook);
                        break;
                    case "6": // ФИО всех сотрудников
                        print.printAllFullName(employeeBook);
                        break;
                    case "7": // Индексация зарплаты на указываемый процент
                        print.printMessage("Введите, на сколько процентов повысить зарплату: ");
                        int percent;
                        if ((percent = getInt()) != -1) {
                            if (employeeBook.raiseSalary(percent)) {
                                print.printMessage(String.format("Зарплата увеличена на %d процентов.%n", percent));
                            } else {
                                print.printError(1);
                            }
                        } else {
                            print.printError(1);
                        }
                        break;
                    case "8": // Выбор отдела для перехода в меню отдела
                        print.printMessage("Введите номер отдела: ");
                        if ((currentDepartment = getInt()) != -1) {
                            if (currentDepartment > 0 && currentDepartment <= EmployeeBook.departmentQuantity) {
                                currentMenu = 8;
                            } else {
                                print.printError(2);
                            }
                        } else {
                            print.printError(2);
                        }
                        break;
                    case "9": // Сотрудники с ЗП меньше введеной
                        int rateLess;
                        print.printMessage("Введите искомую сумму: ");
                        if ((rateLess = getInt()) != -1) {
                            print.printLessSalary(rateLess, employeeBook);
                            print.printMessage("%n");
                        } else {
                            print.printError(0);
                        }
                        break;
                    case "10": // Сотрудники с ЗП больше введеной
                        int rateMore;
                        print.printMessage("Введите искомую сумму: ");
                        if ((rateMore = getInt()) != -1) {
                            print.printMoreSalary(rateMore, employeeBook);
                            print.printMessage("%n");
                        } else {
                            print.printError(0);
                        }
                        break;
                    case "11": // Добавить сотрудника
                        Scanner scannerAdd = new Scanner(System.in);
                        int salary;
                        print.printMessage("Прием сотрудника на работу.%nВведите ФИО: ");
                        String fullName = scannerAdd.nextLine();
                        print.printMessage("Введите зарплату: ");
                        if ((salary = getInt()) != -1) {
                            print.printMessage("Введите отдел: ");
                            int department;
                            if ((department = getInt()) != -1) {
                                if (employeeBook.addEmployee(fullName, department, salary)) {
                                    print.printMessage(String.format("Сотрудник %s принят на работу в отдел %d с зарплатой %d.%n", fullName, department, salary));
                                } else {
                                    print.printError(3);
                                }
                            } else {
                                print.printError(2);
                            }
                        } else {
                            print.printError(1);
                        }
                        break;
                    case "12": // Удалить сотрудника
                        currentMenu = 12;
                        break;
                    case "13": // Изменить зарплату сотрудника
                        print.printMessage("Список сотрудников организации:%n");
                        print.printAllEmployee(employeeBook);
                        print.printMessage("Введие ФИО сотрудника, которому нужно измеить зарплату: ");
                        Scanner scannerChangeSalary = new Scanner(System.in);
                        String salaryChangeFullName = scannerChangeSalary.nextLine();
                        print.printMessage("Введите новую зарплату: ");
                        int changedSalary;
                        if ((changedSalary = getInt()) != -1) {
                            if (employeeBook.changeSalary(salaryChangeFullName, changedSalary)) {
                                print.printMessage(String.format("Зарплата сотрудника %s изменена на %d%n", salaryChangeFullName, changedSalary));
                            } else {
                                print.printError(1);
                            }
                        } else {
                            print.printError(1);
                        }
                        break;
                    case "14": // Изменить отдел сотрдника
                        print.printMessage("Список сотрудников организации:");
                        employeeBook.listAll();
                        print.printMessage("Введие ФИО сотрудника, которому нужно измеить отдел: ");
                        Scanner scannerChangeDepartment = new Scanner(System.in);
                        String depChangeFullName = scannerChangeDepartment.nextLine();
                        print.printMessage("Введите отдел: ");
                        int changedDepartment;
                        if ((changedDepartment = getInt()) != -1) {
                            if (employeeBook.changeDepartment(depChangeFullName, changedDepartment)) {
                                print.printMessage(String.format("Отдел сотрудника %s изменен на %d.%n", depChangeFullName, changedDepartment));
                            } else {
                                print.printError(2);
                            }
                        } else {
                            print.printError(2);
                        }
                        break;
                    case "15": // Список сотрудников по отделам
                        print.printAllEmployeeByDepartment(employeeBook);
                        break;
                    default: // Некорректный ввод
                        print.printMessage("Неверный выбор, попробуйте снова.%n");
                        break;
                }
            } else if (currentMenu == 8) { // Меню для работы с выбранным отделом
                print.printDepartmentMenu(currentDepartment);
                command = scanner.nextLine();
                print.printMessage("%n");
                switch (command) {
                    case "0": // Возврат в главное меню
                        currentMenu = 0;
                        break;
                    case "1": // Вывести сотрудников отдела
                        print.printMessage(String.format("Состав отдела %d:%n", currentDepartment));
                        print.printAllEmployee(employeeBook, currentDepartment);
                        break;
                    case "2": // Поиск сотрудника с минимальной зарплатой
                        print.printMinSalaryEmployee(employeeBook, currentDepartment);
                        break;
                    case "3": // Поиск сотрудника с максимальной зарплатой
                        print.printMaxSalaryEmployee(employeeBook, currentDepartment);
                        break;
                    case "4": // Вывод суммы затрат по отделу
                        print.printMonthSalary(employeeBook, currentDepartment);
                        break;
                    case "5": // Вывод средней зарплаты
                        print.printAverageSalary(employeeBook, currentDepartment);
                        break;
                    case "6": // Индексация зарплаты в отделе
                        print.printMessage("Введите, на сколько процентов повысить зарплату: ");
                        int percent;
                        if ((percent = getInt()) != -1) {
                            if (employeeBook.raiseSalary(percent, currentDepartment)) {
                                print.printMessage(String.format("Зарплата отдела %d увеличена на %d процентов.%n", currentDepartment, percent));
                            } else {
                                print.printError(1);
                            }
                        } else {
                            print.printError(1);
                        }
                        break;
                    default: // Некорректный ввод
                        print.printError(0);
                        break;
                }
            } else if (currentMenu == 12) { // Меню удаления сотрудника
                print.printMessage("Список сотрудников организации:%n");
                print.printAllEmployee(employeeBook);
                print.printMessage("Введите exit для перехода в предыдущее меню. Введие ID или ФИО увольняемого сотрудника: ");
                Scanner scannerRemove = new Scanner(System.in);
                String input = scannerRemove.nextLine();
                if (!input.equals("exit")) {
                    if (employeeBook.removeEmployee(input)) {
                        print.printMessage(String.format("Сотрудник %s уволен.%n%n", input));
                    } else {
                        print.printError(4);
                    }
                } else currentMenu = 0;
            } else currentMenu = 0;
        }
    }

    private int getInt() { // Проверка введенного в консоли значения на число. Зацикливаем, пока юзверь не введет число.
        Scanner scanner = new Scanner(System.in);
        boolean inputCheck = true;
        while (inputCheck) {
            String input = scanner.nextLine();
            if (EmployeeBook.isInteger(input)) {
                inputCheck = false;
                return Integer.parseInt(input);
            } else {
                inputCheck = true;
                print.printMessage("Не верный ввод, попробуйте снова. Введите число: ");
            }
        }
        return -1;
    }
}
