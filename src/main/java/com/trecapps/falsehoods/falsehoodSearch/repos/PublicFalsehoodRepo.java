package com.trecapps.falsehoods.falsehoodSearch.repos;

import com.trecapps.base.FalsehoodModel.models.PublicFalsehood;
import com.trecapps.base.InfoResource.models.Institution;
import com.trecapps.base.InfoResource.models.PublicFigure;
import com.trecapps.base.InfoResource.models.Region;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

@Repository
public interface PublicFalsehoodRepo extends JpaRepository<PublicFalsehood, BigInteger> {
    
    //// Queries where falsehoods are merely submitted
    
    @Query("select f from PublicFalsehood f where f.status = 10 or f.status = 11 or f.status < 2")
	List<PublicFalsehood> getSubmittedFalsehoods(Pageable p);
    
    //// Queries where falsehoods are considered confirmed and active
    
    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11)")
    List<PublicFalsehood> getConfirmedFalsehoods(Pageable p, @Param("offType")byte offType);
    
    //// Start with Between
    
    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11) and f.dateMade >= :begin and f.dateMade <= :end")
    List<PublicFalsehood> getConfirmedFalsehoodsBetween(Pageable p, @Param("offType")byte offType, @Param("begin") Date begin, @Param("end")Date end);
    

    

    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11) and f.official = :official and f.dateMade >= :begin and f.dateMade <= :end")
    List<PublicFalsehood> getConfirmedFalsehoodsBetweenAndByOfficial(Pageable p, @Param("offType")byte offType, @Param("begin") Date begin, @Param("end")Date end,
    		@Param("official")PublicFigure official);
    
    
    //// Between and base Severity
    
    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11) and f.severity >= :maxSeverity and f.severity <= :minSeverity and f.dateMade >= :begin and f.dateMade <= :end")
    List<PublicFalsehood> getConfirmedFalsehoodsBetweenAndBySeverity(Pageable p, @Param("offType")byte offType, @Param("begin") Date begin, @Param("end")Date end, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity);
    

    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11) and f.severity >= :maxSeverity and f.severity <= :minSeverity and f.official = :official and f.dateMade >= :begin and f.dateMade <= :end")
    List<PublicFalsehood> getConfirmedFalsehoodsBetweenAndBySeverityOfficial(Pageable p, @Param("offType")byte offType, @Param("begin") Date begin, @Param("end")Date end,
    		@Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity, @Param("official")PublicFigure official);
    

    //// Between and Min Severity
    
    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11) and f.severity <= :minSeverity and f.dateMade >= :begin and f.dateMade <= :end")
    List<PublicFalsehood> getConfirmedFalsehoodsBetweenAndByMinSeverity(Pageable p, @Param("offType")byte offType, @Param("begin") Date begin, @Param("end")Date end, @Param("minSeverity") byte minSeverity);
    
    

    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11) and f.severity <= :minSeverity and f.official = :official and f.dateMade >= :begin and f.dateMade <= :end")
    List<PublicFalsehood> getConfirmedFalsehoodsBetweenAndByMinSeverityOfficial(Pageable p, @Param("offType")byte offType, @Param("begin") Date begin, @Param("end")Date end,
    		@Param("minSeverity") byte minSeverity, @Param("official")PublicFigure official);
    
    
    //// Between and Max Severity
    
    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11) and f.severity >= :maxSeverity and f.dateMade >= :begin and f.dateMade <= :end")
    List<PublicFalsehood> getConfirmedFalsehoodsBetweenAndByMaxSeverity(Pageable p, @Param("offType")byte offType, @Param("begin") Date begin, @Param("end")Date end, @Param("maxSeverity") byte maxSeverity);
    
    

    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11) and f.severity >= :maxSeverity and f.official = :official and f.dateMade >= :begin and f.dateMade <= :end")
    List<PublicFalsehood> getConfirmedFalsehoodsBetweenAndByMaxSeverityOfficial(Pageable p, @Param("offType")byte offType, @Param("begin") Date begin, @Param("end")Date end,
    		@Param("maxSeverity") byte maxSeverity, @Param("official")PublicFigure official);

    
    //// Now do Before
    
    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11)  and f.dateMade <= :end")
    List<PublicFalsehood> getConfirmedFalsehoodsBefore(Pageable p, @Param("offType")byte offType,  @Param("end")Date end);
   
    

    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11) and f.official = :official  and f.dateMade <= :end")
    List<PublicFalsehood> getConfirmedFalsehoodsBeforeAndByOfficial(Pageable p, @Param("offType")byte offType,  @Param("end")Date end,
    		@Param("official")PublicFigure official);
    
    //// Before and base Severity
    
    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11) and f.severity >= :maxSeverity and f.severity <= :minSeverity  and f.dateMade <= :end")
    List<PublicFalsehood> getConfirmedFalsehoodsBeforeAndBySeverity(Pageable p, @Param("offType")byte offType,  @Param("end")Date end, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity);

    

    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11) and f.severity >= :maxSeverity and f.severity <= :minSeverity and f.official = :official  and f.dateMade <= :end")
    List<PublicFalsehood> getConfirmedFalsehoodsBeforeAndBySeverityOfficial(Pageable p, @Param("offType")byte offType,  @Param("end")Date end,
    		@Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity, @Param("official")PublicFigure official);

    //// Before and Min Severity
    
    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11) and f.severity <= :minSeverity  and f.dateMade <= :end")
    List<PublicFalsehood> getConfirmedFalsehoodsBeforeAndByMinSeverity(Pageable p, @Param("offType")byte offType,  @Param("end")Date end, @Param("minSeverity") byte minSeverity);
    


    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11) and f.severity <= :minSeverity and f.official = :official  and f.dateMade <= :end")
    List<PublicFalsehood> getConfirmedFalsehoodsBeforeAndByMinSeverityOfficial(Pageable p, @Param("offType")byte offType,  @Param("end")Date end,
    		@Param("minSeverity") byte minSeverity, @Param("official")PublicFigure official);

    //// Before and Max Severity
    
    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11) and f.severity >= :maxSeverity  and f.dateMade <= :end")
    List<PublicFalsehood> getConfirmedFalsehoodsBeforeAndByMaxSeverity(Pageable p, @Param("offType")byte offType,  @Param("end")Date end, @Param("maxSeverity") byte maxSeverity);
    
    

    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11) and f.severity >= :maxSeverity and f.official = :official  and f.dateMade <= :end")
    List<PublicFalsehood> getConfirmedFalsehoodsBeforeAndByMaxSeverityOfficial(Pageable p, @Param("offType")byte offType,  @Param("end")Date end,
    		@Param("maxSeverity") byte maxSeverity, @Param("official")PublicFigure official);

    
    ///// Now Do no Dates

    

    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11) and f.official = :official ")
    List<PublicFalsehood> getConfirmedFalsehoodsByOfficial(Pageable p, @Param("offType")byte offType, 
    		@Param("official")PublicFigure official);
    
    //// Before and base Severity
    
    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11) and f.severity >= :maxSeverity and f.severity <= :minSeverity ")
    List<PublicFalsehood> getConfirmedFalsehoodsBySeverity(Pageable p, @Param("offType")byte offType,  @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity);
    

    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11) and f.severity >= :maxSeverity and f.severity <= :minSeverity and f.official = :official ")
    List<PublicFalsehood> getConfirmedFalsehoodsBySeverityOfficial(Pageable p, @Param("offType")byte offType, 
    		@Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity, @Param("official")PublicFigure official);

    //// Before and Min Severity
    
    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11) and f.severity <= :minSeverity ")
    List<PublicFalsehood> getConfirmedFalsehoodsByMinSeverity(Pageable p, @Param("offType")byte offType,  @Param("minSeverity") byte minSeverity);

    

    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11) and f.severity <= :minSeverity and f.official = :official ")
    List<PublicFalsehood> getConfirmedFalsehoodsByMinSeverityOfficial(Pageable p, @Param("offType")byte offType, 
    		@Param("minSeverity") byte minSeverity, @Param("official")PublicFigure official);
    
    //// Before and Max Severity
    
    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11) and f.severity >= :maxSeverity ")
    List<PublicFalsehood> getConfirmedFalsehoodsByMaxSeverity(Pageable p, @Param("offType")byte offType,  @Param("maxSeverity") byte maxSeverity);
    

    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11) and f.severity >= :maxSeverity and f.official = :official ")
    List<PublicFalsehood> getConfirmedFalsehoodsByMaxSeverityOfficial(Pageable p, @Param("offType")byte offType, 
    		@Param("maxSeverity") byte maxSeverity, @Param("official")PublicFigure official);
 
    
    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.region = :region ")
    List<PublicFalsehood> getConfirmedFalsehoodsByRegion(Pageable p, @Param("offType")byte offType, 
    		@Param("region") Region region);
    
    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.institution = :inst ")
    List<PublicFalsehood> getConfirmedFalsehoodsByInstitution(Pageable p, @Param("offType")byte offType, 
    		@Param("inst") Institution i);
    
    //// Time For Rejected Falsehoods
    
    
    
    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and f.status > 4")
    List<PublicFalsehood> getRejectedFalsehoods(Pageable p, @Param("offType")byte offType);
    
    //// Start with Between
    
    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.dateMade >= :begin and f.dateMade <= :end")
    List<PublicFalsehood> getRejectedFalsehoodsBetween(Pageable p, @Param("offType")byte offType, @Param("begin") Date begin, @Param("end")Date end);
    
    

    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.official = :official and f.dateMade >= :begin and f.dateMade <= :end")
    List<PublicFalsehood> getRejectedFalsehoodsBetweenAndByOfficial(Pageable p, @Param("offType")byte offType, @Param("begin") Date begin, @Param("end")Date end,
    		@Param("official")PublicFigure official);
    
    //// Between and base Severity
    
    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.severity >= :maxSeverity and f.severity <= :minSeverity and f.dateMade >= :begin and f.dateMade <= :end")
    List<PublicFalsehood> getRejectedFalsehoodsBetweenAndBySeverity(Pageable p, @Param("offType")byte offType, @Param("begin") Date begin, @Param("end")Date end, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity);
    

    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.severity >= :maxSeverity and f.severity <= :minSeverity and f.official = :official and f.dateMade >= :begin and f.dateMade <= :end")
    List<PublicFalsehood> getRejectedFalsehoodsBetweenAndBySeverityOfficial(Pageable p, @Param("offType")byte offType, @Param("begin") Date begin, @Param("end")Date end,
    		@Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity, @Param("official")PublicFigure official);
    
    //// Between and Min Severity
    
    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.severity <= :minSeverity and f.dateMade >= :begin and f.dateMade <= :end")
    List<PublicFalsehood> getRejectedFalsehoodsBetweenAndByMinSeverity(Pageable p, @Param("offType")byte offType, @Param("begin") Date begin, @Param("end")Date end, @Param("minSeverity") byte minSeverity);
    

    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.severity <= :minSeverity and f.official = :official and f.dateMade >= :begin and f.dateMade <= :end")
    List<PublicFalsehood> getRejectedFalsehoodsBetweenAndByMinSeverityOfficial(Pageable p, @Param("offType")byte offType, @Param("begin") Date begin, @Param("end")Date end,
    		@Param("minSeverity") byte minSeverity, @Param("official")PublicFigure official);

    
    //// Between and Max Severity
    
    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.severity >= :maxSeverity and f.dateMade >= :begin and f.dateMade <= :end")
    List<PublicFalsehood> getRejectedFalsehoodsBetweenAndByMaxSeverity(Pageable p, @Param("offType")byte offType, @Param("begin") Date begin, @Param("end")Date end, @Param("maxSeverity") byte maxSeverity);
    
    

    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.severity >= :maxSeverity and f.official = :official and f.dateMade >= :begin and f.dateMade <= :end")
    List<PublicFalsehood> getRejectedFalsehoodsBetweenAndByMaxSeverityOfficial(Pageable p, @Param("offType")byte offType, @Param("begin") Date begin, @Param("end")Date end,
    		@Param("maxSeverity") byte maxSeverity, @Param("official")PublicFigure official);

    
    //// Now do Before
    
    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.dateMade <= :end")
    List<PublicFalsehood> getRejectedFalsehoodsBefore(Pageable p, @Param("offType")byte offType,  @Param("end")Date end);
    

    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.official = :official  and f.dateMade <= :end")
    List<PublicFalsehood> getRejectedFalsehoodsBeforeAndByOfficial(Pageable p, @Param("offType")byte offType,  @Param("end")Date end,
    		@Param("official")PublicFigure official);
    
    
    //// Before and base Severity
    
    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.severity >= :maxSeverity and f.severity <= :minSeverity  and f.dateMade <= :end")
    List<PublicFalsehood> getRejectedFalsehoodsBeforeAndBySeverity(Pageable p, @Param("offType")byte offType,  @Param("end")Date end, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity);

    

    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.severity >= :maxSeverity and f.severity <= :minSeverity and f.official = :official  and f.dateMade <= :end")
    List<PublicFalsehood> getRejectedFalsehoodsBeforeAndBySeverityOfficial(Pageable p, @Param("offType")byte offType,  @Param("end")Date end,
    		@Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity, @Param("official")PublicFigure official);
    
    //// Before and Min Severity
    
    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.severity <= :minSeverity  and f.dateMade <= :end")
    List<PublicFalsehood> getRejectedFalsehoodsBeforeAndByMinSeverity(Pageable p, @Param("offType")byte offType,  @Param("end")Date end, @Param("minSeverity") byte minSeverity);
    

    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.severity <= :minSeverity and f.official = :official  and f.dateMade <= :end")
    List<PublicFalsehood> getRejectedFalsehoodsBeforeAndByMinSeverityOfficial(Pageable p, @Param("offType")byte offType,  @Param("end")Date end,
    		@Param("minSeverity") byte minSeverity, @Param("official")PublicFigure official);

    //// Before and Max Severity
    
    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.severity >= :maxSeverity  and f.dateMade <= :end")
    List<PublicFalsehood> getRejectedFalsehoodsBeforeAndByMaxSeverity(Pageable p, @Param("offType")byte offType,  @Param("end")Date end, @Param("maxSeverity") byte maxSeverity);


    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.severity >= :maxSeverity and f.official = :official  and f.dateMade <= :end")
    List<PublicFalsehood> getRejectedFalsehoodsBeforeAndByMaxSeverityOfficial(Pageable p, @Param("offType")byte offType,  @Param("end")Date end,
    		@Param("maxSeverity") byte maxSeverity, @Param("official")PublicFigure official);

    ///// Now Do no Dates
    
    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.region = :region ")
    List<PublicFalsehood> getRejectedFalsehoodsByRegion(Pageable p, @Param("offType")byte offType, 
    		@Param("region") Region region);
    
    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.institution = :inst ")
    List<PublicFalsehood> getRejectedFalsehoodsByInstitution(Pageable p, @Param("offType")byte offType, 
    		@Param("inst") Institution i);
    
    

    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.official = :official ")
    List<PublicFalsehood> getRejectedFalsehoodsByOfficial(Pageable p, @Param("offType")byte offType, 
    		@Param("official")PublicFigure official);
    
    
    //// Before and base Severity
    
    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.severity >= :maxSeverity and f.severity <= :minSeverity ")
    List<PublicFalsehood> getRejectedFalsehoodsBySeverity(Pageable p, @Param("offType")byte offType,  @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity);
    
    

    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.severity >= :maxSeverity and f.severity <= :minSeverity and f.official = :official ")
    List<PublicFalsehood> getRejectedFalsehoodsBySeverityOfficial(Pageable p, @Param("offType")byte offType, 
    		@Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity, @Param("official")PublicFigure official);
    
    //// Before and Min Severity
    
    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.severity <= :minSeverity ")
    List<PublicFalsehood> getRejectedFalsehoodsByMinSeverity(Pageable p, @Param("offType")byte offType,  @Param("minSeverity") byte minSeverity);


    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.severity <= :minSeverity and f.official = :official ")
    List<PublicFalsehood> getRejectedFalsehoodsByMinSeverityOfficial(Pageable p, @Param("offType")byte offType, 
    		@Param("minSeverity") byte minSeverity, @Param("official")PublicFigure official);

    //// Before and Max Severity
    
    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.severity >= :maxSeverity ")
    List<PublicFalsehood> getRejectedFalsehoodsByMaxSeverity(Pageable p, @Param("offType")byte offType,  @Param("maxSeverity") byte maxSeverity);
    
    

    @Query("select f from PublicFalsehood f where (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.severity >= :maxSeverity and f.official = :official ")
    List<PublicFalsehood> getRejectedFalsehoodsByMaxSeverityOfficial(Pageable p, @Param("offType")byte offType, 
    		@Param("maxSeverity") byte maxSeverity, @Param("official")PublicFigure official);
    

    
    /////// Adding Search terms to the list /////////
    
    
    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11)")
    List<PublicFalsehood> getConfirmedFalsehoods(Pageable p, @Param("terms") String terms, @Param("offType")byte offType);
    
    //// Start with Between
    
    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11) and f.dateMade >= :begin and f.dateMade <= :end")
    List<PublicFalsehood> getConfirmedFalsehoodsBetween(Pageable p, @Param("terms") String terms, @Param("offType")byte offType, @Param("begin") Date begin, @Param("end")Date end);
    
    

    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11) and f.official = :official and f.dateMade >= :begin and f.dateMade <= :end")
    List<PublicFalsehood> getConfirmedFalsehoodsBetweenAndByOfficial(Pageable p, @Param("terms") String terms, @Param("offType")byte offType, @Param("begin") Date begin, @Param("end")Date end,
    		@Param("official")PublicFigure official);

    
    //// Between and base Severity
    
    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11) and f.severity >= :maxSeverity and f.severity <= :minSeverity and f.dateMade >= :begin and f.dateMade <= :end")
    List<PublicFalsehood> getConfirmedFalsehoodsBetweenAndBySeverity(Pageable p, @Param("terms") String terms, @Param("offType")byte offType, @Param("begin") Date begin, @Param("end")Date end, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity);
    

    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11) and f.severity >= :maxSeverity and f.severity <= :minSeverity and f.official = :official and f.dateMade >= :begin and f.dateMade <= :end")
    List<PublicFalsehood> getConfirmedFalsehoodsBetweenAndBySeverityOfficial(Pageable p, @Param("terms") String terms, @Param("offType")byte offType, @Param("begin") Date begin, @Param("end")Date end,
    		@Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity, @Param("official")PublicFigure official);

    
    //// Between and Min Severity
    
    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11) and f.severity <= :minSeverity and f.dateMade >= :begin and f.dateMade <= :end")
    List<PublicFalsehood> getConfirmedFalsehoodsBetweenAndByMinSeverity(Pageable p, @Param("terms") String terms, @Param("offType")byte offType, @Param("begin") Date begin, @Param("end")Date end, @Param("minSeverity") byte minSeverity);

    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11) and f.severity <= :minSeverity and f.official = :official and f.dateMade >= :begin and f.dateMade <= :end")
    List<PublicFalsehood> getConfirmedFalsehoodsBetweenAndByMinSeverityOfficial(Pageable p, @Param("terms") String terms, @Param("offType")byte offType, @Param("begin") Date begin, @Param("end")Date end,
    		@Param("minSeverity") byte minSeverity, @Param("official")PublicFigure official);
    
    //// Between and Max Severity
    
    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11) and f.severity >= :maxSeverity and f.dateMade >= :begin and f.dateMade <= :end")
    List<PublicFalsehood> getConfirmedFalsehoodsBetweenAndByMaxSeverity(Pageable p, @Param("terms") String terms, @Param("offType")byte offType, @Param("begin") Date begin, @Param("end")Date end, @Param("maxSeverity") byte maxSeverity);
    

    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11) and f.severity >= :maxSeverity and f.official = :official and f.dateMade >= :begin and f.dateMade <= :end")
    List<PublicFalsehood> getConfirmedFalsehoodsBetweenAndByMaxSeverityOfficial(Pageable p, @Param("terms") String terms, @Param("offType")byte offType, @Param("begin") Date begin, @Param("end")Date end,
    		@Param("maxSeverity") byte maxSeverity, @Param("official")PublicFigure official);
    

    //// Now do Before
    
    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11)  and f.dateMade <= :end")
    List<PublicFalsehood> getConfirmedFalsehoodsBefore(Pageable p, @Param("terms") String terms, @Param("offType")byte offType,  @Param("end")Date end);
    

    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11) and f.official = :official  and f.dateMade <= :end")
    List<PublicFalsehood> getConfirmedFalsehoodsBeforeAndByOfficial(Pageable p, @Param("terms") String terms, @Param("offType")byte offType,  @Param("end")Date end,
    		@Param("official")PublicFigure official);
    
    //// Before and base Severity
    
    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11) and f.severity >= :maxSeverity and f.severity <= :minSeverity  and f.dateMade <= :end")
    List<PublicFalsehood> getConfirmedFalsehoodsBeforeAndBySeverity(Pageable p, @Param("terms") String terms, @Param("offType")byte offType,  @Param("end")Date end, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity);
    

    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11) and f.severity >= :maxSeverity and f.severity <= :minSeverity and f.official = :official  and f.dateMade <= :end")
    List<PublicFalsehood> getConfirmedFalsehoodsBeforeAndBySeverityOfficial(Pageable p, @Param("terms") String terms, @Param("offType")byte offType,  @Param("end")Date end,
    		@Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity, @Param("official")PublicFigure official);

    //// Before and Min Severity
    
    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11) and f.severity <= :minSeverity  and f.dateMade <= :end")
    List<PublicFalsehood> getConfirmedFalsehoodsBeforeAndByMinSeverity(Pageable p, @Param("terms") String terms, @Param("offType")byte offType,  @Param("end")Date end, @Param("minSeverity") byte minSeverity);
    

    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11) and f.severity <= :minSeverity and f.official = :official  and f.dateMade <= :end")
    List<PublicFalsehood> getConfirmedFalsehoodsBeforeAndByMinSeverityOfficial(Pageable p, @Param("terms") String terms, @Param("offType")byte offType,  @Param("end")Date end,
    		@Param("minSeverity") byte minSeverity, @Param("official")PublicFigure official);
    
    
    //// Before and Max Severity
    
    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11) and f.severity >= :maxSeverity  and f.dateMade <= :end")
    List<PublicFalsehood> getConfirmedFalsehoodsBeforeAndByMaxSeverity(Pageable p, @Param("terms") String terms, @Param("offType")byte offType,  @Param("end")Date end, @Param("maxSeverity") byte maxSeverity);
    

    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11) and f.severity >= :maxSeverity and f.official = :official  and f.dateMade <= :end")
    List<PublicFalsehood> getConfirmedFalsehoodsBeforeAndByMaxSeverityOfficial(Pageable p, @Param("terms") String terms, @Param("offType")byte offType,  @Param("end")Date end,
    		@Param("maxSeverity") byte maxSeverity, @Param("official")PublicFigure official);

    
    ///// Now Do no Dates
    

    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11) and f.official = :official ")
    List<PublicFalsehood> getConfirmedFalsehoodsByOfficial(Pageable p, @Param("terms") String terms, @Param("offType")byte offType, 
    		@Param("official")PublicFigure official);
    
    //// Before and base Severity
    
    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11) and f.severity >= :maxSeverity and f.severity <= :minSeverity ")
    List<PublicFalsehood> getConfirmedFalsehoodsBySeverity(Pageable p, @Param("terms") String terms, @Param("offType")byte offType,  @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity);
    

    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11) and f.severity >= :maxSeverity and f.severity <= :minSeverity and f.official = :official ")
    List<PublicFalsehood> getConfirmedFalsehoodsBySeverityOfficial(Pageable p, @Param("terms") String terms, @Param("offType")byte offType, 
    		@Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity, @Param("official")PublicFigure official);
   
    
    //// Before and Min Severity
    
    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11) and f.severity <= :minSeverity ")
    List<PublicFalsehood> getConfirmedFalsehoodsByMinSeverity(Pageable p, @Param("terms") String terms, @Param("offType")byte offType,  @Param("minSeverity") byte minSeverity);
    

    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11) and f.severity <= :minSeverity and f.official = :official ")
    List<PublicFalsehood> getConfirmedFalsehoodsByMinSeverityOfficial(Pageable p, @Param("terms") String terms, @Param("offType")byte offType, 
    		@Param("minSeverity") byte minSeverity, @Param("official")PublicFigure official);
    
    //// Before and Max Severity
    
    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11) and f.severity >= :maxSeverity ")
    List<PublicFalsehood> getConfirmedFalsehoodsByMaxSeverity(Pageable p, @Param("terms") String terms, @Param("offType")byte offType,  @Param("maxSeverity") byte maxSeverity);
    

    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and ((f.status > 1 and f.status < 5) or f.status > 11) and f.severity >= :maxSeverity and f.official = :official ")
    List<PublicFalsehood> getConfirmedFalsehoodsByMaxSeverityOfficial(Pageable p, @Param("terms") String terms, @Param("offType")byte offType, 
    		@Param("maxSeverity") byte maxSeverity, @Param("official")PublicFigure official);
        
    
    
    //// Time For Rejected Falsehoods
    
    
    
    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and f.status > 4")
    List<PublicFalsehood> getRejectedFalsehoods(Pageable p, @Param("terms") String terms, @Param("offType")byte offType);
    
    //// Start with Between
    
    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.dateMade >= :begin and f.dateMade <= :end")
    List<PublicFalsehood> getRejectedFalsehoodsBetween(Pageable p, @Param("terms") String terms, @Param("offType")byte offType, @Param("begin") Date begin, @Param("end")Date end);
    
    

    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.official = :official and f.dateMade >= :begin and f.dateMade <= :end")
    List<PublicFalsehood> getRejectedFalsehoodsBetweenAndByOfficial(Pageable p, @Param("terms") String terms, @Param("offType")byte offType, @Param("begin") Date begin, @Param("end")Date end,
    		@Param("official")PublicFigure official);
    
    
    //// Between and base Severity
    
    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.severity >= :maxSeverity and f.severity <= :minSeverity and f.dateMade >= :begin and f.dateMade <= :end")
    List<PublicFalsehood> getRejectedFalsehoodsBetweenAndBySeverity(Pageable p, @Param("terms") String terms, @Param("offType")byte offType, @Param("begin") Date begin, @Param("end")Date end, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity);

    

    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.severity >= :maxSeverity and f.severity <= :minSeverity and f.official = :official and f.dateMade >= :begin and f.dateMade <= :end")
    List<PublicFalsehood> getRejectedFalsehoodsBetweenAndBySeverityOfficial(Pageable p, @Param("terms") String terms, @Param("offType")byte offType, @Param("begin") Date begin, @Param("end")Date end,
    		@Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity, @Param("official")PublicFigure official);

    
    //// Between and Min Severity
    
    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.severity <= :minSeverity and f.dateMade >= :begin and f.dateMade <= :end")
    List<PublicFalsehood> getRejectedFalsehoodsBetweenAndByMinSeverity(Pageable p, @Param("terms") String terms, @Param("offType")byte offType, @Param("begin") Date begin, @Param("end")Date end, @Param("minSeverity") byte minSeverity);
    

    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.severity <= :minSeverity and f.official = :official and f.dateMade >= :begin and f.dateMade <= :end")
    List<PublicFalsehood> getRejectedFalsehoodsBetweenAndByMinSeverityOfficial(Pageable p, @Param("terms") String terms, @Param("offType")byte offType, @Param("begin") Date begin, @Param("end")Date end,
    		@Param("minSeverity") byte minSeverity, @Param("official")PublicFigure official);

    
    //// Between and Max Severity
    
    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.severity >= :maxSeverity and f.dateMade >= :begin and f.dateMade <= :end")
    List<PublicFalsehood> getRejectedFalsehoodsBetweenAndByMaxSeverity(Pageable p, @Param("terms") String terms, @Param("offType")byte offType, @Param("begin") Date begin, @Param("end")Date end, @Param("maxSeverity") byte maxSeverity);
    

    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.severity >= :maxSeverity and f.official = :official and f.dateMade >= :begin and f.dateMade <= :end")
    List<PublicFalsehood> getRejectedFalsehoodsBetweenAndByMaxSeverityOfficial(Pageable p, @Param("terms") String terms, @Param("offType")byte offType, @Param("begin") Date begin, @Param("end")Date end,
    		@Param("maxSeverity") byte maxSeverity, @Param("official")PublicFigure official);

    
    //// Now do Before
    
    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.dateMade <= :end")
    List<PublicFalsehood> getRejectedFalsehoodsBefore(Pageable p, @Param("terms") String terms, @Param("offType")byte offType,  @Param("end")Date end);
    

    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.official = :official  and f.dateMade <= :end")
    List<PublicFalsehood> getRejectedFalsehoodsBeforeAndByOfficial(Pageable p, @Param("terms") String terms, @Param("offType")byte offType,  @Param("end")Date end,
    		@Param("official")PublicFigure official);

    
    //// Before and base Severity
    
    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.severity >= :maxSeverity and f.severity <= :minSeverity  and f.dateMade <= :end")
    List<PublicFalsehood> getRejectedFalsehoodsBeforeAndBySeverity(Pageable p, @Param("terms") String terms, @Param("offType")byte offType,  @Param("end")Date end, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity);
    

    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.severity >= :maxSeverity and f.severity <= :minSeverity and f.official = :official  and f.dateMade <= :end")
    List<PublicFalsehood> getRejectedFalsehoodsBeforeAndBySeverityOfficial(Pageable p, @Param("terms") String terms, @Param("offType")byte offType,  @Param("end")Date end,
    		@Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity, @Param("official")PublicFigure official);
    
    //// Before and Min Severity
    
    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.severity <= :minSeverity  and f.dateMade <= :end")
    List<PublicFalsehood> getRejectedFalsehoodsBeforeAndByMinSeverity(Pageable p, @Param("terms") String terms, @Param("offType")byte offType,  @Param("end")Date end, @Param("minSeverity") byte minSeverity);
    

    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.severity <= :minSeverity and f.official = :official  and f.dateMade <= :end")
    List<PublicFalsehood> getRejectedFalsehoodsBeforeAndByMinSeverityOfficial(Pageable p, @Param("terms") String terms, @Param("offType")byte offType,  @Param("end")Date end,
    		@Param("minSeverity") byte minSeverity, @Param("official")PublicFigure official);

    
    //// Before and Max Severity
    
    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.severity >= :maxSeverity  and f.dateMade <= :end")
    List<PublicFalsehood> getRejectedFalsehoodsBeforeAndByMaxSeverity(Pageable p, @Param("terms") String terms, @Param("offType")byte offType,  @Param("end")Date end, @Param("maxSeverity") byte maxSeverity);
    

    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.severity >= :maxSeverity and f.official = :official  and f.dateMade <= :end")
    List<PublicFalsehood> getRejectedFalsehoodsBeforeAndByMaxSeverityOfficial(Pageable p, @Param("terms") String terms, @Param("offType")byte offType,  @Param("end")Date end,
    		@Param("maxSeverity") byte maxSeverity, @Param("official")PublicFigure official);
    
    ///// Now Do no Dates
    

    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.official = :official ")
    List<PublicFalsehood> getRejectedFalsehoodsByOfficial(Pageable p, @Param("terms") String terms, @Param("offType")byte offType, 
    		@Param("official")PublicFigure official);
    
    //// Before and base Severity
    
    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.severity >= :maxSeverity and f.severity <= :minSeverity ")
    List<PublicFalsehood> getRejectedFalsehoodsBySeverity(Pageable p, @Param("terms") String terms, @Param("offType")byte offType,  @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity);
    

    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.severity >= :maxSeverity and f.severity <= :minSeverity and f.official = :official ")
    List<PublicFalsehood> getRejectedFalsehoodsBySeverityOfficial(Pageable p, @Param("terms") String terms, @Param("offType")byte offType, 
    		@Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity, @Param("official")PublicFigure official);
    
    //// Before and Min Severity
    
    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.severity <= :minSeverity ")
    List<PublicFalsehood> getRejectedFalsehoodsByMinSeverity(Pageable p, @Param("terms") String terms, @Param("offType")byte offType,  @Param("minSeverity") byte minSeverity);
    
    

    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.severity <= :minSeverity and f.official = :official ")
    List<PublicFalsehood> getRejectedFalsehoodsByMinSeverityOfficial(Pageable p, @Param("terms") String terms, @Param("offType")byte offType, 
    		@Param("minSeverity") byte minSeverity, @Param("official")PublicFigure official);
    
    
    //// Before and Max Severity
    
    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.severity >= :maxSeverity ")
    List<PublicFalsehood> getRejectedFalsehoodsByMaxSeverity(Pageable p, @Param("terms") String terms, @Param("offType")byte offType,  @Param("maxSeverity") byte maxSeverity);
    

    @Query("select f from PublicFalsehood f where f.tags like %:terms% and (:offType > 15 or f.officialType = :offType) and f.status > 4 and f.severity >= :maxSeverity and f.official = :official ")
    List<PublicFalsehood> getRejectedFalsehoodsByMaxSeverityOfficial(Pageable p, @Param("terms") String terms, @Param("offType")byte offType, 
    		@Param("maxSeverity") byte maxSeverity, @Param("official")PublicFigure official);
    
    
}
