package cz.etnetera.reesmo.model.elasticsearch.result;

import cz.etnetera.reesmo.model.elasticsearch.ElasticAuditedModel;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

public class ResultLink extends ElasticAuditedModel {
	
	@Field(type = FieldType.String)
	private String name;
	
	@Field(type = FieldType.String, index = FieldIndex.not_analyzed)
	private String url;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
