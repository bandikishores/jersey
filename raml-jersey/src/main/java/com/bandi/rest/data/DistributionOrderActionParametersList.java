package com.bandi.rest.data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;

@Data
@JsonRootName("DistributionOrderActionParametersList")
public class DistributionOrderActionParametersList {
	private List<DistributionOrderActionParameters> elements;
}
