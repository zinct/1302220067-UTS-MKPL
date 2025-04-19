package lib;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Employee {

    private String employeeId;
    private String firstName;
    private String lastName;
    private String idNumber;
    private String address;

    private int yearJoined;
    private int monthJoined;
    private int dayJoined;
    private int monthWorkingInYear;

    private boolean isForeigner;
    private boolean gender; // true = Laki-laki, false = Perempuan

    private int monthlySalary;
    private int otherMonthlyIncome;
    private int annualDeductible;

    private String spouseName;
    private String spouseIdNumber;

    private List<String> childNames;
    private List<String> childIdNumbers;

    private SalaryCalculator salaryCalculator;  // Dependency Injection untuk SalaryCalculator

    // Konstruktor dengan Dependency Injection
    public Employee(String employeeId, String firstName, String lastName, String idNumber, String address, 
                    int yearJoined, int monthJoined, int dayJoined, boolean isForeigner, 
                    boolean gender, SalaryCalculator salaryCalculator) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.address = address;
        this.yearJoined = yearJoined;
        this.monthJoined = monthJoined;
        this.dayJoined = dayJoined;
        this.isForeigner = isForeigner;
        this.gender = gender;
        this.salaryCalculator = salaryCalculator;

        this.childNames = new LinkedList<>();
        this.childIdNumbers = new LinkedList<>();
    }

    public void setMonthlySalary(int grade) {
        this.monthlySalary = salaryCalculator.calculateSalary(grade, isForeigner);
    }

    public void setAnnualDeductible(int deductible) {
        this.annualDeductible = deductible;
    }

    public void setAdditionalIncome(int income) {
        this.otherMonthlyIncome = income;
    }

    public void setSpouse(String spouseName, String spouseIdNumber) {
        this.spouseName = spouseName;
        this.spouseIdNumber = spouseIdNumber;
    }

    public void addChild(String childName, String childIdNumber) {
        this.childNames.add(childName);
        this.childIdNumbers.add(childIdNumber);
    }

    public int getAnnualIncomeTax() {

        LocalDate date = LocalDate.now();

        if (date.getYear() == yearJoined) {
            monthWorkingInYear = date.getMonthValue() - monthJoined;
        } else {
            monthWorkingInYear = 12;
        }

        return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible, 
                                        spouseIdNumber.equals(""), childIdNumbers.size());
    }
}
