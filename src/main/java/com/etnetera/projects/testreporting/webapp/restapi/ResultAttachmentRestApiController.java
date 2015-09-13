package com.etnetera.projects.testreporting.webapp.restapi;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.etnetera.projects.testreporting.webapp.model.elasticsearch.result.Result;
import com.etnetera.projects.testreporting.webapp.model.elasticsearch.result.ResultAttachment;
import com.etnetera.projects.testreporting.webapp.model.mongodb.user.Permission;
import com.etnetera.projects.testreporting.webapp.repository.elasticsearch.result.ResultRepository;
import com.etnetera.projects.testreporting.webapp.user.UserHelper;
import com.mongodb.gridfs.GridFSDBFile;

@RestController
@RequestMapping(value = "/api")
public class ResultAttachmentRestApiController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResultAttachmentRestApiController.class);

	@Autowired
	private ResultRepository resultRepository;

	@RequestMapping(value = "/results/attachment/create/{resultId}", method = RequestMethod.POST, produces = "application/json")
	public ResultAttachment createResultAttachment(@PathVariable String resultId, @RequestParam MultipartFile file)
			throws IOException {
		Result result = resultRepository.findOne(resultId);
		UserHelper.checkProjectPermission(result.getProjectId(), Permission.EDITOR);
		return resultRepository.createAttachment(result, file);
	}

	@RequestMapping(value = "/results/attachment/update/{resultId}/{attachmentId}", method = RequestMethod.POST, produces = "application/json")
	public ResultAttachment updateResultAttachment(@PathVariable String resultId, @PathVariable String attachmentId,
			@RequestParam MultipartFile file) throws IOException {
		Result result = resultRepository.findOne(resultId);
		UserHelper.checkProjectPermission(result.getProjectId(), Permission.EDITOR);
		return resultRepository.updateAttachment(result, attachmentId, file);
	}
	
	@RequestMapping(value = "/results/attachment/delete/{resultId}/{attachmentId}", method = RequestMethod.GET, produces = "application/json")
	public void deleteResultAttachment(@PathVariable String resultId, @PathVariable String attachmentId) {
		Result result = resultRepository.findOne(resultId);
		UserHelper.checkProjectPermission(result.getProjectId(), Permission.EDITOR);
		resultRepository.deleteAttachment(result, attachmentId);
	}

	@RequestMapping(value = "/results/attachment/get/{resultId}/{attachmentId}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getResultAttachment(@PathVariable String resultId,
			@PathVariable String attachmentId) {
		Result result = resultRepository.findOne(resultId);
		UserHelper.checkProjectPermission(result.getProjectId(), Permission.BASIC);
		GridFSDBFile file = resultRepository.getAttachmentFile(result.getAttachment(attachmentId));
		if (file == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		try {
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.setContentType(MediaType.parseMediaType(file.getContentType()));
			responseHeaders.setContentDispositionFormData(file.getFilename(), file.getFilename());
			responseHeaders.setContentLength(file.getLength());
			responseHeaders.setCacheControl("max-age=7200, must-revalidate");

			return new ResponseEntity<>(IOUtils.toByteArray(file.getInputStream()), responseHeaders, HttpStatus.OK);
		} catch (IOException e) {
			LOGGER.error("Cannot read byte[] for file {}", file.getId(), e);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
