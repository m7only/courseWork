public class EmployeeBook {
    private Employee[] employee = new Employee[10];
    public static final int departmentQuantity = 5; // количество отделов согласно условий задачи

    public boolean addEmployee(String fullName, int department, int salary) { // Добавляем сотрудника. Если добавился - ок, если нет места в массиве - ошибка
        for (int i = 0; i < employee.length; i++) {
            if (employee[i] == null && department > 0 && department <= departmentQuantity) { // проверка на пустую ячейку в масиве и правильно введенный отдел
                employee[i] = new Employee(fullName, department, salary);
                return true;
            }
        }
        return false;
    }

    public boolean removeEmployee(String input) { // Увольняем сотрудника по ФЫО или id, если удален - ок, если нет - не ок.
        if (isInteger(input)) { // проверяем, что ввели строку: ФЫО или номер отдела
            for (int i = 0; i < employee.length; i++) {
                if (employee[i] != null && employee[i].getId() == Integer.parseInt(input)) {
                    employee[i] = null;
                    return true;
                }
            }
        } else {
            for (int i = 0; i < employee.length; i++) {
                if (employee[i] != null && employee[i].getFullName().equals(input)) {
                    employee[i] = null;
                    return true;
                }
            }
        }
        return false;
    }

    public void listAll() { // Выводим все данные по сотрудникам в консоль .toString
        for (Employee emp : employee) {
            if (emp != null) {
                System.out.println(emp);
            }
        }
    }

    public void listAll(int currentDepartment) { // Выводим данные сотрудников выбранного отдела
        for (Employee emp : employee) {
            if (emp != null) {
                if (emp.getDepartment() == currentDepartment) {
                    System.out.printf("id=%d, %s, зарплата %d\n", emp.getId(), emp.getFullName(), emp.getSalary());
                }
            }
        }
    }

    public void listAllByDepartment() { // Вывод сотрудников по отделам
        int counter = 0;
        for (int currentDepartment = 1; currentDepartment <= departmentQuantity; currentDepartment++) {
            System.out.println("Состав сотрудников отдела " + currentDepartment + ":");
            for (Employee emp : employee) {
                if (emp != null && emp.getDepartment() == currentDepartment) {
                    System.out.println("    " + emp.getFullName());
                    counter++;
                }
            }
            if (counter == 0) {
                System.out.println("  В отделе нет сотрудников.");
            }
            counter = 0;
        }
    }

    public int monthSalary() { // Считаем месячный бюджет на зарплаты
        int totalMonthSalary = 0;
        for (Employee emp : employee) {
            if (emp != null) {
                totalMonthSalary += emp.getSalary();
            }
        }
        return totalMonthSalary;
    }

    public int monthSalary(int currentDepartment) { // Считаем месячный бюджет отдела на зарплаты
        int totalMonthSalary = 0;
        for (Employee emp : employee) {
            if (emp != null && emp.getDepartment() == currentDepartment) {
                totalMonthSalary += emp.getSalary();
            }
        }
        return totalMonthSalary;
    }

    public Employee minSalaryEmployee() { // Ищем неудачника с минимальной зарплатой
        Employee minSalaryEmployee = new Employee();
        for (int department = 1; department <= departmentQuantity; department++) {
            Employee minSalaryByDepartment = minSalaryEmployee(department);
            if (minSalaryEmployee.getFullName() == null || minSalaryEmployee.getSalary() > minSalaryByDepartment.getSalary()) {
                minSalaryEmployee = minSalaryByDepartment;
            }
        }
        return minSalaryEmployee;
    }

    public Employee minSalaryEmployee(int currentDepartment) { // Ищем неудачника в отделе с минимальной зарплатой
        Employee minSalaryEmployee = new Employee();
        for (Employee emp : employee) {
            if (emp != null && emp.getDepartment() == currentDepartment) {
                if (minSalaryEmployee.getFullName() == null) { // для присвоения начальной зарплаты
                    minSalaryEmployee = emp;
                }
                if (minSalaryEmployee.getSalary() > emp.getSalary()) {
                    minSalaryEmployee = emp;
                }
            }
        }
        return minSalaryEmployee;
    }

    public Employee maxSalaryEmployee() { // Ищем выскочку с максимальной зарплатой
        Employee maxSalaryEmployee = new Employee();
        for (int department = 1; department <= departmentQuantity; department++) {
            Employee maxSalaryByDepartment = maxSalaryEmployee(department);
            if (maxSalaryEmployee.getFullName() == null || maxSalaryEmployee.getSalary() < maxSalaryByDepartment.getSalary()) {
                maxSalaryEmployee = maxSalaryByDepartment;
            }
        }
        return maxSalaryEmployee;
    }

    public Employee maxSalaryEmployee(int currentDepartment) { // Ищем выскочку в отделе с максимальной зарплатой
        Employee maxSalaryEmployee = new Employee();
        for (Employee emp : employee) {
            if (emp != null && emp.getDepartment() == currentDepartment) {
                if (maxSalaryEmployee.getFullName() == null) { // Для присвоения начальной зарплаты, с которой будем сравнивать
                    maxSalaryEmployee = emp;
                }
                if (maxSalaryEmployee.getSalary() < emp.getSalary())
                    maxSalaryEmployee = emp;
            }
        }
        return maxSalaryEmployee;
    }

    public double averageSalary() { // Определяем среднюю зарплату по больнице
        int totalSalary = monthSalary();
        int employeeCounter = 0;
        for (Employee emp : employee) {
            if (emp != null) {
                employeeCounter++;
            }
        }
        return totalSalary / employeeCounter;
    }

    public double averageSalary(int currentDepartment) { // Определяем среднюю зарплату по отделу
        int totalSalary = monthSalary(currentDepartment);
        int employeeCounter = 0;
        for (Employee emp : employee) {
            if (emp != null && emp.getDepartment() == currentDepartment) {
                employeeCounter++;
            }
        }
        return totalSalary / employeeCounter;
    }

    public void listAllFullName() { // Выводим ФЫО по всем сотрудника в консоль
        for (Employee emp : employee) {
            if (emp != null) {
                System.out.println(emp.getFullName());
            }
        }
    }

    public boolean raiseSalary(int percent) { // Индексируем зарплату на введеный процент
        double k = (double) percent / 100 + 1;
        for (Employee value : employee) {
            if (value != null) {
                value.setSalary((int) (value.getSalary() * k));
            }
        }
        return true;
    }

    public boolean raiseSalary(int percent, int currentDepartment) { // Индексируем зарплату отделу на введеный процент
        double k = (double) percent / 100 + 1;
        for (Employee value : employee) {
            if (value != null && value.getDepartment() == currentDepartment) {
                value.setSalary((int) (value.getSalary() * k));
            }
        }
        return true;
    }

    public void listLessSalary(int lessSalary) { // Выводим данные сотрудников, зарплата которых меньше указанной
        System.out.printf("Сотрудники, зарплата которых меньше %d:\n", lessSalary);
        for (Employee emp : employee) {
            if (emp != null && emp.getSalary() < lessSalary) {
                System.out.printf("id=%d,%s, зарплата: %d\n", emp.getId(), emp.getFullName(), emp.getSalary());
            }
        }
    }

    public void listMoreSalary(int moreSalary) { // Выводим данные сотрудников, зарплата которых больше указанной
        System.out.printf("Сотрудники, зарплата которых больше %d:\n", moreSalary);
        for (Employee emp : employee) {
            if (emp != null && emp.getSalary() >= moreSalary) {
                System.out.printf("id=%d,%s, зарплата: %d\n", emp.getId(), emp.getFullName(), emp.getSalary());
            }
        }
    }

    public boolean changeSalary(String fullName, int newSalary) { // Изменить ЗП указанному ФЫО
        for (Employee value : employee) {
            if (value != null && value.getFullName().equals(fullName)) {
                value.setSalary(newSalary);
                return true;
            }
        }
        return false;
    }

    public boolean changeDepartment(String fullName, int newDepartment) { // Изменить отдел указанному ФЫО
        for (Employee value : employee) {
            if (value != null && value.getFullName().equals(fullName)) {
                value.setDepartment(newDepartment);
                return true;
            }
        }
        return false;
    }

    public static boolean isInteger(String string) { // Проверка введеной строки на целочисленное значение
        try {
            Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }

}
