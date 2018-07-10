package us.davidandersen.poeapi.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import us.davidandersen.poeapi.square.SquarePoeApiClientTest;

public class ResourceUtil
{

	public static String getFile(final String fileName) throws IOException, URISyntaxException
	{
		return new String(Files.readAllBytes(Paths.get(SquarePoeApiClientTest.class.getClassLoader().getResource(fileName).toURI())));
	}

}
