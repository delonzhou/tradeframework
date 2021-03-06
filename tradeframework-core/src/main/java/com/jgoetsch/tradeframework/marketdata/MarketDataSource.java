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
package com.jgoetsch.tradeframework.marketdata;

import java.io.Closeable;
import java.io.IOException;

import com.jgoetsch.tradeframework.Contract;
import com.jgoetsch.tradeframework.InvalidContractException;
import com.jgoetsch.tradeframework.data.ContractDataSource;

public interface MarketDataSource extends ContractDataSource<MarketData>, Closeable {

	/**
	 * Returns a current snapshot of market data from the specified contract.
	 */
	public MarketData getMktDataSnapshot(Contract contract) throws IOException, InvalidContractException;

	/**
	 * Begin receiving callbacks to a MarketDataListener with market data updates for the given contract
	 */
	public void subscribeMarketData(Contract contract, MarketDataListener marketDataListener) throws IOException, InvalidContractException;

	/**
	 * Stops callbacks to a MarketDataListener for the given contract
	 */
	public void cancelMarketData(Contract contract, MarketDataListener marketDataListener) throws IOException;

}
