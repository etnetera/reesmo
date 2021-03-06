package cz.etnetera.reesmo.restapi.output;

import org.springframework.data.domain.Page;

import java.util.List;

public class RestApiList<T> {
	
	public PageData page;
	
	public List<T> items;
	
	public RestApiList(Page<T> page) {
		this.page = new PageData(page);
		items = page.getContent();
	}
	
	public class PageData {
		
		public int number;
		
		public int size;
		
		public int totalPages;
		
		public long totalElements;
		
		public PageData(Page<T> page) {
			number = page.getNumber();
			size = page.getSize();
			totalPages = page.getTotalPages();
			totalElements = page.getTotalElements();
		}
		
	}
	
}
