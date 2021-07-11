package ru.javarush.internship.parts.list.dto;

import java.util.List;

public final class PartListDto {
    private final List<PartDto> parts;
    private final int canAssemblyComps;

    public PartListDto(List<PartDto> parts, int canAssemblyComps) {
        this.parts = parts;
        this.canAssemblyComps = canAssemblyComps;
    }

    public int getCanAssemblyComps() {
        return canAssemblyComps;
    }

    public List<PartDto> getParts() {
        return parts;
    }
}
