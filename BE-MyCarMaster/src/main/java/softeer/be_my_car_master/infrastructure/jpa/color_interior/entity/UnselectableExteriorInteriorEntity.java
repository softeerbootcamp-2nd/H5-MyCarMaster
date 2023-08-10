package softeer.be_my_car_master.infrastructure.jpa.color_interior.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import softeer.be_my_car_master.infrastructure.jpa.color_exterior.entity.ExteriorColorEntity;

@Entity
@Table(name = "trim_exterior_interior")
public class UnselectableExteriorInteriorEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "exterior_color_id")
	private ExteriorColorEntity exteriorColor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "interior_color_id")
	private InteriorColorEntity interiorColor;
}

