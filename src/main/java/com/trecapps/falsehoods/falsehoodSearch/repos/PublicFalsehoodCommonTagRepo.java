package com.trecapps.falsehoods.falsehoodSearch.repos;

import com.trecapps.falsehoods.falsehoodSearch.models.PublicFalsehoodCommonTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicFalsehoodCommonTagRepo extends JpaRepository<PublicFalsehoodCommonTag, String> {
}
