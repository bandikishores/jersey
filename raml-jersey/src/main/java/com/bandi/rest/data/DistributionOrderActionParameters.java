package com.bandi.rest.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistributionOrderActionParameters {
	private int distributionOrderId;
	private int companyId;
	private String tcDistributionOrderId;
	private String lastUpdatedDTTMHid = null;
}
