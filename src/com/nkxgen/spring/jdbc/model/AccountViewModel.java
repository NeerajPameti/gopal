package com.nkxgen.spring.jdbc.model;

public class AccountViewModel {
	private Long applicationId;
	private String accountTypeId;
	private String applicationNomineeFirstName;
	private String applicationNomineeLastName;
	private Long customerId;
	private String createdDate;
	private long createdBy;
	private long processedBy;
	private long balance;
	// Getters and setters

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public String getAccountTypeId() {
		return accountTypeId;
	}

	public void setAccountTypeId(String accountTypeId) {
		this.accountTypeId = accountTypeId;
	}

	public String getApplicationNomineeFirstName() {
		return applicationNomineeFirstName;
	}

	public void setApplicationNomineeFirstName(String applicationNomineeFirstName) {
		this.applicationNomineeFirstName = applicationNomineeFirstName;
	}

	public String getApplicationNomineeLastName() {
		return applicationNomineeLastName;
	}

	public void setApplicationNomineeLastName(String applicationNomineeLastName) {
		this.applicationNomineeLastName = applicationNomineeLastName;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCreated_date() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	public long getProcessedBy() {
		return processedBy;
	}

	public void setProcessedBy(long processedBy) {
		this.processedBy = processedBy;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance_amt) {
		this.balance = balance_amt;
	}

	public static AccountViewModel mapToViewModel(Account account) {
		AccountViewModel viewModel = new AccountViewModel();
		viewModel.setApplicationId(account.getApplicationId());
		viewModel.setAccountTypeId(account.getAccountTypeId());
		viewModel.setApplicationNomineeFirstName(account.getApplicationNomineeFirstName());
		viewModel.setApplicationNomineeLastName(account.getApplicationNomineeLastName());
		viewModel.setCustomerId(account.getCustomer().getId());
		viewModel.setCreatedDate(account.getCreated_date());
		viewModel.setCreatedBy(account.getCreatedByUser().getBusr_id());
		viewModel.setProcessedBy(account.getProcessedByUser().getBusr_id());
		viewModel.setBalance(account.getBalance());

		return viewModel;
	}
}
