using System;
using System.Collections.Generic;

namespace org.codejuicer.java2csharp.testclasses.admin
{
	public class Information
	{
		private int id;

		private int defaultRole;

		private string name;

		private string surname;

		private string idNumber;

		private string telephone;

		private string mobile;

		private string fax;

		private string email;

		private string department;

		private string address;

		private bool enabled;

		private string username;

		private string password;

		private IList<int?> roles;

		public Information(int id, int defaultRole, string name, string surname, string idNumber
			, string telephone, string mobile, string fax, string email, string department, 
			string address, bool enabled, string username, string password, IList<int?> roles
			)
			: base()
		{
			this.id = id;
			this.defaultRole = defaultRole;
			this.name = name;
			this.surname = surname;
			this.idNumber = idNumber;
			this.telephone = telephone;
			this.mobile = mobile;
			this.fax = fax;
			this.email = email;
			this.department = department;
			this.address = address;
			this.enabled = enabled;
			this.username = username;
			this.password = password;
			this.roles = roles;
		}

		protected internal Information()
			: base()
		{
		}

		public virtual int Id
		{
			get
			{
				return id;
			}
			set
			{
				int id = value;
				this.id = id;
			}
		}

		public virtual int DefaultRole
		{
			get
			{
				return defaultRole;
			}
			set
			{
				int defaultRole = value;
				this.defaultRole = defaultRole;
			}
		}

		public virtual string Name
		{
			get
			{
				return name;
			}
			set
			{
				string name = value;
				this.name = name;
			}
		}

		public virtual string Surname
		{
			get
			{
				return surname;
			}
			set
			{
				string surname = value;
				this.surname = surname;
			}
		}

		public virtual string IdNumber
		{
			get
			{
				return idNumber;
			}
			set
			{
				string idNumber = value;
				this.idNumber = idNumber;
			}
		}

		public virtual string Telephone
		{
			get
			{
				return telephone;
			}
			set
			{
				string telephone = value;
				this.telephone = telephone;
			}
		}

		public virtual string Mobile
		{
			get
			{
				return mobile;
			}
			set
			{
				string mobile = value;
				this.mobile = mobile;
			}
		}

		public virtual string Fax
		{
			get
			{
				return fax;
			}
			set
			{
				string fax = value;
				this.fax = fax;
			}
		}

		public virtual string Email
		{
			get
			{
				return email;
			}
			set
			{
				string email = value;
				this.email = email;
			}
		}

		public virtual string Department
		{
			get
			{
				return department;
			}
			set
			{
				string department = value;
				this.department = department;
			}
		}

		public virtual string Address
		{
			get
			{
				return address;
			}
			set
			{
				string address = value;
				this.address = address;
			}
		}

		public virtual bool Enabled
		{
			get
			{
				return enabled;
			}
			set
			{
				bool enabled = value;
				this.enabled = enabled;
			}
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

		public virtual IList<int?> Roles
		{
			get
			{
				return roles;
			}
			set
			{
				IList<int?> roles = value;
				this.roles = roles;
			}
		}
	}
}
