import entities.*;
import org.w3c.dom.ls.LSOutput;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Engine implements Runnable {

    private final EntityManager entityManager;
    private final BufferedReader reader;

    public Engine(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {
        //2. Remove Objects
        this.removeObjects();

        /*-----------------------------------------------------------------------------*/
        //3. Contains Employee
/*        try {
            this.containsEmployee();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        /*-----------------------------------------------------------------------------*/
        //4. Employees with Salary Over 50 000
        // this.employeeWithSalaryOver50000();

        /*-----------------------------------------------------------------------------*/
        //5. Employees from Department

        //this.employeesFromDepartment();

        /*-----------------------------------------------------------------------------*/
        // 6.Adding a New Address and Updating Employee

/*
        try {
            this.addNewAddressAndUpdateEmployee();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        /*-----------------------------------------------------------------------------*/
        // 7.Addresses with Employee Count
       //this.addressWithEmployeeCount();

        /*-----------------------------------------------------------------------------*/
        // 8.Get Employee with Project
 /*       try {
            this.getEmployeeWithProject();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        /*-----------------------------------------------------------------------------*/
        //9. Find Latest 10 Projects
        //this.findLatest10Projects();

        /*-----------------------------------------------------------------------------*/
        //10. Increase Salaries
      // this.increaseSalaries();

        /*-----------------------------------------------------------------------------*/
        //11.Remove Towns
    /*    try {
            this.removeTowns();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        /*-----------------------------------------------------------------------------*/
        //12.Find Employees by First Name
      /*  try {
            this.findEmployeesByFirstName();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        /*-----------------------------------------------------------------------------*/
        //13. Employees Maximum Salaries
      // this.employeesMaximumSalaries();


        /*-----------------------------------------------------------------------------*/
        /*-----------------------------------------------------------------------------*/
        /*-----------------------------------------------------------------------------*/
    }

    private void employeesMaximumSalaries() {
        this.entityManager.getTransaction().begin();
       List<Object[]> departments = this.entityManager
                .createQuery("SELECT e.department.name, max(e.salary) FROM Employee as e group by e.department.name having max(e.salary) not between 30000 AND 70000",
                        Object[].class).getResultList();
       departments.forEach(e -> System.out.println(String.format("%s %.2f", e[0], e[1])));

        this.entityManager.getTransaction().commit();
    }

    private void findEmployeesByFirstName() throws IOException {
        System.out.print("Enter a pattern: ");
        String pattern = reader.readLine();
        this.entityManager.getTransaction().begin();
        List<Employee> employees = this.entityManager.createQuery("SELECT e from Employee as e WHERE e.firstName like :pattern", Employee.class).setParameter("pattern", pattern + "%")
                .getResultList();

        employees.forEach(e -> System.out.println(String.format("%s %s - %s - ($%.2f)", e.getFirstName(), e.getLastName(), e.getJobTitle(), e.getSalary())));



    this.entityManager.getTransaction().commit();

    }

    private void removeTowns() throws IOException {
        System.out.print("Enter Town Name: ");
        String townName = reader.readLine();
        this.entityManager.getTransaction().begin();

        Town town = this.entityManager.createQuery("SELECT t from Town as t WHERE t.name = :townName", Town.class).setParameter("townName", townName).getSingleResult();
        List<Address>addresses = this.entityManager.createQuery("SElect a from Address as a where a.town.id = :id", Address.class).setParameter("id", town.getId()).getResultList();

        int numberAddresses = addresses.size();
        addresses.forEach(e -> e.getEmployees().forEach(c -> c.setAddress(null)));
        addresses.forEach(this.entityManager::remove);
        this.entityManager.remove(town);
        System.out.println(String.format("%d %s in %s deleted", numberAddresses, numberAddresses == 1 ? "address" : "addresses", townName));
        this.entityManager.getTransaction().commit();


    }

    private void increaseSalaries() {
        this.entityManager.getTransaction().begin();
        List<String> names = Arrays.asList("Engineering", "Tool Design", "Marketing", "Information Services");


        List<Employee> employees = this.entityManager.createQuery("SELECT e FROM Employee as e where e.department.name in (:departmentNames)",Employee.class)
                .setParameter("departmentNames", names).getResultList();
                employees.forEach(e -> e.setSalary(e.getSalary().multiply(BigDecimal.valueOf(1.12))));
        this.entityManager.flush();
        this.entityManager.getTransaction().commit();
        employees.forEach(e -> System.out.println(String.format("%s %s ($%.2f)%n", e.getFirstName(), e.getLastName(), e.getSalary())));
    }

    private void findLatest10Projects() {
        this.entityManager.getTransaction().begin();
        this.entityManager.createQuery("SELECT p FROM Project as p ORDER BY p.startDate DESC", Project.class).setMaxResults(10)
                .getResultStream().sorted(Comparator.comparing(Project::getName)).forEach(e -> System.out.println(String.format("Project name: %s%n\tProject Description: %s%n\tProject Start Date: %s%n\tProject End Date: %s",
        e.getName(), e.getDescription(), e.getStartDate(),e.getEndDate())));

        this.entityManager.getTransaction().commit();
    }

    private void getEmployeeWithProject() throws IOException {
        this.entityManager.getTransaction().begin();
        System.out.print("Enter employee ID: ");
        int employeeId = Integer.parseInt(reader.readLine());

        Employee employee = this.entityManager.createQuery("SELECT e FROM Employee as e WHERE e.id = :employeeId", Employee.class).setParameter("employeeId", employeeId).getSingleResult();
        System.out.println(String.format("%s %s - %s%n\t%s", employee.getFirstName(), employee.getLastName(), employee.getJobTitle(), employee.getProjects()
                .stream().map(Project::getName).sorted().collect(Collectors.joining(System.lineSeparator() + "\t"))));

        this.entityManager.getTransaction().commit();
    }

    private void addNewAddressAndUpdateEmployee() throws IOException {
        System.out.print("Enter employee last name: ");
        String lastName = reader.readLine();
        Employee employee = this.entityManager.createQuery("SELECT e FROM Employee as e where e.lastName = :name", Employee.class).setParameter("name", lastName).setMaxResults(1).getSingleResult();
        if (!employee.getAddress().getText().equals("Vitoshka 15")){
            Address address = this.createNewAddress("Vitoshka 15");
            this.entityManager.getTransaction().begin();
            this.entityManager.detach(employee);
            employee.setAddress(address);
            this.entityManager.merge(employee);
            this.entityManager.flush();
            this.entityManager.getTransaction().commit();
        }
    }

    private Address createNewAddress(String text) {
        Address address = new Address();
        address.setText(text);
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(address);
        this.entityManager.getTransaction().commit();
        return address;
    }

    private void removeObjects() {
        List<Town> towns = this.entityManager
                .createQuery("SELECT t from Town as t", Town.class)
                .getResultList();
        this.entityManager.getTransaction().begin();
        for (Town town : towns) {
            if (town.getName().length() > 5) {
                this.entityManager.detach(town);
            }
        }
        for (Town town : towns) {
            if (this.entityManager.contains(town)){
                town.setName(town.getName().toLowerCase());
         }
        }
        towns.forEach(this.entityManager::merge);
        this.entityManager.flush();
        this.entityManager.getTransaction().commit();
    }

    private void addressWithEmployeeCount() {
        this.entityManager.getTransaction().begin();
        List<Address> addresses = this.entityManager.createQuery("SELECT a from Address as a order by a.employees.size DESC", Address.class).setMaxResults(10).getResultList();
        addresses.forEach(e -> System.out.println(String.format("%s %s - %d employees", e.getText(), e.getTown().getName(), e.getEmployees().size())));
        this.entityManager.getTransaction().commit();
    }

    private void employeesFromDepartment() {
        this.entityManager.createQuery("SELECT e from Employee as e where e.department.name = 'Research and Development'" +
                "order by e.salary, e.id", Employee.class).getResultStream().forEach(e -> System.out.println(String.format("%s %s from %s - $%.2f",
                e.getFirstName(), e.getLastName(), e.getDepartment().getName(), e.getSalary())));
    }

    private void employeeWithSalaryOver50000() {
        List<Employee> employees = this.entityManager.createQuery("SELECT e from Employee as e WHERE e.salary > 50000", Employee.class).getResultList();
        employees.forEach(e -> System.out.println(String.format("%s", e.getFirstName())));

    }


    private void containsEmployee() throws IOException {
        System.out.print("Enter employee full name: ");
        String fullName = this.reader.readLine();
        try {
            Employee employee = this.entityManager.createQuery("SELECT e FROM Employee as e " +
                    "WHERE concat(e.firstName, ' ', e.lastName) = :name", Employee.class)
                    .setParameter("name", fullName).getSingleResult();
            System.out.println("Yes");
        } catch (NoResultException nr) {
            System.out.println("No");
        }
    }
}
