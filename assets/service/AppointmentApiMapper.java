package com.capgemini.training.appointmentbooking.service.mapper;

import java.util.Optional;

import com.capgemini.training.appointmentbooking.common.to.AppointmentBookingEto;
import com.capgemini.training.appointmentbooking.common.to.AppointmentCto;
import com.capgemini.training.appointmentbooking.common.to.AppointmentEto;
import com.capgemini.training.appointmentbooking.service.model.Appointment;
import com.capgemini.training.appointmentbooking.service.model.AppointmentRequest;

public class AppointmentApiMapper {

	public AppointmentBookingEto toBookingEto(AppointmentRequest request) {
		return AppointmentBookingEto.builder().clientId(request.getClientId()).treatmentId(request.getTreatmentId())
				.specialistId(0L) // TODO specjalista nie jest częścią requestu – usunąć to po usunięciu
									// specialistId tylko z AppointmentBookingEto,
				// bo jedyne użycie AppointmentBookingEto#specialistId jest w
				// findAppointmentUc.hasConflictingAppointment
				// gdzie specialistId da się wyciągnąć z treatment
				.dateTime(request.getDateTime().toInstant()).build();
	}

	public Appointment toApiAppointment(AppointmentCto cto) {
		AppointmentEto appointmentEto = cto.appointmentEto();

		Appointment result = new Appointment();
		result.setId(Optional.of(appointmentEto.id()));
		result.setClientId(Optional.of(cto.clientEto().id()));
		result.setTreatmentId(Optional.of(cto.treatmentCto().treatmentEto().id()));
		result.setDateTime(Optional.of(java.util.Date.from(appointmentEto.dateTime())));
		result.setStatus(Optional.of(Appointment.StatusEnum.valueOf(appointmentEto.status().name())));
		return result;
	}

}
