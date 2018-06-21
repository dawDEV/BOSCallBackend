package de.dorianweidler.boscallserver.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	
	@JsonBackReference
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="user_unit", joinColumns=@JoinColumn(name = "user_id"), inverseJoinColumns=@JoinColumn(name="unit_id"))
	List<Unit> units;
	String apiKey;
	String token;
	String name;
	
}
