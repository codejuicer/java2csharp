/*
 * Copyright 2014 Giuseppe Gerla. All Rights Reserved.
 *
 * Licensed under the GNU General Public License, Version 2.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.gnu.org/licenses/gpl2.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.codejuicer.java2csharp.testclasses.admin;

import java.util.List;

import org.codejuicer.java2csharp.testclasses.Header;
import org.codejuicer.java2csharp.testclasses.ResponseContent;

public class LoginResponse extends Message {
	private ResponseContent content;

	private Information information;

	private List<ContentArea> areas;

	protected LoginResponse() {
		super();
	}

	public LoginResponse(Header header,
			ResponseContent content, Information information,
			List<ContentArea> areas) {
		super(header);
		this.content = content;
		this.information = information;
		this.areas = areas;
	}

	public ResponseContent getContent() {
		return content;
	}

	public void setContent(ResponseContent content) {
		this.content = content;
	}

	public Information getInformation() {
		return information;
	}

	public void setInformation(Information information) {
		this.information = information;
	}

	public List<ContentArea> getAreas() {
		return areas;
	}

	public void setAreas(List<ContentArea> areas) {
		this.areas = areas;
	}
}
