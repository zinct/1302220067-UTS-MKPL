package lib;

public class GradeBasedSalaryCalculator implements SalaryCalculator {

    private static final int GRADE_1_SALARY = 3000000;
    private static final int GRADE_2_SALARY = 5000000;
    private static final int GRADE_3_SALARY = 7000000;
    private static final double FOREIGNER_SALARY_MULTIPLIER = 1.5;

    @Override
    public int calculateSalary(int grade, boolean isForeigner) {
        int salary;

        switch (grade) {
            case 1:
                salary = GRADE_1_SALARY;
                break;
            case 2:
                salary = GRADE_2_SALARY;
                break;
            case 3:
                salary = GRADE_3_SALARY;
                break;
            default:
                throw new IllegalArgumentException("Invalid grade: " + grade);
        }

        if (isForeigner) {
            salary = (int) (salary * FOREIGNER_SALARY_MULTIPLIER);
        }

        return salary;
    }
}
