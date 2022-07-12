package com.trecapps.falsehoods.falsehoodSearch.repos;

import com.trecapps.base.FalsehoodModel.models.Falsehood;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

@Repository
public interface FalsehoodRepo extends JpaRepository<Falsehood, BigInteger> {




	
	//// Queries where Falsehoods are merely submitted
	
	@Query("select f from Falsehood f where f.status < 2")
	List<Falsehood> getSubmittedFalsehood(Pageable p);
	
	//// Queries where Falsehoods are considered confirmed and active
	
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11)")
//	List<Falsehood> getConfirmedFalsehoods(Pageable p);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getConfirmedFalsehoodsByPublicOfficial(Pageable p, @Param("author") PublicFigure author);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.severity >= :maxSeverity and f.severity <= :minSeverity")
//	List<Falsehood> getConfirmedFalsehoodsBySeverity(Pageable p, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.severity <= :minSeverity")
//	List<Falsehood> getConfirmedFalsehoodByMinSeverity(Pageable p, @Param("minSeverity")byte minSeverity);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.severity >= :maxSeverity")
//	List<Falsehood> getConfirmedFalsehoodByMaxSeverity(Pageable p, @Param("maxSeverity") byte maxSeverity);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.dateMade >= :begin and f.dateMade <= :end")
//	List<Falsehood> getConfirmedFalsehoodsBetween(Pageable p, @Param("begin") Date begin, @Param("end") Date end);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.dateMade <= :end")
//	List<Falsehood> getConfirmedFalsehoodsBefore(Pageable p, @Param("end") Date end);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.outlet = :outletId")
//	List<Falsehood> getConfirmedFalsehoodsByOutlet(Pageable p, @Param("outletId") MediaOutlet outletId);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.outlet = :outletId and f.severity >= :maxSeverity and f.severity <= :minSeverity")
//	List<Falsehood> getConfirmedFalsehoodsByOutletAndSeverity(Pageable p, @Param("outletId") MediaOutlet outletId,
//			@Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.outlet = :outletId and f.severity <= :minSeverity")
//	List<Falsehood> getConfirmedFalsehoodsByOutletAndMinSeverity(Pageable p, @Param("outletId") MediaOutlet outletId, @Param("minSeverity") byte minSeverity);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.outlet = :outletId and f.severity >= :maxSeverity")
//	List<Falsehood> getConfirmedFalsehoodsByOutletAndMaxSeverity(Pageable p, @Param("outletId") MediaOutlet outletId, @Param("maxSeverity") byte maxSeverity);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.outlet = :outletId and f.dateMade >= :begin and f.dateMade <= :end")
//	List<Falsehood> getConfirmedFalsehoodsBetweenAndByOutlet(Pageable p, @Param("outletId") MediaOutlet outletId, @Param("begin") Date begin, @Param("end") Date end);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.outlet = :outletId and f.dateMade <= :end")
//	List<Falsehood> getConfirmedFalsehoodsBeforeAndByOutlet(Pageable p, @Param("outletId") MediaOutlet outletId, @Param("end") Date end);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.outlet = :outletId and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getConfirmedFalsehoodsByPublicOfficialAndOutlet(Pageable p, @Param("author")PublicFigure author, @Param("outletId") MediaOutlet outletId);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and (f.author1 = :author or f.author2 = :author) and f.severity >= :maxSeverity and f.severity <= :minSeverity")
//	List<Falsehood> getConfirmedFalsehoodsByPublicOfficialAndSeverity(Pageable p, @Param("author")PublicFigure author, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and (f.author1 = :author or f.author2 = :author) and f.severity <= :minSeverity")
//	List<Falsehood> getConfirmedFalsehoodsByPublicOfficialAndMinSeverity(Pageable p, @Param("author")PublicFigure author, @Param("minSeverity") byte minSeverity);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and (f.author1 = :author or f.author2 = :author) and f.severity >= :maxSeverity")
//	List<Falsehood> getConfirmedFalsehoodsByPublicOfficialAndMaxSeverity(Pageable p, @Param("author")PublicFigure author, @Param("maxSeverity") byte maxSeverity);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and (f.author1 = :author or f.author2 = :author) and f.dateMade >= :begin and f.dateMade <= :end")
//	List<Falsehood> getConfirmedFalsehoodsBetweenAndByPublicOfficial(Pageable p, @Param("author")PublicFigure author, @Param("begin") Date begin, @Param("end") Date end);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and (f.author1 = :author or f.author2 = :author) and f.dateMade <= :end")
//	List<Falsehood> getConfirmedFalsehoodsBeforeAndByPublicOfficial(Pageable p, @Param("author")PublicFigure author, @Param("end") Date end);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.outlet = :outletId and (f.author1 = :author or f.author2 = :author) and f.severity >= :maxSeverity and f.severity <= :minSeverity")
//	List<Falsehood> getConfirmedFalsehoodsByPublicOfficialOutletAndSeverity(Pageable p, @Param("author")PublicFigure author,
//			@Param("outletId") MediaOutlet outletId,@Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.outlet = :outletId and (f.author1 = :author or f.author2 = :author) and f.severity <= :minSeverity")
//	List<Falsehood> getConfirmedFalsehoodsByPublicOfficialOutletAndMinSeverity(Pageable p, @Param("author")PublicFigure author,
//			@Param("outletId") MediaOutlet outletId, @Param("minSeverity") byte minSeverity);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.outlet = :outletId and (f.author1 = :author or f.author2 = :author) and f.severity >= :maxSeverity")
//	List<Falsehood> getConfirmedFalsehoodsByPublicOfficialOutletAndMaxSeverity(Pageable p, @Param("author")PublicFigure author,
//			@Param("outletId") MediaOutlet outletId,@Param("maxSeverity") byte maxSeverity);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.dateMade >= :begin and f.dateMade <= :end and f.severity >= :maxSeverity and f.severity <= :minSeverity")
//	List<Falsehood> getConfirmedFalsehoodsBetweenAndBySeverity(Pageable p, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.dateMade >= :begin and f.dateMade <= :end and f.severity <= :minSeverity")
//	List<Falsehood> getConfirmedFalsehoodsBetweenAndByMinSeverity(Pageable p, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("minSeverity") byte minSeverity);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.dateMade >= :begin and f.dateMade <= :end and f.severity >= :maxSeverity")
//	List<Falsehood> getConfirmedFalsehoodsBetweenAndByMaxSeverity(Pageable p, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.dateMade >= :begin and f.dateMade <= :end and f.severity >= :maxSeverity and f.severity <= :minSeverity")
//	List<Falsehood> getConfirmedFalsehoodsBeforeAndBySeverity(Pageable p, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.dateMade <= :end and f.severity <= :minSeverity")
//	List<Falsehood> getConfirmedFalsehoodsBeforeAndByMinSeverity(Pageable p, @Param("end") Date end
//			, @Param("minSeverity") byte minSeverity);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.dateMade <= :end and f.severity >= :maxSeverity")
//	List<Falsehood> getConfirmedFalsehoodsBeforeAndByMaxSeverity(Pageable p, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.dateMade >= :begin and f.dateMade <= :end and (f.author1 = :author or f.author2 = :author) and f.severity >= :maxSeverity and f.severity <= :minSeverity")
//	List<Falsehood> getConfirmedFalsehoodsBetweenAndBySeverityPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.dateMade >= :begin and f.dateMade <= :end and (f.author1 = :author or f.author2 = :author) and f.severity <= :minSeverity")
//	List<Falsehood> getConfirmedFalsehoodsBetweenAndByMinSeverityPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("minSeverity") byte minSeverity);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.dateMade >= :begin and f.dateMade <= :end and (f.author1 = :author or f.author2 = :author) and f.severity >= :maxSeverity")
//	List<Falsehood> getConfirmedFalsehoodsBetweenAndByMaxSeverityPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.dateMade >= :begin and f.dateMade <= :end and (f.author1 = :author or f.author2 = :author) and f.severity >= :maxSeverity and f.severity <= :minSeverity")
//	List<Falsehood> getConfirmedFalsehoodsBeforeAndBySeverityPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.dateMade <= :end and f.severity <= :minSeverity and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getConfirmedFalsehoodsBeforeAndByMinSeverityPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("end") Date end
//			, @Param("minSeverity") byte minSeverity);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.dateMade <= :end and f.severity >= :maxSeverity and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getConfirmedFalsehoodsBeforeAndByMaxSeverityPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.dateMade >= :begin and f.dateMade <= :end and f.outlet = :outletId and f.severity >= :maxSeverity and f.severity <= :minSeverity")
//	List<Falsehood> getConfirmedFalsehoodsBetweenAndBySeverityOutlet(Pageable p, @Param("outletId") MediaOutlet outletId, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.dateMade >= :begin and f.dateMade <= :end and f.outlet = :outletId and f.severity <= :minSeverity")
//	List<Falsehood> getConfirmedFalsehoodsBetweenAndByMinSeverityOutlet(Pageable p, @Param("outletId") MediaOutlet outletId, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("minSeverity") byte minSeverity);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.dateMade >= :begin and f.dateMade <= :end and f.outlet = :outletId and f.severity >= :maxSeverity")
//	List<Falsehood> getConfirmedFalsehoodsBetweenAndByMaxSeverityOutlet(Pageable p, @Param("outletId") MediaOutlet outletId, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.dateMade <= :end and f.outlet = :outletId and f.severity >= :maxSeverity and f.severity <= :minSeverity")
//	List<Falsehood> getConfirmedFalsehoodsBeforeAndBySeverityOutlet(Pageable p, @Param("outletId") MediaOutlet outletId, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.dateMade <= :end and f.severity <= :minSeverity and f.outlet = :outletId")
//	List<Falsehood> getConfirmedFalsehoodsBeforeAndByMinSeverityOutlet(Pageable p, @Param("outletId") MediaOutlet outletId, @Param("end") Date end
//			, @Param("minSeverity") byte minSeverity);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.dateMade <= :end and f.severity >= :maxSeverity and f.outlet = :outletId")
//	List<Falsehood> getConfirmedFalsehoodsBeforeAndByMaxSeverityOutlet(Pageable p, @Param("outletId") MediaOutlet outletId, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.dateMade >= :begin and f.dateMade <= :end and f.outlet = :outletId and f.severity >= :maxSeverity and f.severity <= :minSeverity and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getConfirmedFalsehoodsBetweenAndBySeverityOutletPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("outletId") MediaOutlet outletId, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.dateMade >= :begin and f.dateMade <= :end and f.outlet = :outletId and f.severity <= :minSeverity and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getConfirmedFalsehoodsBetweenAndByMinSeverityOutletPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("outletId") MediaOutlet outletId, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("minSeverity") byte minSeverity);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.dateMade >= :begin and f.dateMade <= :end and f.outlet = :outletId and f.severity >= :maxSeverity and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getConfirmedFalsehoodsBetweenAndByMaxSeverityOutletPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("outletId") MediaOutlet outletId, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.dateMade <= :end and f.outlet = :outletId and f.severity >= :maxSeverity and f.severity <= :minSeverity and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getConfirmedFalsehoodsBeforeAndBySeverityOutletPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("outletId") MediaOutlet outletId, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.dateMade <= :end and f.severity <= :minSeverity and f.outlet = :outletId and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getConfirmedFalsehoodsBeforeAndByMinSeverityOutletPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("outletId") MediaOutlet outletId, @Param("end") Date end
//			, @Param("minSeverity") byte minSeverity);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.dateMade <= :end and f.severity >= :maxSeverity and f.outlet = :outletId and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getConfirmedFalsehoodsBeforeAndByMaxSeverityOutletPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("outletId") MediaOutlet outletId, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.dateMade >= :begin and f.dateMade <= :end and f.outlet = :outletId and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getConfirmedFalsehoodsBetweenAndByOutletPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("outletId") MediaOutlet outletId, @Param("begin") Date begin, @Param("end") Date end);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.dateMade <= :end and f.outlet = :outletId  and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getConfirmedFalsehoodsBeforeAndByOutletPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("outletId") MediaOutlet outletId, @Param("end") Date end);
//
//	//// Queries where Falsehoods are considered Rejected
//
//	@Query("select f from Falsehood f where f.status > 4")
//	List<Falsehood> getRejectedFalsehoods(Pageable p);
//
//	@Query("select f from Falsehood f where f.status > 4 and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getRejectedFalsehoodsByPublicOfficial(Pageable p, @Param("author")PublicFigure author);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.severity >= :maxSeverity and f.severity <= :minSeverity")
//	List<Falsehood> getRejectedFalsehoodsBySeverity(Pageable p, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.severity <= :minSeverity")
//	List<Falsehood> getRejectedFalsehoodByMinSeverity(Pageable p, @Param("minSeverity")byte minSeverity);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.severity >= :maxSeverity")
//	List<Falsehood> getRejectedFalsehoodByMaxSeverity(Pageable p, @Param("maxSeverity") byte maxSeverity);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.dateMade >= :begin and f.dateMade <= :end")
//	List<Falsehood> getRejectedFalsehoodsBetween(Pageable p, @Param("begin") Date begin, @Param("end") Date end);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.dateMade <= :end")
//	List<Falsehood> getRejectedFalsehoodsBefore(Pageable p, @Param("end") Date end);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.outlet = :outletId")
//	List<Falsehood> getRejectedFalsehoodsByOutlet(Pageable p, @Param("outletId") MediaOutlet outletId);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.outlet = :outletId and f.severity >= :maxSeverity and f.severity <= :minSeverity")
//	List<Falsehood> getRejectedFalsehoodsByOutletAndSeverity(Pageable p, @Param("outletId") MediaOutlet outletId,
//			@Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.outlet = :outletId and f.severity <= :minSeverity")
//	List<Falsehood> getRejectedFalsehoodsByOutletAndMinSeverity(Pageable p, @Param("outletId") MediaOutlet outletId, @Param("minSeverity") byte minSeverity);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.outlet = :outletId and f.severity >= :maxSeverity")
//	List<Falsehood> getRejectedFalsehoodsByOutletAndMaxSeverity(Pageable p, @Param("outletId") MediaOutlet outletId, @Param("maxSeverity") byte maxSeverity);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.outlet = :outletId and f.dateMade >= :begin and f.dateMade <= :end")
//	List<Falsehood> getRejectedFalsehoodsBetweenAndByOutlet(Pageable p, @Param("outletId") MediaOutlet outletId, @Param("begin") Date begin, @Param("end") Date end);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.outlet = :outletId and f.dateMade <= :end")
//	List<Falsehood> getRejectedFalsehoodsBeforeAndByOutlet(Pageable p, @Param("outletId") MediaOutlet outletId, @Param("end") Date end);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.outlet = :outletId and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getRejectedFalsehoodsByPublicOfficialAndOutlet(Pageable p, @Param("author")PublicFigure author, @Param("outletId") MediaOutlet outletId);
//
//	@Query("select f from Falsehood f where f.status > 4 and (f.author1 = :author or f.author2 = :author) and f.severity >= :maxSeverity and f.severity <= :minSeverity")
//	List<Falsehood> getRejectedFalsehoodsByPublicOfficialAndSeverity(Pageable p, @Param("author")PublicFigure author, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity);
//
//	@Query("select f from Falsehood f where f.status > 4 and (f.author1 = :author or f.author2 = :author) and f.severity <= :minSeverity")
//	List<Falsehood> getRejectedFalsehoodsByPublicOfficialAndMinSeverity(Pageable p, @Param("author")PublicFigure author, @Param("minSeverity") byte minSeverity);
//
//	@Query("select f from Falsehood f where f.status > 4 and (f.author1 = :author or f.author2 = :author) and f.severity >= :maxSeverity")
//	List<Falsehood> getRejectedFalsehoodsByPublicOfficialAndMaxSeverity(Pageable p, @Param("author")PublicFigure author, @Param("maxSeverity") byte maxSeverity);
//
//	@Query("select f from Falsehood f where f.status > 4 and (f.author1 = :author or f.author2 = :author) and f.dateMade >= :begin and f.dateMade <= :end")
//	List<Falsehood> getRejectedFalsehoodsBetweenAndByPublicOfficial(Pageable p, @Param("author")PublicFigure author, @Param("begin") Date begin, @Param("end") Date end);
//
//	@Query("select f from Falsehood f where f.status > 4 and (f.author1 = :author or f.author2 = :author) and f.dateMade <= :end")
//	List<Falsehood> getRejectedFalsehoodsBeforeAndByPublicOfficial(Pageable p, @Param("author")PublicFigure author, @Param("end") Date end);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.outlet = :outletId and (f.author1 = :author or f.author2 = :author) and f.severity >= :maxSeverity and f.severity <= :minSeverity")
//	List<Falsehood> getRejectedFalsehoodsByPublicOfficialOutletAndSeverity(Pageable p, @Param("author")PublicFigure author,
//			@Param("outletId") MediaOutlet outletId,@Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.outlet = :outletId and (f.author1 = :author or f.author2 = :author) and f.severity <= :minSeverity")
//	List<Falsehood> getRejectedFalsehoodsByPublicOfficialOutletAndMinSeverity(Pageable p, @Param("author")PublicFigure author,
//			@Param("outletId") MediaOutlet outletId, @Param("minSeverity") byte minSeverity);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.outlet = :outletId and (f.author1 = :author or f.author2 = :author) and f.severity >= :maxSeverity")
//	List<Falsehood> getRejectedFalsehoodsByPublicOfficialOutletAndMaxSeverity(Pageable p, @Param("author")PublicFigure author,
//			@Param("outletId") MediaOutlet outletId,@Param("maxSeverity") byte maxSeverity);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.dateMade >= :begin and f.dateMade <= :end and f.severity >= :maxSeverity and f.severity <= :minSeverity")
//	List<Falsehood> getRejectedFalsehoodsBeforeAndBySeverity(Pageable p, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.dateMade <= :end and f.severity <= :minSeverity")
//	List<Falsehood> getRejectedFalsehoodsBeforeAndByMinSeverity(Pageable p, @Param("end") Date end
//			, @Param("minSeverity") byte minSeverity);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.dateMade <= :end and f.severity >= :maxSeverity")
//	List<Falsehood> getRejectedFalsehoodsBeforeAndByMaxSeverity(Pageable p, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.dateMade >= :begin and f.dateMade <= :end and (f.author1 = :author or f.author2 = :author) and f.severity >= :maxSeverity and f.severity <= :minSeverity")
//	List<Falsehood> getRejectedFalsehoodsBetweenAndBySeverityPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.dateMade >= :begin and f.dateMade <= :end and (f.author1 = :author or f.author2 = :author) and f.severity <= :minSeverity")
//	List<Falsehood> getRejectedFalsehoodsBetweenAndByMinSeverityPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("minSeverity") byte minSeverity);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.dateMade >= :begin and f.dateMade <= :end and (f.author1 = :author or f.author2 = :author) and f.severity >= :maxSeverity")
//	List<Falsehood> getRejectedFalsehoodsBetweenAndByMaxSeverityPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.dateMade >= :begin and f.dateMade <= :end and (f.author1 = :author or f.author2 = :author) and f.severity >= :maxSeverity and f.severity <= :minSeverity")
//	List<Falsehood> getRejectedFalsehoodsBeforeAndBySeverityPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.dateMade <= :end and f.severity <= :minSeverity and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getRejectedFalsehoodsBeforeAndByMinSeverityPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("end") Date end
//			, @Param("minSeverity") byte minSeverity);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.dateMade <= :end and f.severity >= :maxSeverity and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getRejectedFalsehoodsBeforeAndByMaxSeverityPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.dateMade >= :begin and f.dateMade <= :end and f.outlet = :outletId and f.severity >= :maxSeverity and f.severity <= :minSeverity")
//	List<Falsehood> getRejectedFalsehoodsBetweenAndBySeverityOutlet(Pageable p, @Param("outletId") MediaOutlet outletId, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.dateMade >= :begin and f.dateMade <= :end and f.outlet = :outletId and f.severity <= :minSeverity")
//	List<Falsehood> getRejectedFalsehoodsBetweenAndByMinSeverityOutlet(Pageable p, @Param("outletId") MediaOutlet outletId, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("minSeverity") byte minSeverity);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.dateMade >= :begin and f.dateMade <= :end and f.outlet = :outletId and f.severity >= :maxSeverity")
//	List<Falsehood> getRejectedFalsehoodsBetweenAndByMaxSeverityOutlet(Pageable p, @Param("outletId") MediaOutlet outletId, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.dateMade <= :end and f.outlet = :outletId and f.severity >= :maxSeverity and f.severity <= :minSeverity")
//	List<Falsehood> getRejectedFalsehoodsBeforeAndBySeverityOutlet(Pageable p, @Param("outletId") MediaOutlet outletId, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.dateMade <= :end and f.severity <= :minSeverity and f.outlet = :outletId")
//	List<Falsehood> getRejectedFalsehoodsBeforeAndByMinSeverityOutlet(Pageable p, @Param("outletId") MediaOutlet outletId, @Param("end") Date end
//			, @Param("minSeverity") byte minSeverity);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.dateMade <= :end and f.severity >= :maxSeverity and f.outlet = :outletId")
//	List<Falsehood> getRejectedFalsehoodsBeforeAndByMaxSeverityOutlet(Pageable p, @Param("outletId") MediaOutlet outletId, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.dateMade >= :begin and f.dateMade <= :end and f.outlet = :outletId and f.severity >= :maxSeverity and f.severity <= :minSeverity and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getRejectedFalsehoodsBetweenAndBySeverityOutletPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("outletId") MediaOutlet outletId, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.dateMade >= :begin and f.dateMade <= :end and f.outlet = :outletId and f.severity <= :minSeverity and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getRejectedFalsehoodsBetweenAndByMinSeverityOutletPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("outletId") MediaOutlet outletId, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("minSeverity") byte minSeverity);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.dateMade >= :begin and f.dateMade <= :end and f.outlet = :outletId and f.severity >= :maxSeverity and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getRejectedFalsehoodsBetweenAndByMaxSeverityOutletPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("outletId") MediaOutlet outletId, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.dateMade <= :end and f.outlet = :outletId and f.severity >= :maxSeverity and f.severity <= :minSeverity and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getRejectedFalsehoodsBeforeAndBySeverityOutletPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("outletId") MediaOutlet outletId, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.dateMade <= :end and f.severity <= :minSeverity and f.outlet = :outletId and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getRejectedFalsehoodsBeforeAndByMinSeverityOutletPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("outletId") MediaOutlet outletId, @Param("end") Date end
//			, @Param("minSeverity") byte minSeverity);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.dateMade <= :end and f.severity >= :maxSeverity and f.outlet = :outletId and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getRejectedFalsehoodsBeforeAndByMaxSeverityOutletPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("outletId") MediaOutlet outletId, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.dateMade >= :begin and f.dateMade <= :end and f.outlet = :outletId and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getRejectedFalsehoodsBetweenAndByOutletPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("outletId") MediaOutlet outletId, @Param("begin") Date begin, @Param("end") Date end);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.dateMade <= :end and f.outlet = :outletId  and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getRejectedFalsehoodsBeforeAndByOutletPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("outletId") MediaOutlet outletId, @Param("end") Date end);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.dateMade >= :begin and f.dateMade <= :end and f.severity >= :maxSeverity and f.severity <= :minSeverity")
//	List<Falsehood> getRejectedFalsehoodsBetweenAndBySeverity(Pageable p, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.dateMade >= :begin and f.dateMade <= :end and f.severity <= :minSeverity")
//	List<Falsehood> getRejectedFalsehoodsBetweenAndByMinSeverity(Pageable p, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("minSeverity") byte minSeverity);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.dateMade >= :begin and f.dateMade <= :end and f.severity >= :maxSeverity")
//	List<Falsehood> getRejectedFalsehoodsBetweenAndByMaxSeverity(Pageable p, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity);
//
//
//
//
//	//// Now Adding Search Terms to the List
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms%")
//	List<Falsehood> getConfirmedFalsehoods(Pageable p, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms% and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getConfirmedFalsehoodsByPublicOfficial(Pageable p, @Param("author")PublicFigure author, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms% and f.severity >= :maxSeverity and f.severity <= :minSeverity")
//	List<Falsehood> getConfirmedFalsehoodsBySeverity(Pageable p, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms% and f.severity <= :minSeverity")
//	List<Falsehood> getConfirmedFalsehoodByMinSeverity(Pageable p, @Param("minSeverity")byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms% and f.severity >= :maxSeverity")
//	List<Falsehood> getConfirmedFalsehoodByMaxSeverity(Pageable p, @Param("maxSeverity") byte maxSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms% and f.dateMade >= :begin and f.dateMade <= :end")
//	List<Falsehood> getConfirmedFalsehoodsBetween(Pageable p, @Param("begin") Date begin, @Param("end") Date end, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms% and f.dateMade <= :end")
//	List<Falsehood> getConfirmedFalsehoodsBefore(Pageable p, @Param("end") Date end, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms% and f.outlet = :outletId")
//	List<Falsehood> getConfirmedFalsehoodsByOutlet(Pageable p, @Param("outletId") MediaOutlet outletId, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms% and f.outlet = :outletId and f.severity >= :maxSeverity and f.severity <= :minSeverity")
//	List<Falsehood> getConfirmedFalsehoodsByOutletAndSeverity(Pageable p, @Param("outletId") MediaOutlet outletId,
//			@Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms% and f.outlet = :outletId and f.severity <= :minSeverity")
//	List<Falsehood> getConfirmedFalsehoodsByOutletAndMinSeverity(Pageable p, @Param("outletId") MediaOutlet outletId, @Param("minSeverity") byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms% and f.outlet = :outletId and f.severity >= :maxSeverity")
//	List<Falsehood> getConfirmedFalsehoodsByOutletAndMaxSeverity(Pageable p, @Param("outletId") MediaOutlet outletId, @Param("maxSeverity") byte maxSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms% and f.outlet = :outletId and f.dateMade >= :begin and f.dateMade <= :end")
//	List<Falsehood> getConfirmedFalsehoodsBetweenAndByOutlet(Pageable p, @Param("outletId") MediaOutlet outletId, @Param("begin") Date begin, @Param("end") Date end, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms% and f.outlet = :outletId and f.dateMade <= :end")
//	List<Falsehood> getConfirmedFalsehoodsBeforeAndByOutlet(Pageable p, @Param("outletId") MediaOutlet outletId, @Param("end") Date end, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms% and f.outlet = :outletId and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getConfirmedFalsehoodsByPublicOfficialAndOutlet(Pageable p, @Param("author")PublicFigure author, @Param("outletId") MediaOutlet outletId, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms% and (f.author1 = :author or f.author2 = :author) and f.severity >= :maxSeverity and f.severity <= :minSeverity")
//	List<Falsehood> getConfirmedFalsehoodsByPublicOfficialAndSeverity(Pageable p, @Param("author")PublicFigure author, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms% and (f.author1 = :author or f.author2 = :author) and f.severity <= :minSeverity")
//	List<Falsehood> getConfirmedFalsehoodsByPublicOfficialAndMinSeverity(Pageable p, @Param("author")PublicFigure author, @Param("minSeverity") byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms% and (f.author1 = :author or f.author2 = :author) and f.severity >= :maxSeverity")
//	List<Falsehood> getConfirmedFalsehoodsByPublicOfficialAndMaxSeverity(Pageable p, @Param("author")PublicFigure author, @Param("maxSeverity") byte maxSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms% and (f.author1 = :author or f.author2 = :author) and f.dateMade >= :begin and f.dateMade <= :end")
//	List<Falsehood> getConfirmedFalsehoodsBetweenAndByPublicOfficial(Pageable p, @Param("author")PublicFigure author, @Param("begin") Date begin, @Param("end") Date end, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms% and (f.author1 = :author or f.author2 = :author) and f.dateMade <= :end")
//	List<Falsehood> getConfirmedFalsehoodsBeforeAndByPublicOfficial(Pageable p, @Param("author")PublicFigure author, @Param("end") Date end, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms% and f.outlet = :outletId and (f.author1 = :author or f.author2 = :author) and f.severity >= :maxSeverity and f.severity <= :minSeverity")
//	List<Falsehood> getConfirmedFalsehoodsByPublicOfficialOutletAndSeverity(Pageable p, @Param("author")PublicFigure author,
//			@Param("outletId") MediaOutlet outletId,@Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms% and f.outlet = :outletId and (f.author1 = :author or f.author2 = :author) and f.severity <= :minSeverity")
//	List<Falsehood> getConfirmedFalsehoodsByPublicOfficialOutletAndMinSeverity(Pageable p, @Param("author")PublicFigure author,
//			@Param("outletId") MediaOutlet outletId, @Param("minSeverity") byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms% and f.outlet = :outletId and (f.author1 = :author or f.author2 = :author) and f.severity >= :maxSeverity")
//	List<Falsehood> getConfirmedFalsehoodsByPublicOfficialOutletAndMaxSeverity(Pageable p, @Param("author")PublicFigure author,
//			@Param("outletId") MediaOutlet outletId,@Param("maxSeverity") byte maxSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms% and f.dateMade >= :begin and f.dateMade <= :end and f.severity >= :maxSeverity and f.severity <= :minSeverity")
//	List<Falsehood> getConfirmedFalsehoodsBetweenAndBySeverity(Pageable p, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms% and f.dateMade >= :begin and f.dateMade <= :end and f.severity <= :minSeverity")
//	List<Falsehood> getConfirmedFalsehoodsBetweenAndByMinSeverity(Pageable p, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("minSeverity") byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms% and f.dateMade >= :begin and f.dateMade <= :end and f.severity >= :maxSeverity")
//	List<Falsehood> getConfirmedFalsehoodsBetweenAndByMaxSeverity(Pageable p, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms% and f.dateMade >= :begin and f.dateMade <= :end and f.severity >= :maxSeverity and f.severity <= :minSeverity")
//	List<Falsehood> getConfirmedFalsehoodsBeforeAndBySeverity(Pageable p, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms% and f.dateMade <= :end and f.severity <= :minSeverity")
//	List<Falsehood> getConfirmedFalsehoodsBeforeAndByMinSeverity(Pageable p, @Param("end") Date end
//			, @Param("minSeverity") byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms% and f.dateMade <= :end and f.severity >= :maxSeverity")
//	List<Falsehood> getConfirmedFalsehoodsBeforeAndByMaxSeverity(Pageable p, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms% and f.dateMade >= :begin and f.dateMade <= :end and (f.author1 = :author or f.author2 = :author) and f.severity >= :maxSeverity and f.severity <= :minSeverity")
//	List<Falsehood> getConfirmedFalsehoodsBetweenAndBySeverityPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms% and f.dateMade >= :begin and f.dateMade <= :end and (f.author1 = :author or f.author2 = :author) and f.severity <= :minSeverity")
//	List<Falsehood> getConfirmedFalsehoodsBetweenAndByMinSeverityPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("minSeverity") byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms% and f.dateMade >= :begin and f.dateMade <= :end and (f.author1 = :author or f.author2 = :author) and f.severity >= :maxSeverity")
//	List<Falsehood> getConfirmedFalsehoodsBetweenAndByMaxSeverityPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms% and f.dateMade >= :begin and f.dateMade <= :end and (f.author1 = :author or f.author2 = :author) and f.severity >= :maxSeverity and f.severity <= :minSeverity")
//	List<Falsehood> getConfirmedFalsehoodsBeforeAndBySeverityPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms% and f.dateMade <= :end and f.severity <= :minSeverity and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getConfirmedFalsehoodsBeforeAndByMinSeverityPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("end") Date end
//			, @Param("minSeverity") byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms% and f.dateMade <= :end and f.severity >= :maxSeverity and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getConfirmedFalsehoodsBeforeAndByMaxSeverityPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms% and f.dateMade >= :begin and f.dateMade <= :end and f.outlet = :outletId and f.severity >= :maxSeverity and f.severity <= :minSeverity")
//	List<Falsehood> getConfirmedFalsehoodsBetweenAndBySeverityOutlet(Pageable p, @Param("outletId") MediaOutlet outletId, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms% and f.dateMade >= :begin and f.dateMade <= :end and f.outlet = :outletId and f.severity <= :minSeverity")
//	List<Falsehood> getConfirmedFalsehoodsBetweenAndByMinSeverityOutlet(Pageable p, @Param("outletId") MediaOutlet outletId, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("minSeverity") byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms% and f.dateMade >= :begin and f.dateMade <= :end and f.outlet = :outletId and f.severity >= :maxSeverity")
//	List<Falsehood> getConfirmedFalsehoodsBetweenAndByMaxSeverityOutlet(Pageable p, @Param("outletId") MediaOutlet outletId, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms% and f.dateMade <= :end and f.outlet = :outletId and f.severity >= :maxSeverity and f.severity <= :minSeverity")
//	List<Falsehood> getConfirmedFalsehoodsBeforeAndBySeverityOutlet(Pageable p, @Param("outletId") MediaOutlet outletId, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms% and f.dateMade <= :end and f.severity <= :minSeverity and f.outlet = :outletId")
//	List<Falsehood> getConfirmedFalsehoodsBeforeAndByMinSeverityOutlet(Pageable p, @Param("outletId") MediaOutlet outletId, @Param("end") Date end
//			, @Param("minSeverity") byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms% and f.dateMade <= :end and f.severity >= :maxSeverity and f.outlet = :outletId")
//	List<Falsehood> getConfirmedFalsehoodsBeforeAndByMaxSeverityOutlet(Pageable p, @Param("outletId") MediaOutlet outletId, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms% and f.dateMade >= :begin and f.dateMade <= :end and f.outlet = :outletId and f.severity >= :maxSeverity and f.severity <= :minSeverity and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getConfirmedFalsehoodsBetweenAndBySeverityOutletPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("outletId") MediaOutlet outletId, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms% and f.dateMade >= :begin and f.dateMade <= :end and f.outlet = :outletId and f.severity <= :minSeverity and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getConfirmedFalsehoodsBetweenAndByMinSeverityOutletPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("outletId") MediaOutlet outletId, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("minSeverity") byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms% and f.dateMade >= :begin and f.dateMade <= :end and f.outlet = :outletId and f.severity >= :maxSeverity and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getConfirmedFalsehoodsBetweenAndByMaxSeverityOutletPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("outletId") MediaOutlet outletId, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms% and f.dateMade <= :end and f.outlet = :outletId and f.severity >= :maxSeverity and f.severity <= :minSeverity and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getConfirmedFalsehoodsBeforeAndBySeverityOutletPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("outletId") MediaOutlet outletId, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms% and f.dateMade <= :end and f.severity <= :minSeverity and f.outlet = :outletId and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getConfirmedFalsehoodsBeforeAndByMinSeverityOutletPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("outletId") MediaOutlet outletId, @Param("end") Date end
//			, @Param("minSeverity") byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms% and f.dateMade <= :end and f.severity >= :maxSeverity and f.outlet = :outletId and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getConfirmedFalsehoodsBeforeAndByMaxSeverityOutletPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("outletId") MediaOutlet outletId, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms% and f.dateMade >= :begin and f.dateMade <= :end and f.outlet = :outletId and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getConfirmedFalsehoodsBetweenAndByOutletPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("outletId") MediaOutlet outletId, @Param("begin") Date begin, @Param("end") Date end, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where ((f.status > 1 and f.status < 5) or f.status > 11) and f.tags like %:terms% and f.dateMade <= :end and f.outlet = :outletId  and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getConfirmedFalsehoodsBeforeAndByOutletPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("outletId") MediaOutlet outletId, @Param("end") Date end, @Param("terms")String terms);
//
//	//// Queries where Falsehoods are considered Rejected
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms%")
//	List<Falsehood> getRejectedFalsehoods(Pageable p, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms% and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getRejectedFalsehoodsByPublicOfficial(Pageable p, @Param("author")PublicFigure author, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms% and f.severity >= :maxSeverity and f.severity <= :minSeverity")
//	List<Falsehood> getRejectedFalsehoodsBySeverity(Pageable p, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms% and f.severity <= :minSeverity")
//	List<Falsehood> getRejectedFalsehoodByMinSeverity(Pageable p, @Param("minSeverity")byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms% and f.severity >= :maxSeverity")
//	List<Falsehood> getRejectedFalsehoodByMaxSeverity(Pageable p, @Param("maxSeverity") byte maxSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms% and f.dateMade >= :begin and f.dateMade <= :end")
//	List<Falsehood> getRejectedFalsehoodsBetween(Pageable p, @Param("begin") Date begin, @Param("end") Date end, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms% and f.dateMade <= :end")
//	List<Falsehood> getRejectedFalsehoodsBefore(Pageable p, @Param("end") Date end, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms% and f.outlet = :outletId")
//	List<Falsehood> getRejectedFalsehoodsByOutlet(Pageable p, @Param("outletId") MediaOutlet outletId, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms% and f.outlet = :outletId and f.severity >= :maxSeverity and f.severity <= :minSeverity")
//	List<Falsehood> getRejectedFalsehoodsByOutletAndSeverity(Pageable p, @Param("outletId") MediaOutlet outletId,
//			@Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms% and f.outlet = :outletId and f.severity <= :minSeverity")
//	List<Falsehood> getRejectedFalsehoodsByOutletAndMinSeverity(Pageable p, @Param("outletId") MediaOutlet outletId, @Param("minSeverity") byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms% and f.outlet = :outletId and f.severity >= :maxSeverity")
//	List<Falsehood> getRejectedFalsehoodsByOutletAndMaxSeverity(Pageable p, @Param("outletId") MediaOutlet outletId, @Param("maxSeverity") byte maxSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms% and f.outlet = :outletId and f.dateMade >= :begin and f.dateMade <= :end")
//	List<Falsehood> getRejectedFalsehoodsBetweenAndByOutlet(Pageable p, @Param("outletId") MediaOutlet outletId, @Param("begin") Date begin, @Param("end") Date end, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms% and f.outlet = :outletId and f.dateMade <= :end")
//	List<Falsehood> getRejectedFalsehoodsBeforeAndByOutlet(Pageable p, @Param("outletId") MediaOutlet outletId, @Param("end") Date end, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms% and f.outlet = :outletId and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getRejectedFalsehoodsByPublicOfficialAndOutlet(Pageable p, @Param("author")PublicFigure author, @Param("outletId") MediaOutlet outletId, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms% and (f.author1 = :author or f.author2 = :author) and f.severity >= :maxSeverity and f.severity <= :minSeverity")
//	List<Falsehood> getRejectedFalsehoodsByPublicOfficialAndSeverity(Pageable p, @Param("author")PublicFigure author, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms% and (f.author1 = :author or f.author2 = :author) and f.severity <= :minSeverity")
//	List<Falsehood> getRejectedFalsehoodsByPublicOfficialAndMinSeverity(Pageable p, @Param("author")PublicFigure author, @Param("minSeverity") byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms% and (f.author1 = :author or f.author2 = :author) and f.severity >= :maxSeverity")
//	List<Falsehood> getRejectedFalsehoodsByPublicOfficialAndMaxSeverity(Pageable p, @Param("author")PublicFigure author, @Param("maxSeverity") byte maxSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms% and (f.author1 = :author or f.author2 = :author) and f.dateMade >= :begin and f.dateMade <= :end")
//	List<Falsehood> getRejectedFalsehoodsBetweenAndByPublicOfficial(Pageable p, @Param("author")PublicFigure author, @Param("begin") Date begin, @Param("end") Date end, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms% and (f.author1 = :author or f.author2 = :author) and f.dateMade <= :end")
//	List<Falsehood> getRejectedFalsehoodsBeforeAndByPublicOfficial(Pageable p, @Param("author")PublicFigure author, @Param("end") Date end, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms% and f.outlet = :outletId and (f.author1 = :author or f.author2 = :author) and f.severity >= :maxSeverity and f.severity <= :minSeverity")
//	List<Falsehood> getRejectedFalsehoodsByPublicOfficialOutletAndSeverity(Pageable p, @Param("author")PublicFigure author,
//			@Param("outletId") MediaOutlet outletId,@Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms% and f.outlet = :outletId and (f.author1 = :author or f.author2 = :author) and f.severity <= :minSeverity")
//	List<Falsehood> getRejectedFalsehoodsByPublicOfficialOutletAndMinSeverity(Pageable p, @Param("author")PublicFigure author,
//			@Param("outletId") MediaOutlet outletId, @Param("minSeverity") byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms% and f.outlet = :outletId and (f.author1 = :author or f.author2 = :author) and f.severity >= :maxSeverity")
//	List<Falsehood> getRejectedFalsehoodsByPublicOfficialOutletAndMaxSeverity(Pageable p, @Param("author")PublicFigure author,
//			@Param("outletId") MediaOutlet outletId,@Param("maxSeverity") byte maxSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms% and f.dateMade >= :begin and f.dateMade <= :end and f.severity >= :maxSeverity and f.severity <= :minSeverity")
//	List<Falsehood> getRejectedFalsehoodsBeforeAndBySeverity(Pageable p, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms% and f.dateMade <= :end and f.severity <= :minSeverity")
//	List<Falsehood> getRejectedFalsehoodsBeforeAndByMinSeverity(Pageable p, @Param("end") Date end
//			, @Param("minSeverity") byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms% and f.dateMade <= :end and f.severity >= :maxSeverity")
//	List<Falsehood> getRejectedFalsehoodsBeforeAndByMaxSeverity(Pageable p, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms% and f.dateMade >= :begin and f.dateMade <= :end and (f.author1 = :author or f.author2 = :author) and f.severity >= :maxSeverity and f.severity <= :minSeverity")
//	List<Falsehood> getRejectedFalsehoodsBetweenAndBySeverityPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms% and f.dateMade >= :begin and f.dateMade <= :end and (f.author1 = :author or f.author2 = :author) and f.severity <= :minSeverity")
//	List<Falsehood> getRejectedFalsehoodsBetweenAndByMinSeverityPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("minSeverity") byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms% and f.dateMade >= :begin and f.dateMade <= :end and (f.author1 = :author or f.author2 = :author) and f.severity >= :maxSeverity")
//	List<Falsehood> getRejectedFalsehoodsBetweenAndByMaxSeverityPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms% and f.dateMade >= :begin and f.dateMade <= :end and (f.author1 = :author or f.author2 = :author) and f.severity >= :maxSeverity and f.severity <= :minSeverity")
//	List<Falsehood> getRejectedFalsehoodsBeforeAndBySeverityPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms% and f.dateMade <= :end and f.severity <= :minSeverity and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getRejectedFalsehoodsBeforeAndByMinSeverityPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("end") Date end
//			, @Param("minSeverity") byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms% and f.dateMade <= :end and f.severity >= :maxSeverity and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getRejectedFalsehoodsBeforeAndByMaxSeverityPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms% and f.dateMade >= :begin and f.dateMade <= :end and f.outlet = :outletId and f.severity >= :maxSeverity and f.severity <= :minSeverity")
//	List<Falsehood> getRejectedFalsehoodsBetweenAndBySeverityOutlet(Pageable p, @Param("outletId") MediaOutlet outletId, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms% and f.dateMade >= :begin and f.dateMade <= :end and f.outlet = :outletId and f.severity <= :minSeverity")
//	List<Falsehood> getRejectedFalsehoodsBetweenAndByMinSeverityOutlet(Pageable p, @Param("outletId") MediaOutlet outletId, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("minSeverity") byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms% and f.dateMade >= :begin and f.dateMade <= :end and f.outlet = :outletId and f.severity >= :maxSeverity")
//	List<Falsehood> getRejectedFalsehoodsBetweenAndByMaxSeverityOutlet(Pageable p, @Param("outletId") MediaOutlet outletId, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms% and f.dateMade <= :end and f.outlet = :outletId and f.severity >= :maxSeverity and f.severity <= :minSeverity")
//	List<Falsehood> getRejectedFalsehoodsBeforeAndBySeverityOutlet(Pageable p, @Param("outletId") MediaOutlet outletId, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms% and f.dateMade <= :end and f.severity <= :minSeverity and f.outlet = :outletId")
//	List<Falsehood> getRejectedFalsehoodsBeforeAndByMinSeverityOutlet(Pageable p, @Param("outletId") MediaOutlet outletId, @Param("end") Date end
//			, @Param("minSeverity") byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms% and f.dateMade <= :end and f.severity >= :maxSeverity and f.outlet = :outletId")
//	List<Falsehood> getRejectedFalsehoodsBeforeAndByMaxSeverityOutlet(Pageable p, @Param("outletId") MediaOutlet outletId, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms% and f.dateMade >= :begin and f.dateMade <= :end and f.outlet = :outletId and f.severity >= :maxSeverity and f.severity <= :minSeverity and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getRejectedFalsehoodsBetweenAndBySeverityOutletPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("outletId") MediaOutlet outletId, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms% and f.dateMade >= :begin and f.dateMade <= :end and f.outlet = :outletId and f.severity <= :minSeverity and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getRejectedFalsehoodsBetweenAndByMinSeverityOutletPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("outletId") MediaOutlet outletId, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("minSeverity") byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms% and f.dateMade >= :begin and f.dateMade <= :end and f.outlet = :outletId and f.severity >= :maxSeverity and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getRejectedFalsehoodsBetweenAndByMaxSeverityOutletPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("outletId") MediaOutlet outletId, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms% and f.dateMade <= :end and f.outlet = :outletId and f.severity >= :maxSeverity and f.severity <= :minSeverity and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getRejectedFalsehoodsBeforeAndBySeverityOutletPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("outletId") MediaOutlet outletId, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms% and f.dateMade <= :end and f.severity <= :minSeverity and f.outlet = :outletId and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getRejectedFalsehoodsBeforeAndByMinSeverityOutletPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("outletId") MediaOutlet outletId, @Param("end") Date end
//			, @Param("minSeverity") byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms% and f.dateMade <= :end and f.severity >= :maxSeverity and f.outlet = :outletId and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getRejectedFalsehoodsBeforeAndByMaxSeverityOutletPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("outletId") MediaOutlet outletId, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms% and f.dateMade >= :begin and f.dateMade <= :end and f.outlet = :outletId and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getRejectedFalsehoodsBetweenAndByOutletPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("outletId") MediaOutlet outletId, @Param("begin") Date begin, @Param("end") Date end, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms% and f.dateMade <= :end and f.outlet = :outletId  and (f.author1 = :author or f.author2 = :author)")
//	List<Falsehood> getRejectedFalsehoodsBeforeAndByOutletPublicFigure(Pageable p, @Param("author") PublicFigure author, @Param("outletId") MediaOutlet outletId, @Param("end") Date end, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms% and f.dateMade >= :begin and f.dateMade <= :end and f.severity >= :maxSeverity and f.severity <= :minSeverity")
//	List<Falsehood> getRejectedFalsehoodsBetweenAndBySeverity(Pageable p, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("minSeverity") byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms% and f.dateMade >= :begin and f.dateMade <= :end and f.severity <= :minSeverity")
//	List<Falsehood> getRejectedFalsehoodsBetweenAndByMinSeverity(Pageable p, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("minSeverity") byte minSeverity, @Param("terms")String terms);
//
//	@Query("select f from Falsehood f where f.status > 4 and f.tags like %:terms% and f.dateMade >= :begin and f.dateMade <= :end and f.severity >= :maxSeverity")
//	List<Falsehood> getRejectedFalsehoodsBetweenAndByMaxSeverity(Pageable p, @Param("begin") Date begin, @Param("end") Date end
//			, @Param("maxSeverity") byte maxSeverity, @Param("terms")String terms);
//
//
}
