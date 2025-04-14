package com.capgemini.training.appointmentbooking.service.mapper;

import java.util.Optional;

import com.capgemini.training.appointmentbooking.common.to.SpecialistEto;
import com.capgemini.training.appointmentbooking.common.to.TreatmentCreationTo;
import com.capgemini.training.appointmentbooking.common.to.TreatmentCto;
import com.capgemini.training.appointmentbooking.common.to.TreatmentEto;
import com.capgemini.training.appointmentbooking.service.model.Treatment;
import com.capgemini.training.appointmentbooking.service.model.TreatmentDetails;
import com.capgemini.training.appointmentbooking.service.model.TreatmentDetailsAllOfSpecialist;
import com.capgemini.training.appointmentbooking.service.model.TreatmentRequest;

public class TreatmentApiMapper {

	public TreatmentCreationTo toCreationTo(TreatmentRequest request) {
		return TreatmentCreationTo.builder().name(request.getName().orElse(null))
				.durationMinutes(request.getDuration().orElse(0)).specialistId(request.getSpecialistId().orElse(null))
				.description("Default description").build();
	}

	public Treatment toApiTreatment(TreatmentCto cto) {
		TreatmentEto eto = cto.treatmentEto();
		SpecialistEto specialist = cto.specialistEto();

		Treatment result = new Treatment();
		result.setId(Optional.ofNullable(eto.id()));
		result.setName(Optional.ofNullable(eto.name()));
		result.setDuration(Optional.of(eto.durationMinutes()));
		result.setSpecialistId(Optional.ofNullable(specialist.id()));
		return result;
	}

	public TreatmentDetails toApiTreatmentDetails(TreatmentCto cto) {
		TreatmentEto eto = cto.treatmentEto();
		SpecialistEto specialist = cto.specialistEto();

		TreatmentDetails result = new TreatmentDetails();
		result.setId(Optional.ofNullable(eto.id()));
		result.setName(Optional.ofNullable(eto.name()));
		result.setDuration(Optional.of(eto.durationMinutes()));
		result.setSpecialistId(Optional.ofNullable(specialist.id()));

		TreatmentDetailsAllOfSpecialist specialistDto = new TreatmentDetailsAllOfSpecialist();
		specialistDto.setId(Optional.ofNullable(specialist.id()));
		specialistDto.setName(Optional.ofNullable(specialist.specialization().name()));

		result.setSpecialist(Optional.of(specialistDto));
		return result;
	}

}
