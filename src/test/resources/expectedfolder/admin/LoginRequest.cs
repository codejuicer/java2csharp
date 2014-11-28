using System;
using com.google.devtools.java2csharp.testclasses;
using com.google.devtools.java2csharp.testclasses.admin;

namespace com.google.devtools.java2csharp.testclasses.admin
{
	public class LoginRequest : Message
	{
		private string username;

		private string password;

		protected internal LoginRequest()
			: base()
		{
		}

		public LoginRequest(Header header, string username, string password)
			: base(header)
		{
			this.username = username;
			this.password = password;
		}

		public virtual string Username
		{
			get
			{
				return username;
			}
			set
			{
				string username = value;
				this.username = username;
			}
		}

		public virtual string Password
		{
			get
			{
				return password;
			}
			set
			{
				string password = value;
				this.password = password;
			}
		}
	}
}
