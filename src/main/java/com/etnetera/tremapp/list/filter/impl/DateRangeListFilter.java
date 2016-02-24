package com.etnetera.tremapp.list.filter.impl;

import org.elasticsearch.index.query.BoolFilterBuilder;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.RangeFilterBuilder;

import com.etnetera.tremapp.list.filter.ListFilter;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Date range filter representation.
 */
@JsonTypeName(DateRangeListFilter.TYPE)
public class DateRangeListFilter extends ListFilter {

	public static final String TYPE = "daterange";
	
	protected Long from;
	
	protected Long to;

	public FilterBuilder getFilterBuilder() {
		RangeFilterBuilder builder = new RangeFilterBuilder(field);
		if (from != null)
			builder.from(from.longValue());
		else
			builder.includeLower(false);
		if (to != null)
			builder.to(to.longValue());
		else
			builder.includeUpper(false);
		return new BoolFilterBuilder().must(builder).cache(true);
	}

	public Long getFrom() {
		return from;
	}

	public void setFrom(Long from) {
		this.from = from;
	}

	public Long getTo() {
		return to;
	}

	public void setTo(Long to) {
		this.to = to;
	}

}