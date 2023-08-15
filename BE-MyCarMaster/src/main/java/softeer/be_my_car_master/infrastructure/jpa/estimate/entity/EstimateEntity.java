package softeer.be_my_car_master.infrastructure.jpa.estimate.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import softeer.be_my_car_master.domain.estimate.Estimate;
import softeer.be_my_car_master.infrastructure.jpa.body_type.entity.BodyTypeEntity;
import softeer.be_my_car_master.infrastructure.jpa.color_exterior.entity.ExteriorColorEntity;
import softeer.be_my_car_master.infrastructure.jpa.color_interior.entity.InteriorColorEntity;
import softeer.be_my_car_master.infrastructure.jpa.engine.entity.EngineEntity;
import softeer.be_my_car_master.infrastructure.jpa.model.entity.ModelEntity;
import softeer.be_my_car_master.infrastructure.jpa.option.entity.OptionEntity;
import softeer.be_my_car_master.infrastructure.jpa.trim.entity.TrimEntity;
import softeer.be_my_car_master.infrastructure.jpa.wheel_drive.entity.WheelDriveEntity;

@Entity
@Table(name = "estimate")
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EstimateEntity {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(columnDefinition = "BINARY(16)")
	private UUID id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "model_id")
	private ModelEntity model;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trim_id")
	private TrimEntity trim;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "engine_id")
	private EngineEntity engine;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "wheel_drive_id")
	private WheelDriveEntity wheelDrive;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "body_type_id")
	private BodyTypeEntity bodyType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "exterior_color_id")
	private ExteriorColorEntity exteriorColor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "interior_color_id")
	private InteriorColorEntity interiorColorEntity;

	@OneToMany(mappedBy = "estimate", cascade = CascadeType.PERSIST)
	private List<EstimateAdditionalOptionEntity> additionalOptions = new ArrayList<>();

	@OneToMany(mappedBy = "estimate", cascade = CascadeType.PERSIST)
	private List<EstimateConsiderOptionEntity> considerOptions = new ArrayList<>();

	public static EstimateEntity create(
		ModelEntity model,
		TrimEntity trim,
		EngineEntity engine,
		WheelDriveEntity wheelDrive,
		BodyTypeEntity bodyType,
		ExteriorColorEntity exteriorColor,
		InteriorColorEntity interiorColorEntity,
		List<OptionEntity> trimAdditionalOptionEntities,
		List<OptionEntity> trimConsiderOptionEntities
	) {
		EstimateEntity estimateEntity = EstimateEntity.builder()
			.model(model)
			.trim(trim)
			.engine(engine)
			.wheelDrive(wheelDrive)
			.bodyType(bodyType)
			.exteriorColor(exteriorColor)
			.interiorColorEntity(interiorColorEntity)
			.build();

		List<EstimateAdditionalOptionEntity> estimateAdditionalOptions = trimAdditionalOptionEntities.stream()
			.map(optionEntity -> EstimateAdditionalOptionEntity.create(estimateEntity, optionEntity))
			.collect(Collectors.toList());

		List<EstimateConsiderOptionEntity> estimateConsiderOptions = trimConsiderOptionEntities.stream()
			.map(optionEntity -> EstimateConsiderOptionEntity.create(estimateEntity, optionEntity))
			.collect(Collectors.toList());

		estimateEntity.setAdditionalOptions(estimateAdditionalOptions);
		estimateEntity.setConsiderOptions(estimateConsiderOptions);

		return estimateEntity;
	}

	public static EstimateEntity from(Estimate estimate) {
		return EstimateEntity.builder()
			.id(estimate.getId())
			.build();
	}

	private void setAdditionalOptions(List<EstimateAdditionalOptionEntity> estimateAdditionalOptions) {
		this.additionalOptions = estimateAdditionalOptions;
	}

	private void setConsiderOptions(List<EstimateConsiderOptionEntity> estimateConsiderOptions) {
		this.considerOptions = estimateConsiderOptions;
	}

	public Estimate toEstimate() {
		return new Estimate(id);
	}
}
