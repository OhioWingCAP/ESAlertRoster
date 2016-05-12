package gov.cap.ohwg.es.alertroster.model.repo;

import com.google.appengine.api.datastore.Query;
import gov.cap.ohwg.es.alertroster.model.entity.Contact;
import gov.cap.ohwg.es.alertroster.model.entity.Member;
import gov.cap.ohwg.es.alertroster.model.pojo.GaeUser;
import gov.cap.ohwg.es.alertroster.model.pojo.Sort;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by ckovacs on 5/11/16.
 */
@Component
public class MemberRepo extends GenericRepo<Member> {

    private static Logger LOG = Logger.getLogger(MemberRepo.class);

    @Autowired
    private ContactRepo contactRepo;

    public MemberRepo() {
        super(Member.class);
    }

    public Member getOneByEmail(String email) {
        Member member = null;
        List<Contact> contacts = contactRepo.getMatching("contact", email);
        if (contacts.size() == 1) {
            member = get(contacts.get(0).getCapid());
        } else if (contacts.size() > 1) {
            LOG.warn("Email address returned more than one member: " + email);
        }
        return member;
    }

    public Member getLoggedInMember() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof GaeUser)) {
            return null;
        }
        GaeUser user = (GaeUser) authentication.getPrincipal();
        if (user == null) {
            return null;
        }
        return getOneByEmail(user.getEmail());
    }

    public List<Member> searchFor(String[] terms) {
        return getAll(
                getFilterFor(terms)
        );
    }

    private Query.Filter getFilterFor(String name, Query.FilterOperator operator, Object value) {
        return new Query.FilterPredicate(name, operator, value);
    }

    private Query.Filter getStartsWithFilterFor(String name, String value) {
        Collection<Query.Filter> predicates = new ArrayList<>();
        predicates.add(new Query.FilterPredicate(name, Query.FilterOperator.GREATER_THAN_OR_EQUAL, value));
        predicates.add(new Query.FilterPredicate(name, Query.FilterOperator.LESS_THAN, value + "\ufffd"));
        Query.CompositeFilter filter = new Query.CompositeFilter(Query.CompositeFilterOperator.AND, predicates);
        return filter;
    }

    public Query.Filter getFilterFor(String term) {
        Query q = new Query(Member.class.getSimpleName());
        Collection<Query.Filter> predicates = new ArrayList<>();
        if (StringUtils.isAlpha(term)) {
            predicates.add(getStartsWithFilterFor("nameLast", StringUtils.capitalize(term)));
            predicates.add(getStartsWithFilterFor("nameFirst", StringUtils.capitalize(term)));
        } else if (StringUtils.isNumeric(term)) {
            predicates.add(getFilterFor("capid", Query.FilterOperator.EQUAL, Long.valueOf(term)));
        }
        return new Query.CompositeFilter(Query.CompositeFilterOperator.OR, predicates);
    }

    public Query.Filter getFilterFor(String[] terms) {
        if (terms.length == 1) {
            return getFilterFor(terms[0]);
        } else {
            Collection<Query.Filter> predicates = new ArrayList<>();
            for (String term : terms) {
                predicates.add(getFilterFor(term));
            }
            return new Query.CompositeFilter(Query.CompositeFilterOperator.AND, predicates);
        }
    }

}


