package com.trecapps.falsehoods.falsehoodSearch.repos;

import com.trecapps.base.FalsehoodModel.models.PublicFalsehoodUncommonTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicFalsehoodUncommonTagRepo extends JpaRepository<PublicFalsehoodUncommonTag, String> {
}
