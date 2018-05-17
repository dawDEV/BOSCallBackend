package de.dorianweidler.boscallserver.repositories;

import org.springframework.data.repository.CrudRepository;

import de.dorianweidler.boscallserver.dto.Unit;

public interface UnitRepository extends CrudRepository<Unit, Long> {
	
	public Unit findById(long id);
	public Unit findByIdAndSecret(long id, String secret);
	
}
