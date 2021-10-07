package com.ssafy.billige.contract.dto.response;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContractUnavailableResponse {

    List<LocalDate> unavailableList;
}
