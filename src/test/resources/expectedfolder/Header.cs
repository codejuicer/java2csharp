using System;

namespace com.google.devtools.java2csharp.testclasses
{
	public class Header
	{
		private int sequence;

		private long timestamp;

		protected internal Header()
			: base()
		{
		}

		public Header(int sequence, long timestamp)
			: base()
		{
			this.sequence = sequence;
			this.timestamp = timestamp;
		}

		public virtual int Sequence
		{
			get
			{
				return sequence;
			}
			set
			{
				int sequence = value;
				this.sequence = sequence;
			}
		}

		public virtual long Timestamp
		{
			get
			{
				return timestamp;
			}
			set
			{
				long timestamp = value;
				this.timestamp = timestamp;
			}
		}
	}
}
