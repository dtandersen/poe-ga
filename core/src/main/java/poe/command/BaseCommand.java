package poe.command;

public abstract class BaseCommand<REQ, RES>
{
	protected RES result;

	public REQ request;

	public void setRequest(final REQ request)
	{
		this.request = request;
	}

	public void setResult(final RES result)
	{
		this.result = result;
	}

	abstract public void execute();

	interface VoidRequest
	{
	}

	interface VoidResult
	{
	}
}
