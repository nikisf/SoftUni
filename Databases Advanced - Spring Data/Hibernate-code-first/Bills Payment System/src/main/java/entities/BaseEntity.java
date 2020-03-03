package entities;

import javax.persistence.*;

@MappedSuperclass
public abstract class BaseEntity {
    private int id;

    protected BaseEntity() {
    }
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
