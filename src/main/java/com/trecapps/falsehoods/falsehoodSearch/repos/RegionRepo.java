package com.trecapps.falsehoods.falsehoodSearch.repos;

import com.trecapps.base.InfoResource.models.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepo extends JpaRepository<Region, Long>
{
	@Query("select r from Region r where r.name like %:name%")
	List<Region> getLikeName(String name);
}
