/*
 *  Copyright 2012-2013 Marcelo Portilho
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package io.github.mportilho.mathsentenceparser.operation.datetime;

/**
 * A component list of a date/time representation.
 * 
 * @author Marcelo Portilho
 * 
 */
public enum DateElementEnum {

	DAY("Days"), MONTH("Months"), YEAR("Years"), SECOND("Seconds"), MINUTE("Minutes"), HOUR("Hours");

	private String mnemonic;

	private DateElementEnum(String mnemonic) {
		this.mnemonic = mnemonic;
	}

	@Override
	public String toString() {
		return mnemonic;
	}

}
