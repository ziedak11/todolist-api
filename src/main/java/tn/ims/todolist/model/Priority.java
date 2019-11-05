package tn.ims.todolist.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Priority {
	LOW, MEDIUM, HIGH;

	@JsonCreator
	public static Priority create(String value) {
		if (value == null) {
			return null;
		}
		for (Priority v : values()) {
			if (value.equals(v.name())) {
				return v;
			}
		}
		return null;
	}
}
