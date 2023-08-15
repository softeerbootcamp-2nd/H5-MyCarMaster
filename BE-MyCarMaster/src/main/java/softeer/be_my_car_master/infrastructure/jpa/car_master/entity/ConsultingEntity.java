package softeer.be_my_car_master.infrastructure.jpa.car_master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import softeer.be_my_car_master.domain.car_master.CarMaster;
import softeer.be_my_car_master.domain.consulting.Consulting;
import softeer.be_my_car_master.domain.estimate.Estimate;
import softeer.be_my_car_master.infrastructure.jpa.estimate.entity.EstimateEntity;

@Entity
@Table(name = "consulting")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ConsultingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "client_name", nullable = false)
	private String clientName;

	@Column(name = "client_email", nullable = false)
	private String clientEmail;

	@Column(name = "client_phone", nullable = false)
	private String clientPhone;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "estimate_id", nullable = false)
	private EstimateEntity estimate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "car_master_id", nullable = false)
	private CarMasterEntity carMaster;

	public static ConsultingEntity from(Consulting consulting) {
		Estimate estimate = consulting.getEstimate();
		EstimateEntity estimateEntity = EstimateEntity.from(estimate);

		CarMaster carMaster = consulting.getCarMaster();
		CarMasterEntity carMasterEntity = CarMasterEntity.from(carMaster);

		return ConsultingEntity.builder()
			.clientName(consulting.getClientName())
			.clientEmail(consulting.getClientEmail())
			.clientPhone(consulting.getClientPhone())
			.estimate(estimateEntity)
			.carMaster(carMasterEntity)
			.build();
	}
}
