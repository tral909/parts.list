package ru.javarush.internship.parts.list.model;

import java.util.List;

public class PartList {
    private int canAssemblyComps;
    private List<Part> parts;

    public PartList() {
    }

    public PartList(int canAssemblyComps, List<Part> parts) {
        this.canAssemblyComps = canAssemblyComps;
        this.parts = parts;
    }

    public int getCanAssemblyComps() {
        return canAssemblyComps;
    }

    public void setCanAssemblyComps(int canAssemblyComps) {
        this.canAssemblyComps = canAssemblyComps;
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setList(List<Part> list) {
        this.parts = list;
    }
}
