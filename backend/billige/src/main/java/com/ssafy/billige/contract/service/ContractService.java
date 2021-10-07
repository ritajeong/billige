package com.ssafy.billige.contract.service;

import com.ssafy.billige.contract.dto.request.ContractRequest;
import com.ssafy.billige.contract.dto.response.ContractUnavailableResponse;

public interface ContractService {
	void contractSave(ContractRequest contractRequest);

	public boolean lastCheck(ContractRequest contractRequest);

	public ContractUnavailableResponse getUnavailableDate(String tokenEmail, Long itemId);
}
