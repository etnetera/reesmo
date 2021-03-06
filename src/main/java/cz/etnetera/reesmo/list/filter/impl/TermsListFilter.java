package cz.etnetera.reesmo.list.filter.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import cz.etnetera.reesmo.list.filter.ListFilter;
import org.elasticsearch.index.query.BoolFilterBuilder;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.TermsFilterBuilder;

import java.util.List;

/**
 * Terms filter representation.
 */
@JsonTypeName(TermsListFilter.TYPE)
public class TermsListFilter extends ListFilter {

	public static final String TYPE = "terms";
	
	protected List<Object> terms;

	@Override
	public String getType() {
		return TYPE;
	}

	@JsonIgnore
	@Override
	public FilterBuilder getFilterBuilder() {
		return new BoolFilterBuilder().must(new TermsFilterBuilder(field, terms)).cache(true);
	}

	public List<Object> getTerms() {
		return terms;
	}

	public void setTerms(List<Object> terms) {
		this.terms = terms;
	}
	
}
