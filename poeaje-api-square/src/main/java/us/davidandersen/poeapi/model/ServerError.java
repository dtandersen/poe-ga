package us.davidandersen.poeapi.model;

@SuppressWarnings("serial")
public class ServerError extends RuntimeException
{
	public ServerError(final int code)
	{
		super("" + code);
	}
}
