using System;
using com.github.ggerla.java2csharp.testclasses;

namespace com.github.ggerla.java2csharp.testclasses.admin
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
