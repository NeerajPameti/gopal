package com.nkxgen.spring.jdbc.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nkxgen.spring.jdbc.Dao.AuditLogDAO;
import com.nkxgen.spring.jdbc.Dao.AuditLogRepository;
import com.nkxgen.spring.jdbc.model.AuditLogs;


@Controller
public class AuditLogsController {
	
	
	private  AuditLogDAO auditLogDAO;
	  private  AuditLogRepository auditLogRepository;

    @Autowired
    public AuditLogsController(AuditLogDAO auditLogDAO,AuditLogRepository auditLogRepository) {
        this.auditLogDAO = auditLogDAO;
        this.auditLogRepository = auditLogRepository;
    }
    

    
	@RequestMapping(value="logs")
	public String listAuditLogs(Model model)
	{
		List<AuditLogs>auditLogs = auditLogDAO.getAllAuditLogs();
        model.addAttribute("auditLogs", auditLogs);
		return "auditlogs";
	}
	
	@GetMapping("/auditLogs")
	public String getAuditLogs(
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "10") int pageSize,
	        Model model ) 
	{
	    // Create Pageable object for pagination
		org.springframework.data.domain.PageRequest pageable = org.springframework.data.domain.PageRequest.of(page, pageSize, Sort.by("timestamp").descending());

	    // Retrieve the audit logs for the requested page
	    Page<AuditLogs> auditLogPage = auditLogRepository.findAll(pageable);

	    model.addAttribute("auditLogs", auditLogPage.getContent());
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", auditLogPage.getTotalPages());
	    
	    // Calculate the range of page numbers to display
	    int totalPages = auditLogPage.getTotalPages();
	    int maxPageNumbers = 10; // Maximum number of page numbers to display

	    int startPage;
	    int endPage;

	    if (totalPages <= maxPageNumbers) {
	        // If the total number of pages is less than or equal to the maximum, display all pages
	        startPage = 1;
	        endPage = totalPages;
	    } else {
	        // Calculate the start and end page based on the current page
	        int middlePage = (int) Math.ceil(maxPageNumbers / 2.0);

	        if (page <= middlePage) {
	            // If the current page is close to the beginning, display the first maxPageNumbers pages
	            startPage = 1;
	            endPage = maxPageNumbers;
	        } else if (page > totalPages - middlePage) {
	            // If the current page is close to the end, display the last maxPageNumbers pages
	            startPage = totalPages - maxPageNumbers + 1;
	            endPage = totalPages;
	        } else {
	            // Display a range of maxPageNumbers pages centered around the current page
	            startPage = page - middlePage + 1;
	            endPage = page + middlePage;
	        }
	    }

	    List<Integer> pageNumbers = IntStream.iterate(startPage, i -> i + 1)
	            .limit(endPage - startPage + 1)
	            .boxed()
	            .collect(Collectors.toList());


	    model.addAttribute("pageNumbers", pageNumbers);

	    return "auditlogs";
	}

}
