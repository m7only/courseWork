import java.util.LinkedList;
import java.util.List;

public class EmployeeBook {
    private final Employee[] employee = new Employee[10];
    private static final int DEPARTMENT_QUANTITY = 5; // количество отделов согласно условий задачи

    public boolean addEmployee(Employee employee) { // Добавляем сотрудника. Если добавился - ок, если нет места в массиве - ошибка
        if (isDepartmentValid(employee.getDepartment())) { // проверка на правильно введенный отдел
            for (int i = 0; i < this.employee.length; i++) {
                if (this.employee[i] == null) { // проверка на пустую ячейку в масиве
                    this.employee[i] = employee;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean removeEmployee(String input) { // Увольняем сотрудника по ФЫО, если удален - ок, если нет - не ок.
        for (int i = 0; i < employee.length; i++) {
            if (employee[i] != null && employee[i].getFullName().equals(input)) {
                employee[i] = null;
                return true;
            }
        }
        return false;
    }

    public boolean removeEmployee(int id) { // Увольняем сотрудника по id, если удален - ок, если нет - не ок.
        for (int i = 0; i < employee.length; i++) {
            if (employee[i] != null && employee[i].getId() == id) {
                employee[i] = null;
                return true;
            }
        }
        return false;
    }

    public List<Employee> listAll() { // Вернуть все данные по сотрудникам для вывода в консоль .toString
        List<Employee> list = new LinkedList<>();
        for (Employee emp : employee) {
            if(emp!=null){
                list.add(emp);
            }
        }
        return list;
    }

    public List<Employee> listAll(int currentDepartment) { // Возвращаем данные сотрудников выбранного отдела
        List<Employee> list = new LinkedList<>();
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
        Employee minSalaryEmployee=null;
        for (int department = 1; department <= DEPARTMENT_QUANTITY; department++) {
            Employee minSalaryByDepartment = getMinSalaryEmployee(department);
            if (minSalaryEmployee == null || minSalaryEmployee.getSalary() > minSalaryByDepartment.getSalary()) {
                minSalaryEmployee = minSalaryByDepartment;
            }
        }
        return minSalaryEmployee;
    }

    public Employee getMinSalaryEmployee(int currentDepartment) { // Ищем неудачника в отделе с минимальной зарплатой
        Employee minSalaryEmployee = null;
        for (Employee emp : employee) {
            if (emp != null && emp.getDepartment() == currentDepartment) {
                if (minSalaryEmployee.getFullName() == null || minSalaryEmployee.getSalary() > emp.getSalary()) { // для присвоения начальной зарплаты
                    minSalaryEmployee = emp;
                }
            }
        }
        return minSalaryEmployee;
    }

    public Employee getMaxSalaryEmployee() { // Ищем выскочку с максимальной зарплатой
        Employee maxSalaryEmployee = null;
        for (int department = 1; department <= DEPARTMENT_QUANTITY; department++) {
            Employee maxSalaryByDepartment = getMaxSalaryEmployee(department);
            if (maxSalaryEmployee == null || maxSalaryEmployee.getSalary() < maxSalaryByDepartment.getSalary()) {
                maxSalaryEmployee = maxSalaryByDepartment;
            }
        }
        return maxSalaryEmployee;
    }

    public Employee getMaxSalaryEmployee(int currentDepartment) { // Ищем выскочку в отделе с максимальной зарплатой
        Employee maxSalaryEmployee=null;
        for (Employee emp : employee) {
            if (emp != null && emp.getDepartment() == currentDepartment) {
                if (maxSalaryEmployee == null || maxSalaryEmployee.getSalary() < emp.getSalary()) { // Для присвоения начальной зарплаты, с которой будем сравнивать
                    maxSalaryEmployee = emp;
                }
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

    public void raiseSalary(int percent) { // Индексируем зарплату всем сотрудникам на введеный процент
        double k = (double) percent / 100 + 1;
        for (Employee value : employee) {
            if (value != null) {
                value.setSalary((int) (value.getSalary() * k));
            }
        }
    }

    public void raiseSalary(int percent, int currentDepartment) { // Индексируем зарплату отделу на введеный процент
        double k = (double) percent / 100 + 1;
        for (Employee value : employee) {
            if (value != null && value.getDepartment() == currentDepartment) {
                value.setSalary((int) (value.getSalary() * k));
            }
        }
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

    public int getDepartmentQuantity() {
        return DEPARTMENT_QUANTITY;
    }

    public static boolean isDepartmentValid(int d) {
        if (d > 0 && d <= DEPARTMENT_QUANTITY) {
            return true;
        }
        return false;
    }
}
