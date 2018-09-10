package ru.javarush.internship.parts.list.model;

public class Part {
    private int id;
    private String name;
    private boolean required;
    private int amount;

    public Part() {
    }

    public Part(int id, String name, boolean required, int amount) {
        this.name = name;
        this.required = required;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
