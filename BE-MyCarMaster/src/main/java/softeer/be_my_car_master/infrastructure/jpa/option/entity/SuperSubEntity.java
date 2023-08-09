package softeer.be_my_car_master.infrastructure.jpa.option.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "super_sub")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SuperSubEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "super_option_id")
	private OptionEntity superOption;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sub_option_id")
	private OptionEntity subOption;
}
