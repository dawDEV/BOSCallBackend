package de.dorianweidler.boscallserver.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import de.dorianweidler.boscallserver.dto.CalendarEntry;

public interface CalendarEntryRepository extends CrudRepository<CalendarEntry, Long> {

	@Query(nativeQuery = true, value = "SELECT * FROM calendar_entry, unit WHERE unit_id IN (SELECT unit_id FROM user_unit WHERE user_id = ?1)")
	public List<CalendarEntry> findByUserId(long userId);
	
}
