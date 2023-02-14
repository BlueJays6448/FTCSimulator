/* Copyright (c) 2014 Qualcomm Technologies Inc

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Qualcomm Technologies Inc nor the names of its contributors
may be used to endorse or promote products derived from this software without
specific prior written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. */

package com.qualcomm.robotcore.util;

import java.io.Serializable;

/**
 * Manage a serial number
 */
public class SerialNumber implements Serializable {

	private static final long serialVersionUID = 1L;
	private String serialNumber;

	/**
	 * Constructor - use default serial number
	 */
	public SerialNumber() {
		serialNumber = "N/A";
	}

	/**
	 * Constructor - use supplied serial number
	 * 
	 * @param serialNumber
	 */
	public SerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	/**
	 * Get the serial number
	 * 
	 * @return serial number
	 */
	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * Set the serial number
	 * 
	 * @param serialNumber
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	@Override
	public boolean equals(Object object) {
		if (object == null) return false;
		if (object == this) return true;

		if (object instanceof SerialNumber) {
			return serialNumber.equals(((SerialNumber) object).getSerialNumber());
		}

		if (object instanceof String) {
			return serialNumber.equals(object);
		}

		return false;
	}

	@Override
	public int hashCode() {
		return serialNumber.hashCode();
	}

	@Override
	public String toString() {
		return serialNumber;
	}

}