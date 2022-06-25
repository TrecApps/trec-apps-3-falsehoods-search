package com.trecapps.falsehoods.falsehoodSearch.repos;

import com.trecapps.falsehoods.falsehoodSearch.models.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstitutionRepo extends JpaRepository<Institution, Long> {
	
	@Query("select i from Institution i where i.name like %:name%")
	List<Institution> getLikeName(String name);
}
