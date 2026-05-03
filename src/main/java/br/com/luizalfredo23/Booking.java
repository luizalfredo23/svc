package br.com.luizalfredo23;

import java.time.LocalDate;

public record Booking(Long id,
		String customerName,
		String destination,
		LocalDate startDate,
		LocalDate endDate,
		BookingStatus status,
		Category category) {

}
