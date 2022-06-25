package com.trecapps.falsehoods.falsehoodSearch.services;

import com.trecapps.falsehoods.falsehoodSearch.models.FalsehoodCommonTag;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class TagSupportService {
    @PersistenceContext
    EntityManager entityManager;

    List<String> getTerms(String termString)
    {
        List<String> terms = new ArrayList<>();

        termString = termString.replace('\n', ' ').replace('\r', ' ').replace('\t', ' ').replace('\\', ' ');


        String basicTerm = new String("");
        boolean inquote = false;

        for(int rust = 0; rust < termString.length(); rust++)
        {
            char ch = termString.charAt(rust);

            if(ch == '\"')
                inquote = !inquote;
            else if(ch == ' ' && !inquote)
            {
                if(basicTerm.length()> 0)
                    terms.add(basicTerm);
                basicTerm = new String("");
            }
            else basicTerm += ch;
        }
        if(basicTerm.length()> 0)
            terms.add(basicTerm);
        return terms;


    }

    <T> TypedQuery<T> getQuery(List<String> terms, Class targetType)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteria = criteriaBuilder.createQuery(targetType);
        Root<FalsehoodCommonTag> rootCommon = criteria.from(targetType);

        List<Predicate> predicates = new ArrayList<>();

        for(int rust = 0; rust < terms.size();rust++)
        {
            predicates.add(criteriaBuilder.equal(rootCommon.get("tag"), terms.get(rust)));
        }

        criteria.where(criteriaBuilder.or(predicates.toArray(new Predicate[0])));

        return entityManager.createQuery(criteria);
    }


}
