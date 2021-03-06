package cz.etnetera.reesmo.list.filter.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import cz.etnetera.reesmo.list.filter.ListFilter;
import org.elasticsearch.index.query.BoolFilterBuilder;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.RangeFilterBuilder;

/**
 * Double range filter representation.
 */
@JsonTypeName(DoubleRangeListFilter.TYPE)
public class DoubleRangeListFilter extends ListFilter {

	public static final String TYPE = "doublerange";
	
	protected Double from;
	
	protected Double to;

	@Override
	public String getType() {
		return TYPE;
	}

	@JsonIgnore
	@Override
	public FilterBuilder getFilterBuilder() {
		RangeFilterBuilder builder = new RangeFilterBuilder(field);
		if (from != null)
			builder.from(from.doubleValue());
		if (to != null)
			builder.to(to.doubleValue());
		return new BoolFilterBuilder().must(builder).cache(true);
	}

	public Double getFrom() {
		return from;
	}

	public void setFrom(Double from) {
		this.from = from;
	}

	public Double getTo() {
		return to;
	}

	public void setTo(Double to) {
		this.to = to;
	}

}
