package poe.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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

	public static Stream<Row> stream(final String[] markdown)
	{
		final List<Row> rows = new ArrayList<>();
		final List<String> headers = new ArrayList<>();

		for (final String line : markdown)
		{
			if (headers.isEmpty())
			{
				final String[] h = line.split("\\|");
				Arrays.stream(h).forEach(x -> headers.add(x.trim()));
			}
			else
			{
				rows.add(new Row(headers, line));
			}
		}

		return rows.stream();
	}

	public static class Row
	{
		private final String[] elements;

		private List<String> headers;

		public Row(final String line)
		{
			elements = line.split("\\|");
		}

		public Row(final List<String> headers, final String line)
		{
			this.headers = headers;
			elements = line.split("\\|");
		}

		public String trimmed(final String column)
		{
			final int index = headers.indexOf(column);
			return trimmed(index);
		}

		public String trimmed(final int index)
		{
			return elements[index].trim();
		}

		public int intValue(final int index)
		{
			return Integer.parseInt(trimmed(index));
		}

		public int intValue(final String column)
		{
			return Integer.parseInt(trimmed(column));
		}

		public float floatValue(final int index)
		{
			return Float.parseFloat(trimmed(index));
		}

		public float floatValue(final String columnName)
		{
			return floatValue(headers.indexOf(columnName));
		}

		public Optional<String> trimmedOptional(final int index)
		{
			if (index >= elements.length) { return Optional.empty(); }

			return Optional.of(trimmed(index));
		}
	}

	public static List<Row> asList(final String... markdown)
	{
		return stream(markdown).collect(Collectors.toList());
	}
}
