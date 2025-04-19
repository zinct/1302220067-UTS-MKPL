package lib;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

public class Employee {

    private static final int GRADE_1_SALARY = 3000000;
    private static final int GRADE_2_SALARY = 5000000;
    private static final int GRADE_3_SALARY = 7000000;
    private static final double FOREIGNER_SALARY_MULTIPLIER = 1.5;

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
    private boolean gender; //true = Laki-laki, false = Perempuan
    
    private int monthlySalary;
    private int otherMonthlyIncome;
    private int annualDeductible;
    
    private String spouseName;
    private String spouseIdNumber;

    private List<String> childNames;
    private List<String> childIdNumbers;
    
    public Employee(String employeeId, String firstName, String lastName, String idNumber, String address, int yearJoined, int monthJoined, int dayJoined, boolean isForeigner, boolean gender) {
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
        
        childNames = new LinkedList<String>();
        childIdNumbers = new LinkedList<String>();
    }
    
    /**
     * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya
     * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
     */
    public void setMonthlySalary(int grade) {
        switch(grade) {
            case 1:
                monthlySalary = GRADE_1_SALARY;
                break;
            case 2:
                monthlySalary = GRADE_2_SALARY;
                break;
            case 3:
                monthlySalary = GRADE_3_SALARY;
                break;
            default:
                throw new IllegalArgumentException("Invalid grade: " + grade);
        }
        
        // Apply foreigner salary multiplier if applicable
        if (isForeigner) {
            monthlySalary = applyForeignerSalaryMultiplier(monthlySalary);
        }
    }
    
    private int applyForeignerSalaryMultiplier(int salary) {
        return (int) (salary * FOREIGNER_SALARY_MULTIPLIER);
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
        childNames.add(childName);
        childIdNumbers.add(childIdNumber);
    }
    
    public int getAnnualIncomeTax() {
        
        // Menghitung berapa lama pegawai bekerja dalam setahun ini, jika pegawai sudah bekerja dari tahun sebelumnya maka otomatis dianggap 12 bulan.
        LocalDate date = LocalDate.now();
        
        if (date.getYear() == yearJoined) {
            monthWorkingInYear = date.getMonthValue() - monthJoined;
        } else {
            monthWorkingInYear = 12;
        }
        
        return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible, spouseIdNumber.equals(""), childIdNumbers.size());
    }
}
