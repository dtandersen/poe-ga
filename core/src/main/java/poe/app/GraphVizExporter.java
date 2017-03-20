package poe.app;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import poe.entity.PassiveSkill;
import poe.repository.PassiveSkillRepository;

@SpringBootApplication
@ComponentScan(basePackages = { "poe.app.config" }, excludeFilters = {})
public class GraphVizExporter implements CommandLineRunner
{
	@Autowired
	private PassiveSkillRepository repo;

	@Override
	public void run(final String... args) throws IOException
	{
		final List<PassiveSkill> passives = repo.all();

		final FileWriter graphviz = new FileWriter("passives.dot");
		try
		{
			graphviz.write("graph {\n");
			graphviz.write("root = 58833;\n");
			graphviz.write("layout=neato;\n");
			graphviz.write("epsilon=.01;\n");
			graphviz.write("overlap = false;\n");
			graphviz.write("node [shape=box];\n");
			graphviz.write("splines = true;\n");
			graphviz.write("sep = .1;\n");
			for (final PassiveSkill passive : passives)
			{
				for (final int node : passive.getOutputs())
				{
					graphviz.write(passive.getId() + " -- " + node + ";\n");
				}
			}

			for (final PassiveSkill passive : passives)
			{
				final StringBuffer sb = new StringBuffer();
				sb.append(passive.getId());
				sb.append(" [label=\"" + passive.getId() + "\\n" + passive.getName() + "\"");

				if (passive.isClassStartingNode())
				{
					sb.append(",color=red");

				}
				sb.append("];\n");

				graphviz.write(sb.toString());
			}
			graphviz.write("}\n");
		} finally
		{
			graphviz.close();
		}
	}

	public static void main(final String[] args) throws Exception
	{
		SpringApplication.run(GraphVizExporter.class, args);
	}
}
