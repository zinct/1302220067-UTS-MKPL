package lib;

public class TaxFunction {

    private static final int BASE_NON_TAXABLE = 54000000;
    private static final int MARRIAGE_ALLOWANCE = 4500000;
    private static final int CHILD_ALLOWANCE = 4500000;
    private static final int MAX_CHILDREN = 3;
    private static final double TAX_PERCENTAGE = 0.05;

    /**
     * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
     * 
     * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
     * 
     * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
     * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
     * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
     */
    
    public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {

        if (numberOfMonthWorking > 12) {
            System.err.println("More than 12 month working per year");
        }

        // Membatasi jumlah anak maksimal 3
        numberOfChildren = Math.min(numberOfChildren, MAX_CHILDREN);

        int annualIncome = calculateAnnualIncome(monthlySalary, otherMonthlyIncome, numberOfMonthWorking);
        int nonTaxableIncome = calculateNonTaxableIncome(isMarried, numberOfChildren);

        int tax = calculateTaxAmount(annualIncome, deductible, nonTaxableIncome);

        return Math.max(tax, 0);
    }

    private static int calculateAnnualIncome(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking) {
        return (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking;
    }

    private static int calculateNonTaxableIncome(boolean isMarried, int numberOfChildren) {
        int nonTaxableIncome = BASE_NON_TAXABLE;

        if (isMarried) {
            nonTaxableIncome += MARRIAGE_ALLOWANCE;
            nonTaxableIncome += CHILD_ALLOWANCE * numberOfChildren;
        }

        return nonTaxableIncome;
    }

    private static int calculateTaxAmount(int annualIncome, int deductible, int nonTaxableIncome) {
        return (int) Math.round(TAX_PERCENTAGE * (annualIncome - deductible - nonTaxableIncome));
    }
}
