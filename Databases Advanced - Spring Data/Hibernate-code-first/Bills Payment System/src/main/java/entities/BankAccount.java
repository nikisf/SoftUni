package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table (name = "bank_accounts")
public class BankAccount extends BillingDetail {
    private String bankName;
    private String SWIFTCode;

    public BankAccount() {
    }
    @Column (name = "bank_name", nullable = false)
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    @Column (name = "SWIFT_code", nullable = false)
    public String getSWIFTCode() {
        return SWIFTCode;
    }

    public void setSWIFTCode(String SWIFTCode) {
        this.SWIFTCode = SWIFTCode;
    }
}
