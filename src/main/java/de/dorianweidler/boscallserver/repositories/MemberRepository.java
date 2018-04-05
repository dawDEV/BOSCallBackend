package de.dorianweidler.boscallserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import de.dorianweidler.boscallserver.dto.Member;

@RepositoryRestResource
public interface MemberRepository extends JpaRepository<Member, Integer> {

}
