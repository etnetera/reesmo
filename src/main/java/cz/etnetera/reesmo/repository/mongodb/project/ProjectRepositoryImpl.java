package cz.etnetera.reesmo.repository.mongodb.project;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import cz.etnetera.reesmo.message.Localizer;
import cz.etnetera.reesmo.model.datatables.project.ProjectDT;
import cz.etnetera.reesmo.model.datatables.project.ProjectGroupProjectDT;
import cz.etnetera.reesmo.model.datatables.user.UserProjectDT;
import cz.etnetera.reesmo.model.mongodb.project.Project;
import cz.etnetera.reesmo.model.mongodb.project.ProjectGroup;
import cz.etnetera.reesmo.model.mongodb.user.User;
import cz.etnetera.reesmo.model.mongodb.view.View;
import cz.etnetera.reesmo.repository.mongodb.MongoDatatables;
import cz.etnetera.reesmo.repository.mongodb.view.ViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Project repository custom method implementation
 */
public class ProjectRepositoryImpl implements ProjectRepositoryCustom {
	
	@Autowired
	private MongoOperations mongoTemplate;
	
	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private ViewRepository viewRepository;

	@Autowired
	private Localizer localizer;

	@Override
	public void deleteProject(Project project) {
		List<View> views = mongoTemplate.find(Query.query(Criteria.where("projectId").is(project.getId())), View.class, "view");
		views.forEach(view -> viewRepository.deleteViewAndMonitors(view.getId()));
		projectRepository.delete(project);
	}
	
	@Override
	public Project findOneByKey(String key) {
		return mongoTemplate.findOne(Query.query(Criteria.where("key").is(key)), Project.class);
	}
	
	@Override
	public List<Project> findByUser(String userId) {
		return mongoTemplate.find(Query.query(Criteria.where("users." + userId).exists(true)), Project.class);
	}

	@Override
	public DataSet<ProjectDT> findWithDatatablesCriterias(DatatablesCriterias criterias, List<String> projectIds) {
		DataSet<Project> projects = findProjectsWithDatatablesCriterias(criterias, projectIds);

		return new DataSet<ProjectDT>(
				projects.getRows().stream().map(ProjectDT::new).collect(Collectors.toList()),
				projects.getTotalRecords(), projects.getTotalDisplayRecords());
	}

	@Override
	public DataSet<ProjectGroupProjectDT> findProjectGroupProjectsWithDatatablesCriterias(DatatablesCriterias criterias,
			ProjectGroup projectGroup) {
		DataSet<Project> projects = findProjectsWithDatatablesCriterias(criterias, projectGroup.getProjects());

		return new DataSet<ProjectGroupProjectDT>(projects.getRows().stream().map(p -> {
			return new ProjectGroupProjectDT(p, projectGroup);
		}).collect(Collectors.toList()), projects.getTotalRecords(), projects.getTotalDisplayRecords());
	}
	
	@Override
	public DataSet<UserProjectDT> findUserProjectsWithDatatablesCriterias(DatatablesCriterias criterias, User user, Locale locale) {
		DataSet<Project> projects = findProjectsWithDatatablesCriterias(criterias, user.getProjectsPermissions().keySet());

		return new DataSet<UserProjectDT>(projects.getRows().stream().map(p -> {
			return new UserProjectDT(p, user, user.getProjectsPermissions().get(p.getId()), localizer, locale);
		}).collect(Collectors.toList()), projects.getTotalRecords(), projects.getTotalDisplayRecords());
	}
	
	private DataSet<Project> findProjectsWithDatatablesCriterias(DatatablesCriterias criterias, Collection<String> projectIds) {
		Criteria allCrit;
		if (projectIds == null) {
			allCrit = Criteria.where("_id").exists(true);
		} else if (projectIds.isEmpty()) {
			return new DataSet<Project>(new ArrayList<>(), 0L, 0L);
		} else {
			allCrit = Criteria.where("_id").in(projectIds);
		}

		Criteria crit = MongoDatatables.getCriteria(criterias, allCrit);

		Long count = mongoTemplate.count(Query.query(allCrit), Project.class);
		Long countFiltered = mongoTemplate.count(Query.query(crit), Project.class);
		
		Query query = Query.query(crit);
		MongoDatatables.sortQuery(query, criterias);
		MongoDatatables.paginateQuery(query, criterias);

		List<Project> users = mongoTemplate.find(query, Project.class);

		return new DataSet<Project>(users, count, countFiltered);
	}

}
