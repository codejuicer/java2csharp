using System;

namespace com.google.devtools.java2csharp.testclasses.admin
{
	public class ContentArea
	{
		private int id;

		private string name;

		private bool managed;

		protected internal ContentArea()
			: base()
		{
		}

		public ContentArea(int id, string name, bool managed)
			: base()
		{
			this.id = id;
			this.name = name;
			this.managed = managed;
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

		public virtual bool Managed
		{
			get
			{
				return managed;
			}
			set
			{
				bool managed = value;
				this.managed = managed;
			}
		}
	}
}
