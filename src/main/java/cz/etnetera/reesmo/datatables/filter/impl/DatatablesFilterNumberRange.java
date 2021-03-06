package cz.etnetera.reesmo.datatables.filter.impl;

import cz.etnetera.reesmo.datatables.filter.DatatablesFilter;
import cz.etnetera.reesmo.message.Localizer;

import java.util.Locale;

public class DatatablesFilterNumberRange extends DatatablesFilter {

	public static final String TYPE = "numberrange";
	
	public DatatablesFilterNumberRange(String field, String label) {
		super(field, label);
	}
	
	public DatatablesFilterNumberRange(String field, String labelKey, Localizer localizer, Locale locale) {
		this(field, localizer.getMessageSource().getMessage(labelKey, null, locale));
	}

	@Override
	public String getType() {
		return TYPE;
	}
	
}
