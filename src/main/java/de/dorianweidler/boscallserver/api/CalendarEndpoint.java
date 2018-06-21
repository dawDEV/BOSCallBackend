package de.dorianweidler.boscallserver.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.dorianweidler.boscallserver.dto.CalendarEntry;
import de.dorianweidler.boscallserver.dto.User;
import de.dorianweidler.boscallserver.repositories.CalendarEntryRepository;
import de.dorianweidler.boscallserver.repositories.UserRepository;

@RestController
@RequestMapping("api/calendar")
public class CalendarEndpoint {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CalendarEntryRepository calendarEntryRepository;
	
	@RequestMapping("userid={userid}&apiKey={apiKey}")
	public ResponseEntity<List<CalendarEntry>> getCalendarEntriesForUserIDAndAPIKey(@PathVariable(name = "userid") long userid, @PathVariable(name = "apiKey") String apiKey) {
		User user = userRepository.findByIdAndApiKey(userid, apiKey);
		if(user == null) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<List<CalendarEntry>>(calendarEntryRepository.findByUserId(userid), HttpStatus.OK);
	}
	
}
