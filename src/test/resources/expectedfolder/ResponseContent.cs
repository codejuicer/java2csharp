using System;

namespace com.github.ggerla.java2csharp.testclasses
{
	public class ResponseContent
	{
		private bool success;

		private long errorCode;

		private string errorDescription;

		protected internal ResponseContent()
			: base()
		{
		}

		public ResponseContent(bool success, long errorCode, string errorDescription)
			: base()
		{
			this.success = success;
			this.errorCode = errorCode;
			this.errorDescription = errorDescription;
		}

		public virtual bool Success
		{
			get
			{
				return success;
			}
			set
			{
				bool success = value;
				this.success = success;
			}
		}

		public virtual long ErrorCode
		{
			get
			{
				return errorCode;
			}
			set
			{
				long errorCode = value;
				this.errorCode = errorCode;
			}
		}

		public virtual string ErrorDescription
		{
			get
			{
				return errorDescription;
			}
			set
			{
				string errorDescription = value;
				this.errorDescription = errorDescription;
			}
		}
	}
}
