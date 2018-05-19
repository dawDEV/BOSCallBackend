package de.dorianweidler.boscallserver.repositories;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import de.dorianweidler.boscallserver.dto.Unit;

public interface UnitRepository extends CrudRepository<Unit, Long> {
	
	public Unit findById(long id);
	public Unit findByIdAndSecret(long id, String secret);
	
	@Query(value = "SELECT units_id FROM user_unit WHERE users_id = ?1", nativeQuery = true)
	public List<BigInteger> findUnitIdsByUserId(long userId);
	
}
