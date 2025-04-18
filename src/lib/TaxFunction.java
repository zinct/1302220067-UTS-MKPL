package lib;

public class TaxFunction {

    // Penambahan konstanta untuk menggantikan magic number
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
     * 
     */
    
    public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
        
        int tax = 0;
        
        if (numberOfMonthWorking > 12) {
            System.err.println("More than 12 month working per year");
        }
        
        if (numberOfChildren > 3) {
            numberOfChildren = 3;
        }
        
        if (isMarried) {
            // 54000000 = BASE_NON_TAXABLE
            // 4500000 = MARRIAGE_ALLOWANCE
            // 1500000 * numberOfChildren = CHILD_ALLOWANCE * numberOfChildren (yang ditulis salah di logika awal)
            tax = (int) Math.round(0.05 * (((monthlySalary + otherMonthlyIncome) * numberOfMonthWorking) - deductible - (54000000 + 4500000 + (numberOfChildren * 1500000))));
        }else {
            tax = (int) Math.round(0.05 * (((monthlySalary + otherMonthlyIncome) * numberOfMonthWorking) - deductible - 54000000));
        }
        
        if (tax < 0) {
            return 0;
        }else {
            return tax;
        }
             
    }
    
}
