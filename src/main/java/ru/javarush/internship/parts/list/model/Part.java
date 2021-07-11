package ru.javarush.internship.parts.list.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "part")
public class Part implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @Column(name = "required", nullable = false)
    private boolean required;
    @Column(name = "amount", nullable = false)
    private int amount;

    public Part() {
    }

    public Part(Long id, String name, boolean required, int amount) {
        this.id = id;
        this.name = name;
        this.required = required;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        super.clone();
        return new Part(id, name, required, amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Part part = (Part) o;

        if (!id.equals(part.id)) return false;
        if (required != part.required) return false;
        if (amount != part.amount) return false;
        return name.equals(part.name);
    }

    @Override
    public int hashCode() {
        Long result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + (required ? 1 : 0);
        result = 31 * result + amount;
        return result.intValue();
    }

}
