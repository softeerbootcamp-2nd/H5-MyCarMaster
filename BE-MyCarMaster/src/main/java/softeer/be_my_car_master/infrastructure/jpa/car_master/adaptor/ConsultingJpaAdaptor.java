package softeer.be_my_car_master.infrastructure.jpa.car_master.adaptor;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.consult.usecase.port.ConsultingPort;
import softeer.be_my_car_master.domain.consulting.Consulting;
import softeer.be_my_car_master.global.annotation.Adaptor;
import softeer.be_my_car_master.infrastructure.jpa.car_master.entity.ConsultingEntity;
import softeer.be_my_car_master.infrastructure.jpa.car_master.repository.ConsultingJpaRepository;

@Adaptor
@RequiredArgsConstructor
public class ConsultingJpaAdaptor implements ConsultingPort {

	private final ConsultingJpaRepository consultingJpaRepository;

	@Override
	public void createConsulting(Consulting consulting) {
		consultingJpaRepository.save(ConsultingEntity.from(consulting));
	}
}
