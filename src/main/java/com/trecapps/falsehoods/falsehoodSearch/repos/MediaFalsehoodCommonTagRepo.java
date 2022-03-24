package com.trecapps.falsehoods.falsehoodSearch.repos;

import com.trecapps.base.FalsehoodModel.models.FalsehoodCommonTag;
import com.trecapps.base.InfoResource.models.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaFalsehoodCommonTagRepo extends JpaRepository<FalsehoodCommonTag, String> {
}
