import java.util.LinkedList;

public class EmployeeBook {
    private Employee[] employee = new Employee[10];
    public static final int DEPARTMENT_QUANTITY = 5; // количество отделов согласно условий задачи

    public boolean addEmployee(String fullName, int department, int salary) { // Добавляем сотрудника. Если добавился - ок, если нет места в массиве - ошибка
        for (int i = 0; i < employee.length; i++) {
            if (employee[i] == null && isDepartmentValid(department)) { // проверка на пустую ячейку в масиве и правильно введенный отдел
                employee[i] = new Employee(fullName, department, salary);
                return true;
            }
        }
        return false;
    }

    public boolean removeEmployee(String input) { // Увольняем сотрудника по ФЫО или id, если удален - ок, если нет - не ок.
        if (isInteger(input)) { // проверяем, что ввели в строку: ФЫО или номер отдела
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

    public Employee[] listAll() { // Вернуть все данные по сотрудникам для вывода в консоль .toString
        return this.employee;
    }

    public LinkedList<Employee> listAll(int currentDepartment) { // Возвращаем данные сотрудников выбранного отдела
        LinkedList<Employee> list = new LinkedList<>();
        for (Employee emp : employee) {
            if (emp != null) {
                if (emp.getDepartment() == currentDepartment) {
                    list.add(emp);
                }
            }
        }
        return list;
    }

    public int getMonthSalary() { // Считаем месячный бюджет на зарплаты
        int totalMonthSalary = 0;
        for (Employee emp : employee) {
            if (emp != null) {
                totalMonthSalary += emp.getSalary();
            }
        }
        return totalMonthSalary;
    }

    public int getMonthSalary(int currentDepartment) { // Считаем месячный бюджет отдела на зарплаты
        int totalMonthSalary = 0;
        for (Employee emp : employee) {
            if (emp != null && emp.getDepartment() == currentDepartment) {
                totalMonthSalary += emp.getSalary();
            }
        }
        return totalMonthSalary;
    }

    public Employee getMinSalaryEmployee() { // Ищем неудачника с минимальной зарплатой
        Employee minSalaryEmployee = new Employee();
        for (int department = 1; department <= DEPARTMENT_QUANTITY; department++) {
            Employee minSalaryByDepartment = getMinSalaryEmployee(department);
            if (minSalaryEmployee.getFullName() == null || minSalaryEmployee.getSalary() > minSalaryByDepartment.getSalary()) {
                minSalaryEmployee = minSalaryByDepartment;
            }
        }
        return minSalaryEmployee;
    }

    public Employee getMinSalaryEmployee(int currentDepartment) { // Ищем неудачника в отделе с минимальной зарплатой
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

    public Employee getMaxSalaryEmployee() { // Ищем выскочку с максимальной зарплатой
        Employee maxSalaryEmployee = new Employee();
        for (int department = 1; department <= DEPARTMENT_QUANTITY; department++) {
            Employee maxSalaryByDepartment = getMaxSalaryEmployee(department);
            if (maxSalaryEmployee.getFullName() == null || maxSalaryEmployee.getSalary() < maxSalaryByDepartment.getSalary()) {
                maxSalaryEmployee = maxSalaryByDepartment;
            }
        }
        return maxSalaryEmployee;
    }

    public Employee getMaxSalaryEmployee(int currentDepartment) { // Ищем выскочку в отделе с максимальной зарплатой
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

    public double getAverageSalary() { // Определяем среднюю зарплату по больнице
        int totalSalary = getMonthSalary();
        int employeeCounter = 0;
        for (Employee emp : employee) {
            if (emp != null) {
                employeeCounter++;
            }
        }
        return (double) totalSalary / employeeCounter;
    }

    public double getAverageSalary(int currentDepartment) { // Определяем среднюю зарплату по отделу
        int totalSalary = getMonthSalary(currentDepartment);
        int employeeCounter = 0;
        for (Employee emp : employee) {
            if (emp != null && emp.getDepartment() == currentDepartment) {
                employeeCounter++;
            }
        }
        return (double) totalSalary / employeeCounter;
    }

    public LinkedList<String> getAllFullName() { // Возвращаем ФЫО по всем сотрудникам
        LinkedList<String> list = new LinkedList<>();
        for (Employee emp : employee) {
            if (emp != null) {
                list.add(emp.getFullName());
            }
        }
        return list;
    }

    public boolean raiseSalary(int percent) { // Индексируем зарплату всем сотрудникам на введеный процент
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

    public LinkedList<Employee> listLessSalary(int lessSalary) { // Возвращаем список сотрудников, зарплата которых меньше указанной
        LinkedList<Employee> listLessSalary = new LinkedList<>();
        for (Employee emp : employee) {
            if (emp != null && emp.getSalary() < lessSalary) {
                listLessSalary.add(emp);
            }
        }
        return listLessSalary;
    }

    public LinkedList<Employee> listMoreSalary(int moreSalary) { // Возвращаем список сотрудников, зарплата которых больше указанной
        LinkedList<Employee> listMoreSalary = new LinkedList<>();
        for (Employee emp : employee) {
            if (emp != null && emp.getSalary() >= moreSalary) {
                listMoreSalary.add(emp);
            }
        }
        return listMoreSalary;
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
        if (!isDepartmentValid(newDepartment)) { // Если введеный отдел вне допустимого диапазона - вываливаемся
            return false;
        }
        for (Employee value : employee) {
            if (value != null && value.getFullName().equals(fullName)) {
                value.setDepartment(newDepartment);
                return true;
            }
        }
        return false;
    }

    public static boolean isDepartmentValid(int d) {
        if (d > 0 && d <= DEPARTMENT_QUANTITY) {
            return true;
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
