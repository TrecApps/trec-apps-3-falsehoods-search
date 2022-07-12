package com.trecapps.falsehoods.falsehoodSearch.repos;

import com.trecapps.base.FalsehoodModel.models.FalsehoodUncommonTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaFalsehoodUncommonTagRepo extends JpaRepository<FalsehoodUncommonTag, String> {
}
