package com.trecapps.falsehoods.falsehoodSearch.repos;

import com.trecapps.base.InfoResource.models.PublicFigure;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicFigureRepo extends JpaRepository<PublicFigure, Long>
{
	@Query("select p from PublicFigure p where p.approved > 0")
	Page<PublicFigure> findAllApproved(Pageable pageable);
	
	@Query("select p from PublicFigure p where p.firstname like %:f% and p.middleNames like %:m% or p.lastName like %:l%")
	List<PublicFigure> findLikeName(String f, String m, String l);
	
	@Query("select p from PublicFigure p where p.firstname like %:f% and p.middleNames like %:n% or p.lastName like %:n%")
	List<PublicFigure> findLikeName(String f, String n);
	
	@Query("select p from PublicFigure p where p.firstname like %:name% or p.lastName like %:name%")
	List<PublicFigure> findLikeName(String name);
}
