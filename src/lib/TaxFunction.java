package lib;

public class TaxFunction {

    private static final int BASE_NON_TAXABLE_INCOME = 54000000;
    private static final int MARRIED_ALLOWANCE = 4500000;
    private static final int CHILD_ALLOWANCE = 4500000;
    private static final int MAX_CHILDREN = 3;
    private static final double TAX_RATE = 0.05;

    /**
     * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
     */
    public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {

        validateWorkingMonths(numberOfMonthWorking);
        numberOfChildren = adjustNumberOfChildren(numberOfChildren);

        int annualIncome = calculateAnnualIncome(monthlySalary, otherMonthlyIncome, numberOfMonthWorking);
        int nonTaxableIncome = calculateNonTaxableIncome(isMarried, numberOfChildren);

        return calculateTaxAmount(annualIncome, deductible, nonTaxableIncome);
    }

    private static void validateWorkingMonths(int numberOfMonthWorking) {
        if (numberOfMonthWorking > 12) {
            throw new IllegalArgumentException("Number of working months cannot exceed 12.");
        }
    }

    private static int adjustNumberOfChildren(int numberOfChildren) {
        return Math.min(numberOfChildren, MAX_CHILDREN);
    }

    private static int calculateAnnualIncome(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking) {
        return (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking;
    }

    private static int calculateNonTaxableIncome(boolean isMarried, int numberOfChildren) {
        int nonTaxableIncome = BASE_NON_TAXABLE_INCOME;

        if (isMarried) {
            nonTaxableIncome += MARRIED_ALLOWANCE;
            nonTaxableIncome += calculateChildrenAllowance(numberOfChildren);
        }

        return nonTaxableIncome;
    }

    private static int calculateChildrenAllowance(int numberOfChildren) {
        return CHILD_ALLOWANCE * numberOfChildren;
    }

    private static int calculateTaxAmount(int annualIncome, int deductible, int nonTaxableIncome) {
        int taxableIncome = annualIncome - deductible - nonTaxableIncome;
        int taxAmount = (int) Math.round(TAX_RATE * taxableIncome);
        return Math.max(taxAmount, 0);
    }
}
