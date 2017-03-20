package poe.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class MarkdownStream
{
	public static Stream<Row> stream(final String markDown) throws IOException
	{
		final BufferedReader reader = new BufferedReader(new StringReader(markDown));

		final List<Row> rows = new ArrayList<>();
		String line;

		while ((line = reader.readLine()) != null)
		{
			rows.add(new Row(line));
		}

		return rows.stream();
	}

	public static class Row
	{
		private final String[] elements;

		public Row(final String line)
		{
			elements = line.split("\\|");
		}

		public String trimmed(final int index)
		{
			return elements[index].trim();
		}

		public int intValue(final int index)
		{
			return Integer.parseInt(trimmed(index));
		}

		public float floatValue(final int index)
		{
			return Float.parseFloat(trimmed(index));
		}

		public Optional<String> trimmedOptional(final int index)
		{
			if (index >= elements.length) { return Optional.empty(); }

			return Optional.of(trimmed(index));
		}
	}
}
