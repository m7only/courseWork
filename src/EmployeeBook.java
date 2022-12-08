import java.util.Arrays;

public class EmployeeBook {
    private Employee[] employee = new Employee[10];

    public boolean addEmployee(String fullName, int department, int salary) { // Добавляем сотрудника. Если добавился - ок, если нет места в массиве - ошибка
        for (int i = 0; i < employee.length; i++) {
            if (employee[i] == null) {
                employee[i] = new Employee(fullName, department, salary);
                return true;
            }
        }
        return false;
    }

    public boolean removeEmployee(String input) { // Увольняем сотрудника по ФЫО или id, если удален - ок, если нет - не ок.
        if (isInteger(input)) {
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

    public void listAll() { // Выводим все данные по сотрудникам в консоль
        for (Employee emp : employee) {
            if (emp != null) System.out.println(emp);
        }
    }

    public void listAll(int currentDepartment) { // Выводим данные сотрудников выбранного отдела
        for (Employee emp : employee) {
            if (emp != null) {
                if (emp.getDepartment() == currentDepartment)
                    System.out.printf("id=%d, %s, зарплата %d\n", emp.getId(), emp.getFullName(), emp.getSalary());
            }
        }
    }

    public void listAllByDepartment() {
        int counter = 0;
        for (int i = 1; i <= 5; i++) {
            System.out.println("Состав сотрудников отдела " + i + ":");
            for (Employee emp : employee) {
                if (emp != null && emp.getDepartment() == i) {
                    System.out.println("    " + emp.getFullName());
                    counter++;
                }
            }
            if (counter == 0) System.out.println("  В отделе нет сотрудников.");
            counter = 0;
        }
    }

    public int monthSalary() { // Считаем месячный бюджет на зарплаты
        int totalMonthSalary = 0;
        for (Employee emp : employee) {
            if (emp != null) totalMonthSalary += emp.getSalary();
        }
        return totalMonthSalary;
    }

    public int monthSalary(int currentDepartment) { // Считаем месячный бюджет отдела на зарплаты
        int totalMonthSalary = 0;
        for (Employee emp : employee) {
            if (emp != null && emp.getDepartment() == currentDepartment) totalMonthSalary += emp.getSalary();
        }
        return totalMonthSalary;
    }

    public Employee minSalaryEmployee() { // Ищем неудачника с минимальной зарплатой
        Employee minSalaryEmployee = employee[0];
        for (Employee emp : employee) {
            if (emp != null) {
                if (minSalaryEmployee.getSalary() > emp.getSalary()) minSalaryEmployee = emp;
            }
        }
        return minSalaryEmployee;
    }

    public Employee minSalaryEmployee(int currentDepartment) { // Ищем неудачника в отделе с минимальной зарплатой
        Employee minSalaryEmployee = new Employee();
        for (Employee emp : employee) { // ищем хоть кого-нибудь из отдела для присвоения начальной зп
            if (emp.getDepartment() == currentDepartment) {
                minSalaryEmployee = emp;
                break;
            }
        }

        for (Employee emp : employee) {
            if (emp != null) {
                if (minSalaryEmployee.getSalary() > emp.getSalary() && emp.getDepartment() == currentDepartment)
                    minSalaryEmployee = emp;
            }
        }
        return minSalaryEmployee;
    }

    public Employee maxSalaryEmployee() { // Ищем выскочку с максимальной зарплатой
        Employee maxSalaryEmployee = employee[0];
        for (Employee emp : employee) {
            if (emp != null) {
                if (maxSalaryEmployee.getSalary() < emp.getSalary()) maxSalaryEmployee = emp;
            }
        }
        return maxSalaryEmployee;
    }

    public Employee maxSalaryEmployee(int currentDepartment) { // Ищем выскочку в отделе с максимальной зарплатой
        Employee maxSalaryEmployee = new Employee();
        for (Employee emp : employee) { // ищем хоть кого-нибудь из отдела для присвоения начальной зп
            if (emp.getDepartment() == currentDepartment) {
                maxSalaryEmployee = emp;
                break;
            }
        }

        for (Employee emp : employee) {
            if (emp != null) {
                if (maxSalaryEmployee.getSalary() < emp.getSalary() && emp.getDepartment() == currentDepartment)
                    maxSalaryEmployee = emp;
            }
        }
        return maxSalaryEmployee;
    }

    public int averageSalary() { // Определяем среднюю зарплату по больнице
        int totalSalary = monthSalary();
        int employeeCounter = 0;
        for (Employee emp : employee) {
            if (emp != null) employeeCounter++;
        }
        return totalSalary / employeeCounter;
    }

    public int averageSalary(int currentDepartment) { // Определяем среднюю зарплату по отделу
        int totalSalary = monthSalary(currentDepartment);
        int employeeCounter = 0;
        for (Employee emp : employee) {
            if (emp != null && emp.getDepartment() == currentDepartment) employeeCounter++;
        }
        return totalSalary / employeeCounter;
    }

    public void listAllFullName() { // Выводим ФЫО по всем сотрудника в консоль
        for (Employee emp : employee) {
            if (emp != null) System.out.println(emp.getFullName());
        }
    }

    public boolean raiseSalary(int percent) { // Индексируем зарплату на введеный процент
        double k = (double) percent / 100 + 1;
        for (int i = 0; i < employee.length; i++) {
            if (employee[i] != null) employee[i].setSalary((int) (employee[i].getSalary() * k));
        }
        return true;
    }

    public boolean raiseSalary(int percent, int currentDepartment) { // Индексируем зарплату отделу на введеный процент
        double k = (double) percent / 100 + 1;
        for (int i = 0; i < employee.length; i++) {
            if (employee[i] != null && employee[i].getDepartment() == currentDepartment)
                employee[i].setSalary((int) (employee[i].getSalary() * k));
        }
        return true;
    }

    public void listLessSalary(int lessSalary) { // Выводим данные сотрудников, зарплата которых мегьше указанной
        System.out.printf("Сотрудники, зарплата которых меньше %d:\n", lessSalary);
        for (Employee emp : employee) {
            if (emp != null) {
                if (emp.getSalary() < lessSalary)
                    System.out.printf("id=%d,%s, зарплата: %d\n", emp.getId(), emp.getFullName(), emp.getSalary());
            }
        }
    }

    public void listMoreSalary(int moreSalary) { // Выводим данные сотрудников, зарплата которых мегьше указанной
        System.out.printf("Сотрудники, зарплата которых больше %d:\n", moreSalary);
        for (Employee emp : employee) {
            if (emp != null) {
                if (emp.getSalary() >= moreSalary)
                    System.out.printf("id=%d,%s, зарплата: %d\n", emp.getId(), emp.getFullName(), emp.getSalary());
            }
        }
    }

    public boolean changeSalary(String fullName, int salary) {
        for (int i = 0; i < employee.length; i++) {
            if (employee[i] != null && employee[i].getFullName().equals(fullName)) {
                employee[i].setSalary(salary);
                return true;
            }
        }
        return false;
    }

    public boolean changeDepartment(String fullName, int department) {
        for (int i = 0; i < employee.length; i++) {
            if (employee[i] != null && employee[i].getFullName().equals(fullName)) {
                employee[i].setDepartment(department);
                return true;
            }
        }
        return false;
    }

    public static boolean isInteger(String string) {
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
