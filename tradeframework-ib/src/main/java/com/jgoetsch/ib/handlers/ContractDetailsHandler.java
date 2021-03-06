/*
 * Copyright (c) 2012 Jeremy Goetsch
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jgoetsch.ib.handlers;

import com.jgoetsch.ib.TWSUtils;
import com.jgoetsch.tradeframework.ContractDetails;

public class ContractDetailsHandler extends BaseIdHandler {

	private ContractDetails contractDetails;

	private boolean isDone = false;

	public ContractDetailsHandler(int tickerId) {
		super(tickerId);
	}

	@Override
	public int getStatus() {
		return isDone ? STATUS_SUCCESS : super.getStatus();
	}

	@Override
	protected void onContractDetails(com.ib.client.ContractDetails contractDetails) {
		this.contractDetails = TWSUtils.fromTWSContractDetails(contractDetails);
		onContractDetailsEnd();
	}

	@Override
	protected synchronized void onContractDetailsEnd() {
		isDone = true;
		this.notifyAll();
	}

	@Override
	public String toString() {
		return "ContractDetailsHandler: " + contractDetails.toString();
	}

	public ContractDetails getContractDetails() {
		return contractDetails;
	}
}
