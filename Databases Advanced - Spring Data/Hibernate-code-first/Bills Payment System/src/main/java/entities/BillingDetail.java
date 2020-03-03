package entities;

import javax.persistence.*;

@Entity
@Table (name = "billing_details")
public class BillingDetail extends BaseEntity {
    private int number;
    private User owner;

    public BillingDetail() {
    }
    @Column 
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
