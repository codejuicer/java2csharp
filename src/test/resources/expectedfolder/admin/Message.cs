using System;
using com.google.devtools.java2csharp.testclasses;

namespace com.google.devtools.java2csharp.testclasses.admin
{
	public abstract class Message
	{
		private Header header;

		protected internal Message()
			: base()
		{
		}

		public Message(Header header)
			: base()
		{
			this.header = header;
		}

		public virtual Header Header
		{
			get
			{
				return header;
			}
			set
			{
				Header header = value;
				this.header = header;
			}
		}
	}
}
