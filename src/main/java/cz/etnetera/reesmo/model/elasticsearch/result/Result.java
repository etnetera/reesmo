package cz.etnetera.reesmo.model.elasticsearch.result;

import cz.etnetera.reesmo.model.elasticsearch.ElasticAuditedModel;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(indexName = "#{@elasticsearchIndexName}", type = "result")
public class Result extends ElasticAuditedModel {
	
	@Id
	private String id;
	
	@Field(type = FieldType.String, index = FieldIndex.not_analyzed)
	private String projectId;
	
	/**
	 * Suite name.
	 */
	@Field(type = FieldType.String)
	private String suite;
	
	/**
	 * Unique suite run identifier. It should be unique
	 * only for given suite. Best way is to use timestamp.
	 */
	@Field(type = FieldType.String, index = FieldIndex.not_analyzed)
	private String suiteId;
	
	/**
	 * Name for job, i.e. group of tests. It will usually be set 
	 * in CI or build management tool and it indicates
	 * job name which is running all the tests including
	 * suites. For example in Teamcity it will be the name 
	 * of Build, in Jenkins the name of Job.
	 */
	@Field(type = FieldType.String)
	private String job;
	
	/**
	 * Unique job run identifier. It should be unique
	 * only for given job. Best way is to use timestamp.
	 */
	@Field(type = FieldType.String, index = FieldIndex.not_analyzed)
	private String jobId;
	
	@Field(type = FieldType.String)
	private String milestone;
	
	@Field(type = FieldType.String)
	private String name;
	
	@Field(type = FieldType.String)
	private String description;
	
	@Field(type = FieldType.String)
	private String environment;
	
	@Field(type = FieldType.String)
	private String author;
	
	@Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
	private Date startedAt;
	
	@Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
	private Date endedAt;
	
	@Field(type = FieldType.Long)
	private Long length;
	
	@Field(type = FieldType.String, index = FieldIndex.not_analyzed)
	private TestStatus status;
	
	@Field(type = FieldType.String, index = FieldIndex.not_analyzed)
	private TestSeverity severity;
	
	@Field(type = FieldType.Boolean)
	private boolean automated;
	
	@Field(type = FieldType.String)
	private List<String> labels = new ArrayList<>();
	
	@Field(type = FieldType.String)
	private List<String> notes = new ArrayList<>();
	
	@Field(type = FieldType.String)
	private List<String> errors = new ArrayList<>();
	
	@Field(type = FieldType.String, index = FieldIndex.not_analyzed)
	private List<String> categories = new ArrayList<>();
	
	@Field(type = FieldType.String, index = FieldIndex.not_analyzed)
	private List<String> types = new ArrayList<>();
	
	@Field(type = FieldType.Nested)
	private List<ResultAttachment> attachments = new ArrayList<>();
	
	@Field(type = FieldType.Nested)
	private List<ResultLink> links = new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getSuite() {
		return suite;
	}

	public void setSuite(String suite) {
		this.suite = suite;
	}

	public String getSuiteId() {
		return suiteId;
	}

	public void setSuiteId(String suiteId) {
		this.suiteId = suiteId;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getMilestone() {
		return milestone;
	}

	public void setMilestone(String milestone) {
		this.milestone = milestone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getStartedAt() {
		return startedAt;
	}

	public void setStartedAt(Date startedAt) {
		this.startedAt = startedAt;
	}

	public Date getEndedAt() {
		return endedAt;
	}

	public void setEndedAt(Date endedAt) {
		this.endedAt = endedAt;
	}

	public Long getLength() {
		return length;
	}

	public void setLength(Long length) {
		this.length = length;
	}

	public TestStatus getStatus() {
		return status;
	}

	public void setStatus(TestStatus status) {
		this.status = status;
	}

	public TestSeverity getSeverity() {
		return severity;
	}

	public void setSeverity(TestSeverity severity) {
		this.severity = severity;
	}

	public boolean isAutomated() {
		return automated;
	}

	public void setAutomated(boolean automated) {
		this.automated = automated;
	}
	
	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}

	public List<String> getNotes() {
		return notes;
	}

	public void setNotes(List<String> notes) {
		this.notes = notes;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}

	public List<ResultAttachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<ResultAttachment> attachments) {
		this.attachments = attachments;
	}

	public List<ResultLink> getLinks() {
		return links;
	}

	public void setLinks(List<ResultLink> links) {
		this.links = links;
	}
	
	public ResultAttachment getAttachment(String attachmentId) {
		return attachments.stream().filter(a -> a.getId().equals(attachmentId)).findFirst().orElse(null);
	}
	
	public ResultAttachment getAttachmentByPath(String path) {
		return attachments.stream().filter(a -> a.getPath() == null ? false : a.getPath().equals(path)).findFirst().orElse(null);
	}
	
	public ResultAttachment addAttachment(ResultAttachment attachment) {
		attachments.add(attachment);
		return attachment;
	}
	
	public void removeAttachment(ResultAttachment attachment) {
		attachments.remove(attachment); 
	}
	
}
