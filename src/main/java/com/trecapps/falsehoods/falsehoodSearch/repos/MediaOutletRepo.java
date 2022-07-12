package com.trecapps.falsehoods.falsehoodSearch.repos;

import com.trecapps.base.InfoResource.models.MediaOutlet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaOutletRepo extends JpaRepository<MediaOutlet, Integer> {

	
	@Query("select m from MediaOutlet m where m.name = ?1")
	MediaOutlet getOutletByName(String name);
	
	@Query("select m from MediaOutlet m where m.name like %:name%")
	List<MediaOutlet> getOutletLikeName(String name);
}
