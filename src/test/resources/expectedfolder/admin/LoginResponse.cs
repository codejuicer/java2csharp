using System;
using System.Collections.Generic;
using com.github.ggerla.java2csharp.testclasses;
using com.github.ggerla.java2csharp.testclasses.admin;

namespace com.github.ggerla.java2csharp.testclasses.admin
{
	public class LoginResponse : Message
	{
		private ResponseContent content;

		private Information information;

		private IList<ContentArea> areas;

		protected internal LoginResponse()
			: base()
		{
		}

		public LoginResponse(Header header, ResponseContent content, Information information
			, IList<ContentArea> areas)
			: base(header)
		{
			this.content = content;
			this.information = information;
			this.areas = areas;
		}

		public virtual ResponseContent Content
		{
			get
			{
				return content;
			}
			set
			{
				ResponseContent content = value;
				this.content = content;
			}
		}

		public virtual Information Information
		{
			get
			{
				return information;
			}
			set
			{
				Information information = value;
				this.information = information;
			}
		}

		public virtual IList<ContentArea> Areas
		{
			get
			{
				return areas;
			}
			set
			{
				IList<ContentArea> areas = value;
				this.areas = areas;
			}
		}
	}
}
