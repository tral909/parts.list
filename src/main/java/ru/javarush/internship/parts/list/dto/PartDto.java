package ru.javarush.internship.parts.list.dto;

public final class PartDto {
	private final Long id;
	private final String name;
	private final boolean required;
	private final int amount;

	public PartDto(Long id, String name, boolean required, int amount) {
		this.id = id;
		this.name = name;
		this.required = required;
		this.amount = amount;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public boolean isRequired() {
		return required;
	}

	public int getAmount() {
		return amount;
	}
}
