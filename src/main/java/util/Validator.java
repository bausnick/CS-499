package util;

import java.util.Date;

// ENHANCEMENT Category One - Validator.java created as a utility class for validator methods
// used across the other classes. Improves readaility, reduces redundancy, and improves
// maintainability
public class Validator {

	// Validator method for firstName, lastName, and Address. Validates entry in not
	// blank or null and truncates value to maxLength. Using parameter "fieldType" allows
    // for exception to be more descriptive
	public static String validateField(String entry, int maxLength, String fieldType) {
		if (entry == null || entry.isBlank()) {
			throw new IllegalArgumentException(fieldType + " cannot be null or blank.");
		}

		if (entry.length() > maxLength) {
			entry = entry.substring(0, maxLength);
		}

		return entry;
	}

	// Validates number is not blank or null and has ten digits
	public static String validatePhoneNumber(String entry) {
		if (entry == null || entry.isBlank()) {
			throw new IllegalArgumentException("Phone number cannot be null or blank.");
		}

		if (!entry.matches("\\d{10}")) {
			throw new IllegalArgumentException("Phone number must be ten digits.");
		}

		return entry;
	}

	// Validates the date is not null or in the past
	public static Date validateDate(Date date) {
		if (date == null) {
			throw new IllegalArgumentException("Date cannot be null.");
		}

		if (date.before(new Date())) {
			throw new IllegalArgumentException("Date must be in the future");
		}

		return date;
	}
}
