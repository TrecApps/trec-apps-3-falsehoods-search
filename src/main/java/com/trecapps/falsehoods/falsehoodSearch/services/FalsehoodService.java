package com.trecapps.falsehoods.falsehoodSearch.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.trecapps.base.FalsehoodModel.models.*;
import com.trecapps.falsehoods.falsehoodSearch.repos.FalsehoodRecordsRepo;
import com.trecapps.falsehoods.falsehoodSearch.repos.FalsehoodRepo;
import com.trecapps.falsehoods.falsehoodSearch.config.StorageClient;
import org.hibernate.query.criteria.internal.expression.ExpressionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Date;
import java.util.*;

@Service
public class FalsehoodService {
	public static final int MIN_CREDIT_APPROVE_REJECT = 60;

	FalsehoodRepo fRepo;

	StorageClient s3BucketManager;

	FalsehoodRecordsRepo recordsRepo;

	TagSupportService tagSupportService;

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	public FalsehoodService(@Autowired FalsehoodRepo fRepo,
							@Autowired StorageClient s3BucketManager,
							@Autowired FalsehoodRecordsRepo recordsRepo,
							@Autowired TagSupportService tagSupportService1)
	{
		this.s3BucketManager = s3BucketManager;
		this.fRepo = fRepo;
		this.recordsRepo = recordsRepo;
		this.tagSupportService = tagSupportService1;
	}

	public List<Falsehood> getFalsehoodList(SearchFalsehood s, int target)
	{
		Set<BigInteger> taggedIds = null;

		if(s.getTerms() != null && s.getTerms().trim().length() > 0)
		{
			List<String> terms = tagSupportService.getTerms(s.getTerms());

			TypedQuery<FalsehoodCommonTag> cQuery= tagSupportService.getQuery(terms, FalsehoodCommonTag.class);
			List<FalsehoodCommonTag> commonTags = cQuery.getResultList();

			TypedQuery<FalsehoodUncommonTag> uQuery= tagSupportService.getQuery(terms, FalsehoodCommonTag.class);
			List<FalsehoodUncommonTag> uncommonTags = uQuery.getResultList();

			if(commonTags.size() > 0 || uncommonTags.size() > 0)
				taggedIds = new TreeSet<>();

			for(FalsehoodCommonTag tag : commonTags)
			{
				taggedIds.add(tag.getFalsehood().getId());
			}
			for(FalsehoodUncommonTag tag: uncommonTags)
			{
				taggedIds.add(tag.getFalsehood().getId());
			}
		}

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Falsehood> criteria = criteriaBuilder.createQuery(Falsehood.class);
		Root<Falsehood> falsehoodRoot = criteria.from(Falsehood.class);

		List<Predicate> predicates = new ArrayList<>();

		if(s.getAuthor() != null)
			predicates.add(criteriaBuilder.equal(falsehoodRoot.get("author1"), s.getAuthor()));
		Date from = s.getFrom(), to = s.getTo();

		if(from != null && to != null)
			predicates.add(criteriaBuilder.between(falsehoodRoot.get("dateMade"), from, to));
		else if(from != null)
			predicates.add(criteriaBuilder.greaterThanOrEqualTo(falsehoodRoot.get("dateMade"), from));
		else if(to != null)
			predicates.add(criteriaBuilder.lessThanOrEqualTo(falsehoodRoot.get("dateMade"), to));

		if(s.getOutlet() != null)
			predicates.add(criteriaBuilder.equal(falsehoodRoot.get("outlet"), s.getOutlet()));

		Severity min = s.getMinimum(), max = s.getMinimum();

		if(min != null && max != null)
			predicates.add(criteriaBuilder.between(falsehoodRoot.get("severity"), max, min));
		if(min != null)
			predicates.add(criteriaBuilder.lessThanOrEqualTo(falsehoodRoot.get("severity"), min));
		if(max != null)
			predicates.add(criteriaBuilder.greaterThanOrEqualTo(falsehoodRoot.get("severity"), max));

		if(taggedIds != null && !taggedIds.isEmpty()) {

			predicates.add(criteriaBuilder.any(criteria.subquery(Falsehood.class).where(falsehoodRoot.get("id"))).in(taggedIds));
		}

		// Now to focus on submitted/Rejected/Confirmed status
		if(target == 0) // Submitted
			predicates.add(criteriaBuilder.lessThanOrEqualTo(falsehoodRoot.get("status"), 1));
		else if(target > 0) // Confirmed
			predicates.add(criteriaBuilder.between(falsehoodRoot.get("status"), 2,5));
		else predicates.add(criteriaBuilder.greaterThan(falsehoodRoot.get("status"), 5));

		criteria.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));


		TypedQuery<Falsehood> falsehoodQuery = entityManager.createQuery(criteria);

		falsehoodQuery.setMaxResults(s.getNumberOfEntries());
		falsehoodQuery.setFirstResult(s.getNumberOfEntries() * s.getPage());

		return falsehoodQuery.getResultList();
	}
	
//	public List<Falsehood> getConfirmedFalsehoodsBySearchFeatures(SearchFalsehood s)
//	{
//		MediaOutlet mo = s.getOutlet();
//		PublicFigure pf = s.getAuthor();
//
//		Date before = s.getTo();
//		Date after = s.getFrom();
//
//		if(before == null && after != null)
//			before = new Date(Calendar.getInstance().getTime().getTime());
//
//		Severity minSev = s.getMinimum();
//		Severity maxSev = s.getMaximum();
//
//		Pageable p = PageRequest.of(s.getPage(), s.getNumberOfEntries() == 0 ? 1 : s.getNumberOfEntries());
//
//		String terms = s.getTerms();
//
//
//		if(mo == null)
//		{
//			if(pf == null)
//			{
//				if(before != null && after != null)
//				{
//					if(minSev != null && maxSev != null)
//						return (terms == null) ? fRepo.getConfirmedFalsehoodsBetweenAndBySeverity(p, after, before, maxSev.GetValue(), minSev.GetValue()) :
//							fRepo.getConfirmedFalsehoodsBetweenAndBySeverity(p, after, before, maxSev.GetValue(), minSev.GetValue(), terms);
//					else if(minSev != null)
//						return (terms == null) ? fRepo.getConfirmedFalsehoodsBetweenAndByMinSeverity(p, after, before, minSev.GetValue()) :
//							fRepo.getConfirmedFalsehoodsBetweenAndByMinSeverity(p, after, before, minSev.GetValue(), terms);
//					else if(maxSev != null)
//						return (terms == null) ? fRepo.getConfirmedFalsehoodsBetweenAndByMaxSeverity(p, after, before, maxSev.GetValue()) :
//							fRepo.getConfirmedFalsehoodsBetweenAndByMaxSeverity(p, after, before, maxSev.GetValue(), terms);
//					else
//						return (terms == null) ? fRepo.getConfirmedFalsehoodsBetween(p, after, before) :
//							fRepo.getConfirmedFalsehoodsBetween(p, after, before, terms);
//				}
//				else if(before != null)
//				{
//					if(minSev != null && maxSev != null)
//						return (terms == null) ? fRepo.getConfirmedFalsehoodsBeforeAndBySeverity(p, before, maxSev.GetValue(), minSev.GetValue()) :
//							fRepo.getConfirmedFalsehoodsBeforeAndBySeverity(p, before, maxSev.GetValue(), minSev.GetValue(), terms);
//					else if(minSev != null)
//						return (terms == null) ? fRepo.getConfirmedFalsehoodsBeforeAndByMinSeverity(p, before, minSev.GetValue()) :
//							fRepo.getConfirmedFalsehoodsBeforeAndByMinSeverity(p, before, minSev.GetValue(), terms);
//					else if(maxSev != null)
//						return (terms == null) ? fRepo.getConfirmedFalsehoodsBeforeAndByMaxSeverity(p, before, maxSev.GetValue()) :
//							fRepo.getConfirmedFalsehoodsBeforeAndByMaxSeverity(p, before, maxSev.GetValue(), terms);
//					else
//						return (terms == null) ? fRepo.getConfirmedFalsehoodsBefore(p, before) :
//							fRepo.getConfirmedFalsehoodsBefore(p, before, terms);
//				}
//				else
//				{
//					if(minSev != null && maxSev != null)
//						return (terms == null) ? fRepo.getConfirmedFalsehoodsBySeverity(p, maxSev.GetValue(), minSev.GetValue()) :
//							fRepo.getConfirmedFalsehoodsBySeverity(p, maxSev.GetValue(), minSev.GetValue(), terms);
//					else if(minSev != null)
//						return (terms == null) ? fRepo.getConfirmedFalsehoodByMinSeverity(p, minSev.GetValue()) :
//							fRepo.getConfirmedFalsehoodByMinSeverity(p, minSev.GetValue(), terms);
//					else if(maxSev != null)
//						return (terms == null) ? fRepo.getConfirmedFalsehoodByMaxSeverity(p, maxSev.GetValue()) :
//							fRepo.getConfirmedFalsehoodByMaxSeverity(p, maxSev.GetValue(),terms);
//					else
//						return (terms == null) ? fRepo.getConfirmedFalsehoods(p) : fRepo.getConfirmedFalsehoods(p, terms);
//				}
//			}
//			else // We have no Media Outlet but we do have a public figure
//			{
//				if(before != null && after != null)
//				{
//					if(minSev != null && maxSev != null)
//						return (terms == null) ? fRepo.getConfirmedFalsehoodsBetweenAndBySeverityPublicFigure(p, pf, after, before, maxSev.GetValue(), minSev.GetValue()) :
//							fRepo.getConfirmedFalsehoodsBetweenAndBySeverityPublicFigure(p, pf, after, before, maxSev.GetValue(), minSev.GetValue(), terms);
//					else if(minSev != null)
//						return (terms == null) ? fRepo.getConfirmedFalsehoodsBetweenAndByMinSeverityPublicFigure(p, pf, after, before, minSev.GetValue()) :
//							fRepo.getConfirmedFalsehoodsBetweenAndByMinSeverityPublicFigure(p, pf, after, before, minSev.GetValue(), terms);
//					else if(maxSev != null)
//						return (terms == null) ? fRepo.getConfirmedFalsehoodsBetweenAndByMaxSeverityPublicFigure(p, pf, after, before, maxSev.GetValue()) :
//							fRepo.getConfirmedFalsehoodsBetweenAndByMaxSeverityPublicFigure(p, pf, after, before, maxSev.GetValue(), terms);
//					else
//						return (terms == null) ? fRepo.getConfirmedFalsehoodsBetweenAndByPublicOfficial(p, pf, after, before) :
//							fRepo.getConfirmedFalsehoodsBetweenAndByPublicOfficial(p, pf, after, before, terms);
//				}
//				else if(before != null)
//				{
//					if(minSev != null && maxSev != null)
//						return (terms == null) ? fRepo.getConfirmedFalsehoodsBeforeAndBySeverityPublicFigure(p, pf, before, maxSev.GetValue(), minSev.GetValue()) :
//							fRepo.getConfirmedFalsehoodsBeforeAndBySeverityPublicFigure(p, pf, before, maxSev.GetValue(), minSev.GetValue(), terms);
//					else if(minSev != null)
//						return (terms == null) ? fRepo.getConfirmedFalsehoodsBeforeAndByMinSeverityPublicFigure(p, pf, before, minSev.GetValue()) :
//							fRepo.getConfirmedFalsehoodsBeforeAndByMinSeverityPublicFigure(p, pf, before, minSev.GetValue(), terms);
//					else if(maxSev != null)
//						return (terms == null) ? fRepo.getConfirmedFalsehoodsBeforeAndByMaxSeverityPublicFigure(p, pf, before, maxSev.GetValue()) :
//							fRepo.getConfirmedFalsehoodsBeforeAndByMaxSeverityPublicFigure(p, pf, before, maxSev.GetValue(), terms);
//					else
//						return (terms == null) ? fRepo.getConfirmedFalsehoodsBeforeAndByPublicOfficial(p, pf, before) :
//							fRepo.getConfirmedFalsehoodsBeforeAndByPublicOfficial(p, pf, before, terms);
//				}
//				else
//				{
//					if(minSev != null && maxSev != null)
//						return (terms == null) ? fRepo.getConfirmedFalsehoodsByPublicOfficialAndSeverity(p, pf, maxSev.GetValue(), minSev.GetValue()) :
//							fRepo.getConfirmedFalsehoodsByPublicOfficialAndSeverity(p, pf, maxSev.GetValue(), minSev.GetValue(), terms);
//					else if(minSev != null)
//						return (terms == null) ? fRepo.getConfirmedFalsehoodsByPublicOfficialAndMinSeverity(p, pf, minSev.GetValue()) :
//							fRepo.getConfirmedFalsehoodsByPublicOfficialAndMinSeverity(p, pf, minSev.GetValue(), terms);
//					else if(maxSev != null)
//						return (terms == null) ? fRepo.getConfirmedFalsehoodsByPublicOfficialAndMaxSeverity(p, pf, maxSev.GetValue()) :
//							fRepo.getConfirmedFalsehoodsByPublicOfficialAndMaxSeverity(p, pf, maxSev.GetValue(), terms);
//					else
//						return (terms == null) ? fRepo.getConfirmedFalsehoodsByPublicOfficial(p, pf) : fRepo.getConfirmedFalsehoodsByPublicOfficial(p, pf, terms);
//				}
//			}
//		}
//		else // We have a media Outlet
//		{
//			if(pf == null)
//			{
//				if(before != null && after != null) // Between
//				{
//					if(minSev != null && maxSev != null)
//						return (terms == null) ? fRepo.getConfirmedFalsehoodsBetweenAndBySeverityOutlet(p, mo, after, before, maxSev.GetValue(), minSev.GetValue()) :
//							fRepo.getConfirmedFalsehoodsBetweenAndBySeverityOutlet(p, mo, after, before, maxSev.GetValue(), minSev.GetValue(), terms);
//					else if(minSev != null)
//						return (terms == null) ? fRepo.getConfirmedFalsehoodsBetweenAndByMinSeverityOutlet(p, mo, after, before, minSev.GetValue()) :
//							fRepo.getConfirmedFalsehoodsBetweenAndByMinSeverityOutlet(p, mo, after, before, minSev.GetValue(), terms);
//					else if(maxSev != null)
//						return (terms == null) ? fRepo.getConfirmedFalsehoodsBetweenAndByMaxSeverityOutlet(p, mo, after, before, maxSev.GetValue()) :
//							fRepo.getConfirmedFalsehoodsBetweenAndByMaxSeverityOutlet(p, mo, after, before, maxSev.GetValue(), terms);
//					else
//						return (terms == null) ? fRepo.getConfirmedFalsehoodsBetweenAndByOutlet(p, mo, after, before) :
//							fRepo.getConfirmedFalsehoodsBetweenAndByOutlet(p, mo, after, before, terms);
//				}
//				else if(before != null) // Before
//				{
//					if(minSev != null && maxSev != null)
//						return (terms == null) ? fRepo.getConfirmedFalsehoodsBeforeAndBySeverityOutlet(p, mo, before, maxSev.GetValue(), minSev.GetValue()) :
//							fRepo.getConfirmedFalsehoodsBeforeAndBySeverityOutlet(p, mo, before, maxSev.GetValue(), minSev.GetValue(), terms);
//					else if(minSev != null)
//						return (terms == null) ? fRepo.getConfirmedFalsehoodsBeforeAndByMinSeverityOutlet(p, mo, before, minSev.GetValue()) :
//							fRepo.getConfirmedFalsehoodsBeforeAndByMinSeverityOutlet(p, mo, before, minSev.GetValue(), terms);
//					else if(maxSev != null)
//						return (terms == null) ? fRepo.getConfirmedFalsehoodsBeforeAndByMaxSeverityOutlet(p, mo, before, maxSev.GetValue()) :
//							fRepo.getConfirmedFalsehoodsBeforeAndByMaxSeverityOutlet(p, mo, before, maxSev.GetValue(), terms);
//					else
//						return (terms == null) ? fRepo.getConfirmedFalsehoodsBeforeAndByOutlet(p, mo, before) :
//							fRepo.getConfirmedFalsehoodsBeforeAndByOutlet(p, mo, before, terms);
//				}
//				else // No time
//				{
//					if(minSev != null && maxSev != null)
//						return (terms == null) ? fRepo.getConfirmedFalsehoodsByOutletAndSeverity(p, mo, maxSev.GetValue(), minSev.GetValue()) :
//							fRepo.getConfirmedFalsehoodsByOutletAndSeverity(p, mo, maxSev.GetValue(), minSev.GetValue(), terms);
//					else if(minSev != null)
//						return (terms == null) ? fRepo.getConfirmedFalsehoodsByOutletAndMinSeverity(p, mo, minSev.GetValue()) :
//							fRepo.getConfirmedFalsehoodsByOutletAndMinSeverity(p, mo, minSev.GetValue(), terms);
//					else if(maxSev != null)
//						return (terms == null) ? fRepo.getConfirmedFalsehoodsByOutletAndMaxSeverity(p, mo, maxSev.GetValue()) :
//							fRepo.getConfirmedFalsehoodsByOutletAndMaxSeverity(p, mo, maxSev.GetValue(), terms);
//					else
//						return (terms == null) ? fRepo.getConfirmedFalsehoodsByOutlet(p, mo) : fRepo.getConfirmedFalsehoodsByOutlet(p, mo, terms);
//				}
//			}
//			else // We have a media Outlet and a public figure
//			{
//				if(before != null && after != null) // We have a "between"
//				{
//					if(minSev != null && maxSev != null)
//						return (terms == null) ? fRepo.getConfirmedFalsehoodsBetweenAndBySeverityOutletPublicFigure(p, pf, mo, after, before, maxSev.GetValue(), minSev.GetValue()) :
//							fRepo.getConfirmedFalsehoodsBetweenAndBySeverityOutletPublicFigure(p, pf, mo, after, before, maxSev.GetValue(), minSev.GetValue(), terms);
//					else if(minSev != null)
//						return (terms == null) ? fRepo.getConfirmedFalsehoodsBetweenAndByMinSeverityOutletPublicFigure(p, pf, mo, after, before, minSev.GetValue()) :
//							fRepo.getConfirmedFalsehoodsBetweenAndByMinSeverityOutletPublicFigure(p, pf, mo, after, before, minSev.GetValue(), terms);
//					else if(maxSev != null)
//						return (terms == null) ? fRepo.getConfirmedFalsehoodsBetweenAndByMaxSeverityOutletPublicFigure(p, pf, mo, after, before, maxSev.GetValue()) :
//							fRepo.getConfirmedFalsehoodsBetweenAndByMaxSeverityOutletPublicFigure(p, pf, mo, after, before, maxSev.GetValue(), terms);
//					else
//						return (terms == null) ? fRepo.getConfirmedFalsehoodsBetweenAndByOutletPublicFigure(p, pf, mo, after, before) :
//							fRepo.getConfirmedFalsehoodsBetweenAndByOutletPublicFigure(p, pf, mo, after, before, terms);
//				}
//				else if(before != null) // We only have before
//				{
//					if(minSev != null && maxSev != null)
//						return (terms == null) ? fRepo.getConfirmedFalsehoodsBeforeAndBySeverityOutletPublicFigure(p, pf, mo, before, maxSev.GetValue(), minSev.GetValue()) :
//							fRepo.getConfirmedFalsehoodsBeforeAndBySeverityOutletPublicFigure(p, pf, mo, before, maxSev.GetValue(), minSev.GetValue(), terms);
//					else if(minSev != null)
//						return (terms == null) ? fRepo.getConfirmedFalsehoodsBeforeAndByMinSeverityOutletPublicFigure(p, pf, mo, before, minSev.GetValue()) :
//							fRepo.getConfirmedFalsehoodsBeforeAndByMinSeverityOutletPublicFigure(p, pf, mo, before, minSev.GetValue(), terms);
//					else if(maxSev != null)
//						return (terms == null) ? fRepo.getConfirmedFalsehoodsBeforeAndByMaxSeverityOutletPublicFigure(p, pf, mo, before, maxSev.GetValue()) :
//							fRepo.getConfirmedFalsehoodsBeforeAndByMaxSeverityOutletPublicFigure(p, pf, mo, before, maxSev.GetValue(), terms);
//					else
//						return (terms == null) ? fRepo.getConfirmedFalsehoodsBeforeAndByOutletPublicFigure(p, pf, mo, before) :
//							fRepo.getConfirmedFalsehoodsBeforeAndByOutletPublicFigure(p, pf, mo, before, terms);
//				}
//				else // No Dates
//				{
//					if(minSev != null && maxSev != null)
//						return (terms == null) ? fRepo.getConfirmedFalsehoodsByPublicOfficialOutletAndSeverity(p, pf, mo, maxSev.GetValue(), minSev.GetValue()) :
//							fRepo.getConfirmedFalsehoodsByPublicOfficialOutletAndSeverity(p, pf, mo, maxSev.GetValue(), minSev.GetValue(), terms);
//					else if(minSev != null)
//						return (terms == null) ? fRepo.getConfirmedFalsehoodsByPublicOfficialOutletAndMinSeverity(p, pf, mo, minSev.GetValue()):
//							fRepo.getConfirmedFalsehoodsByPublicOfficialOutletAndMinSeverity(p, pf, mo, minSev.GetValue(), terms);
//					else if(maxSev != null)
//						return (terms == null) ? fRepo.getConfirmedFalsehoodsByPublicOfficialOutletAndMaxSeverity(p, pf, mo, maxSev.GetValue()) :
//							fRepo.getConfirmedFalsehoodsByPublicOfficialOutletAndMaxSeverity(p, pf, mo, maxSev.GetValue(), terms);
//					else
//						return (terms == null) ? fRepo.getConfirmedFalsehoodsByPublicOfficialAndOutlet(p, pf, mo) :
//							fRepo.getConfirmedFalsehoodsByPublicOfficialAndOutlet(p, pf, mo, terms);
//				}
//			}
//		}
//
//	}
//
//	public List<Falsehood> getRejectedFalsehoodsBySearchFeatures(SearchFalsehood s)
//	{
//		MediaOutlet mo = s.getOutlet();
//		PublicFigure pf = s.getAuthor();
//
//		Date before = s.getTo();
//		Date after = s.getFrom();
//
//		if(before == null && after != null)
//			before = new Date(Calendar.getInstance().getTime().getTime());
//
//		Severity minSev = s.getMinimum();
//		Severity maxSev = s.getMaximum();
//
//		Pageable p = PageRequest.of(s.getPage(), s.getNumberOfEntries() == 0 ? 1 : s.getNumberOfEntries());
//
//		String terms = s.getTerms();
//
//
//		if(mo == null)
//		{
//			if(pf == null)
//			{
//				if(before != null && after != null)
//				{
//					if(minSev != null && maxSev != null)
//						return (terms == null) ? fRepo.getRejectedFalsehoodsBetweenAndBySeverity(p, after, before, maxSev.GetValue(), minSev.GetValue()) :
//							fRepo.getRejectedFalsehoodsBetweenAndBySeverity(p, after, before, maxSev.GetValue(), minSev.GetValue(), terms);
//					else if(minSev != null)
//						return (terms == null) ? fRepo.getRejectedFalsehoodsBetweenAndByMinSeverity(p, after, before, minSev.GetValue()) :
//							fRepo.getRejectedFalsehoodsBetweenAndByMinSeverity(p, after, before, minSev.GetValue(), terms);
//					else if(maxSev != null)
//						return (terms == null) ? fRepo.getRejectedFalsehoodsBetweenAndByMaxSeverity(p, after, before, maxSev.GetValue()) :
//							fRepo.getRejectedFalsehoodsBetweenAndByMaxSeverity(p, after, before, maxSev.GetValue(), terms);
//					else
//						return (terms == null) ? fRepo.getRejectedFalsehoodsBetween(p, after, before) :
//							fRepo.getRejectedFalsehoodsBetween(p, after, before, terms);
//				}
//				else if(before != null)
//				{
//					if(minSev != null && maxSev != null)
//						return (terms == null) ? fRepo.getRejectedFalsehoodsBeforeAndBySeverity(p, before, maxSev.GetValue(), minSev.GetValue()) :
//							fRepo.getRejectedFalsehoodsBeforeAndBySeverity(p, before, maxSev.GetValue(), minSev.GetValue(), terms);
//					else if(minSev != null)
//						return (terms == null) ? fRepo.getRejectedFalsehoodsBeforeAndByMinSeverity(p, before, minSev.GetValue()) :
//							fRepo.getRejectedFalsehoodsBeforeAndByMinSeverity(p, before, minSev.GetValue(), terms);
//					else if(maxSev != null)
//						return (terms == null) ? fRepo.getRejectedFalsehoodsBeforeAndByMaxSeverity(p, before, maxSev.GetValue()) :
//							fRepo.getRejectedFalsehoodsBeforeAndByMaxSeverity(p, before, maxSev.GetValue(), terms);
//					else
//						return (terms == null) ? fRepo.getRejectedFalsehoodsBefore(p, before) :
//							fRepo.getRejectedFalsehoodsBefore(p, before, terms);
//				}
//				else
//				{
//					if(minSev != null && maxSev != null)
//						return (terms == null) ? fRepo.getRejectedFalsehoodsBySeverity(p, maxSev.GetValue(), minSev.GetValue()) :
//							fRepo.getRejectedFalsehoodsBySeverity(p, maxSev.GetValue(), minSev.GetValue(), terms);
//					else if(minSev != null)
//						return (terms == null) ? fRepo.getRejectedFalsehoodByMinSeverity(p, minSev.GetValue()) :
//							fRepo.getRejectedFalsehoodByMinSeverity(p, minSev.GetValue(), terms);
//					else if(maxSev != null)
//						return (terms == null) ? fRepo.getRejectedFalsehoodByMaxSeverity(p, maxSev.GetValue()) :
//							fRepo.getRejectedFalsehoodByMaxSeverity(p, maxSev.GetValue(),terms);
//					else
//						return (terms == null) ? fRepo.getRejectedFalsehoods(p) : fRepo.getRejectedFalsehoods(p, terms);
//				}
//			}
//			else // We have no Media Outlet but we do have a public figure
//			{
//				if(before != null && after != null)
//				{
//					if(minSev != null && maxSev != null)
//						return (terms == null) ? fRepo.getRejectedFalsehoodsBetweenAndBySeverityPublicFigure(p, pf, after, before, maxSev.GetValue(), minSev.GetValue()) :
//							fRepo.getRejectedFalsehoodsBetweenAndBySeverityPublicFigure(p, pf, after, before, maxSev.GetValue(), minSev.GetValue(), terms);
//					else if(minSev != null)
//						return (terms == null) ? fRepo.getRejectedFalsehoodsBetweenAndByMinSeverityPublicFigure(p, pf, after, before, minSev.GetValue()) :
//							fRepo.getRejectedFalsehoodsBetweenAndByMinSeverityPublicFigure(p, pf, after, before, minSev.GetValue(), terms);
//					else if(maxSev != null)
//						return (terms == null) ? fRepo.getRejectedFalsehoodsBetweenAndByMaxSeverityPublicFigure(p, pf, after, before, maxSev.GetValue()) :
//							fRepo.getRejectedFalsehoodsBetweenAndByMaxSeverityPublicFigure(p, pf, after, before, maxSev.GetValue(), terms);
//					else
//						return (terms == null) ? fRepo.getRejectedFalsehoodsBetweenAndByPublicOfficial(p, pf, after, before) :
//							fRepo.getRejectedFalsehoodsBetweenAndByPublicOfficial(p, pf, after, before, terms);
//				}
//				else if(before != null)
//				{
//					if(minSev != null && maxSev != null)
//						return (terms == null) ? fRepo.getRejectedFalsehoodsBeforeAndBySeverityPublicFigure(p, pf, before, maxSev.GetValue(), minSev.GetValue()) :
//							fRepo.getRejectedFalsehoodsBeforeAndBySeverityPublicFigure(p, pf, before, maxSev.GetValue(), minSev.GetValue(), terms);
//					else if(minSev != null)
//						return (terms == null) ? fRepo.getRejectedFalsehoodsBeforeAndByMinSeverityPublicFigure(p, pf, before, minSev.GetValue()) :
//							fRepo.getRejectedFalsehoodsBeforeAndByMinSeverityPublicFigure(p, pf, before, minSev.GetValue(), terms);
//					else if(maxSev != null)
//						return (terms == null) ? fRepo.getRejectedFalsehoodsBeforeAndByMaxSeverityPublicFigure(p, pf, before, maxSev.GetValue()) :
//							fRepo.getRejectedFalsehoodsBeforeAndByMaxSeverityPublicFigure(p, pf, before, maxSev.GetValue(), terms);
//					else
//						return (terms == null) ? fRepo.getRejectedFalsehoodsBeforeAndByPublicOfficial(p, pf, before) :
//							fRepo.getRejectedFalsehoodsBeforeAndByPublicOfficial(p, pf, before, terms);
//				}
//				else
//				{
//					if(minSev != null && maxSev != null)
//						return (terms == null) ? fRepo.getRejectedFalsehoodsByPublicOfficialAndSeverity(p, pf, maxSev.GetValue(), minSev.GetValue()) :
//							fRepo.getRejectedFalsehoodsByPublicOfficialAndSeverity(p, pf, maxSev.GetValue(), minSev.GetValue(), terms);
//					else if(minSev != null)
//						return (terms == null) ? fRepo.getRejectedFalsehoodsByPublicOfficialAndMinSeverity(p, pf, minSev.GetValue()) :
//							fRepo.getRejectedFalsehoodsByPublicOfficialAndMinSeverity(p, pf, minSev.GetValue(), terms);
//					else if(maxSev != null)
//						return (terms == null) ? fRepo.getRejectedFalsehoodsByPublicOfficialAndMaxSeverity(p, pf, maxSev.GetValue()) :
//							fRepo.getRejectedFalsehoodsByPublicOfficialAndMaxSeverity(p, pf, maxSev.GetValue(), terms);
//					else
//						return (terms == null) ? fRepo.getRejectedFalsehoodsByPublicOfficial(p, pf) : fRepo.getRejectedFalsehoodsByPublicOfficial(p, pf, terms);
//				}
//			}
//		}
//		else // We have a media Outlet
//		{
//			if(pf == null)
//			{
//				if(before != null && after != null) // Between
//				{
//					if(minSev != null && maxSev != null)
//						return (terms == null) ? fRepo.getRejectedFalsehoodsBetweenAndBySeverityOutlet(p, mo, after, before, maxSev.GetValue(), minSev.GetValue()) :
//							fRepo.getRejectedFalsehoodsBetweenAndBySeverityOutlet(p, mo, after, before, maxSev.GetValue(), minSev.GetValue(), terms);
//					else if(minSev != null)
//						return (terms == null) ? fRepo.getRejectedFalsehoodsBetweenAndByMinSeverityOutlet(p, mo, after, before, minSev.GetValue()) :
//							fRepo.getRejectedFalsehoodsBetweenAndByMinSeverityOutlet(p, mo, after, before, minSev.GetValue(), terms);
//					else if(maxSev != null)
//						return (terms == null) ? fRepo.getRejectedFalsehoodsBetweenAndByMaxSeverityOutlet(p, mo, after, before, maxSev.GetValue()) :
//							fRepo.getRejectedFalsehoodsBetweenAndByMaxSeverityOutlet(p, mo, after, before, maxSev.GetValue(), terms);
//					else
//						return (terms == null) ? fRepo.getRejectedFalsehoodsBetweenAndByOutlet(p, mo, after, before) :
//							fRepo.getRejectedFalsehoodsBetweenAndByOutlet(p, mo, after, before, terms);
//				}
//				else if(before != null) // Before
//				{
//					if(minSev != null && maxSev != null)
//						return (terms == null) ? fRepo.getRejectedFalsehoodsBeforeAndBySeverityOutlet(p, mo, before, maxSev.GetValue(), minSev.GetValue()) :
//							fRepo.getRejectedFalsehoodsBeforeAndBySeverityOutlet(p, mo, before, maxSev.GetValue(), minSev.GetValue(), terms);
//					else if(minSev != null)
//						return (terms == null) ? fRepo.getRejectedFalsehoodsBeforeAndByMinSeverityOutlet(p, mo, before, minSev.GetValue()) :
//							fRepo.getRejectedFalsehoodsBeforeAndByMinSeverityOutlet(p, mo, before, minSev.GetValue(), terms);
//					else if(maxSev != null)
//						return (terms == null) ? fRepo.getRejectedFalsehoodsBeforeAndByMaxSeverityOutlet(p, mo, before, maxSev.GetValue()) :
//							fRepo.getRejectedFalsehoodsBeforeAndByMaxSeverityOutlet(p, mo, before, maxSev.GetValue(), terms);
//					else
//						return (terms == null) ? fRepo.getRejectedFalsehoodsBeforeAndByOutlet(p, mo, before) :
//							fRepo.getRejectedFalsehoodsBeforeAndByOutlet(p, mo, before, terms);
//				}
//				else // No time
//				{
//					if(minSev != null && maxSev != null)
//						return (terms == null) ? fRepo.getRejectedFalsehoodsByOutletAndSeverity(p, mo, maxSev.GetValue(), minSev.GetValue()) :
//							fRepo.getRejectedFalsehoodsByOutletAndSeverity(p, mo, maxSev.GetValue(), minSev.GetValue(), terms);
//					else if(minSev != null)
//						return (terms == null) ? fRepo.getRejectedFalsehoodsByOutletAndMinSeverity(p, mo, minSev.GetValue()) :
//							fRepo.getRejectedFalsehoodsByOutletAndMinSeverity(p, mo, minSev.GetValue(), terms);
//					else if(maxSev != null)
//						return (terms == null) ? fRepo.getRejectedFalsehoodsByOutletAndMaxSeverity(p, mo, maxSev.GetValue()) :
//							fRepo.getRejectedFalsehoodsByOutletAndMaxSeverity(p, mo, maxSev.GetValue(), terms);
//					else
//						return (terms == null) ? fRepo.getRejectedFalsehoodsByOutlet(p, mo) : fRepo.getRejectedFalsehoodsByOutlet(p, mo, terms);
//				}
//			}
//			else // We have a media Outlet and a public figure
//			{
//				if(before != null && after != null) // We have a "between"
//				{
//					if(minSev != null && maxSev != null)
//						return (terms == null) ? fRepo.getRejectedFalsehoodsBetweenAndBySeverityOutletPublicFigure(p, pf, mo, after, before, maxSev.GetValue(), minSev.GetValue()) :
//							fRepo.getRejectedFalsehoodsBetweenAndBySeverityOutletPublicFigure(p, pf, mo, after, before, maxSev.GetValue(), minSev.GetValue(), terms);
//					else if(minSev != null)
//						return (terms == null) ? fRepo.getRejectedFalsehoodsBetweenAndByMinSeverityOutletPublicFigure(p, pf, mo, after, before, minSev.GetValue()) :
//							fRepo.getRejectedFalsehoodsBetweenAndByMinSeverityOutletPublicFigure(p, pf, mo, after, before, minSev.GetValue(), terms);
//					else if(maxSev != null)
//						return (terms == null) ? fRepo.getRejectedFalsehoodsBetweenAndByMaxSeverityOutletPublicFigure(p, pf, mo, after, before, maxSev.GetValue()) :
//							fRepo.getRejectedFalsehoodsBetweenAndByMaxSeverityOutletPublicFigure(p, pf, mo, after, before, maxSev.GetValue(), terms);
//					else
//						return (terms == null) ? fRepo.getRejectedFalsehoodsBetweenAndByOutletPublicFigure(p, pf, mo, after, before) :
//							fRepo.getRejectedFalsehoodsBetweenAndByOutletPublicFigure(p, pf, mo, after, before, terms);
//				}
//				else if(before != null) // We only have before
//				{
//					if(minSev != null && maxSev != null)
//						return (terms == null) ? fRepo.getRejectedFalsehoodsBeforeAndBySeverityOutletPublicFigure(p, pf, mo, before, maxSev.GetValue(), minSev.GetValue()) :
//							fRepo.getRejectedFalsehoodsBeforeAndBySeverityOutletPublicFigure(p, pf, mo, before, maxSev.GetValue(), minSev.GetValue(), terms);
//					else if(minSev != null)
//						return (terms == null) ? fRepo.getRejectedFalsehoodsBeforeAndByMinSeverityOutletPublicFigure(p, pf, mo, before, minSev.GetValue()) :
//							fRepo.getRejectedFalsehoodsBeforeAndByMinSeverityOutletPublicFigure(p, pf, mo, before, minSev.GetValue(), terms);
//					else if(maxSev != null)
//						return (terms == null) ? fRepo.getRejectedFalsehoodsBeforeAndByMaxSeverityOutletPublicFigure(p, pf, mo, before, maxSev.GetValue()) :
//							fRepo.getRejectedFalsehoodsBeforeAndByMaxSeverityOutletPublicFigure(p, pf, mo, before, maxSev.GetValue(), terms);
//					else
//						return (terms == null) ? fRepo.getRejectedFalsehoodsBeforeAndByOutletPublicFigure(p, pf, mo, before) :
//							fRepo.getRejectedFalsehoodsBeforeAndByOutletPublicFigure(p, pf, mo, before, terms);
//				}
//				else // No Dates
//				{
//					if(minSev != null && maxSev != null)
//						return (terms == null) ? fRepo.getRejectedFalsehoodsByPublicOfficialOutletAndSeverity(p, pf, mo, maxSev.GetValue(), minSev.GetValue()) :
//							fRepo.getRejectedFalsehoodsByPublicOfficialOutletAndSeverity(p, pf, mo, maxSev.GetValue(), minSev.GetValue(), terms);
//					else if(minSev != null)
//						return (terms == null) ? fRepo.getRejectedFalsehoodsByPublicOfficialOutletAndMinSeverity(p, pf, mo, minSev.GetValue()):
//							fRepo.getRejectedFalsehoodsByPublicOfficialOutletAndMinSeverity(p, pf, mo, minSev.GetValue(), terms);
//					else if(maxSev != null)
//						return (terms == null) ? fRepo.getRejectedFalsehoodsByPublicOfficialOutletAndMaxSeverity(p, pf, mo, maxSev.GetValue()) :
//							fRepo.getRejectedFalsehoodsByPublicOfficialOutletAndMaxSeverity(p, pf, mo, maxSev.GetValue(), terms);
//					else
//						return (terms == null) ? fRepo.getRejectedFalsehoodsByPublicOfficialAndOutlet(p, pf, mo) :
//							fRepo.getRejectedFalsehoodsByPublicOfficialAndOutlet(p, pf, mo, terms);
//				}
//			}
//		}
//
//	}

	public List<Falsehood> getSubmittedFalsehoods(int size, int page)
	{
		return fRepo.getSubmittedFalsehood(PageRequest.of(page, size));
	}
	
	public ResponseEntity<FullFalsehood> getFalsehoodById(BigInteger id)
	{
		if(!fRepo.existsById(id))
			return new ResponseEntity<FullFalsehood>(HttpStatus.NOT_FOUND);

		ResponseEntity<String> contents = s3BucketManager.getContents("MediaFalsehood-" + id, "Falsehoods");

		if(contents.getStatusCode().is2xxSuccessful())
			try {
				return new ResponseEntity<FullFalsehood>(new FullFalsehood(contents.getBody(),
						fRepo.getById(id), new FalsehoodRecords(id, (byte)0, recordsRepo.retrieveRecords(id)), new ArrayList<>(), new ArrayList<>()), HttpStatus.OK);
			}catch(JsonProcessingException ex)
			{
				return new ResponseEntity<FullFalsehood>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		else
			return new ResponseEntity<FullFalsehood>(contents.getStatusCode());

	}

}
