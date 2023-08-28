package softeer.be_my_car_master.application.consult.usecase.get_consultings;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.domain.consulting.Consulting;
import softeer.be_my_car_master.global.annotation.Adaptor;
import softeer.be_my_car_master.infrastructure.jpa.car_master.repository.CarMasterJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.car_master.repository.ConsultingJpaRepository;

@Adaptor
@RequiredArgsConstructor
public class GetConsultingsAdaptor implements GetConsultingsPort {


	private final CarMasterJpaRepository carMasterJpaRepository;
	private final ConsultingJpaRepository consultingJpaRepository;

	@Override
	public Optional<Long> findCarMasterIdByForm(String email, String phone) {
		return carMasterJpaRepository.findIdByForm(email, phone);
	}

	@Override
	public List<Consulting> findConsultingsByCarMaster(Long carMasterId) {
		return consultingJpaRepository.findAllByCarMaster(carMasterId);
	}
}
