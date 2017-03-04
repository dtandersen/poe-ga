package poe.app.evolve;

import java.io.FileNotFoundException;
import java.io.FileReader;
import org.yaml.snakeyaml.Yaml;

public class ConfigParser
{
	public static EvolveConfig read(final String filename) throws FileNotFoundException
	{
		final Yaml yaml = new Yaml();
		return yaml.loadAs(new FileReader(filename), EvolveConfig.class);
	}
}
