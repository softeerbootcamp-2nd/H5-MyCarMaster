package softeer.bemycarmaster.api.domain.trim.usecase.port;

import java.util.List;

import softeer.bemycarmaster.api.domain.trim.domain.Trim;
import softeer.bemycarmaster.api.global.annotation.Port;

@Port
public interface TrimPort {

	List<Trim> findTrims(Long modelId);
}
