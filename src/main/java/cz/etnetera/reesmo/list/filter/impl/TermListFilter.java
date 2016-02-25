package cz.etnetera.reesmo.list.filter.impl;

import org.elasticsearch.index.query.BoolFilterBuilder;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.TermFilterBuilder;

import com.fasterxml.jackson.annotation.JsonTypeName;

import cz.etnetera.reesmo.list.filter.ListFilter;

/**
 * Term filter representation.
 */
@JsonTypeName(TermListFilter.TYPE)
public class TermListFilter extends ListFilter {

	public static final String TYPE = "term";
	
	protected Object term;

	public FilterBuilder getFilterBuilder() {
		return new BoolFilterBuilder().must(new TermFilterBuilder(field, term)).cache(true);
	}

	public Object getTerm() {
		return term;
	}

	public void setTerm(Object term) {
		this.term = term;
	}

}