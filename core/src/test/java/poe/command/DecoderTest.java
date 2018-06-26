package poe.command;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.api.Test;
import poe.entity.CharacterClass;

public class DecoderTest
{
	@Test
	public void decodeMarauder() throws IOException
	{
		final Info i = readInfo("AAAABAEAAHuM");

		assertThat(i.version, equalTo(4));
		assertThat(i.charId, equalTo(CharacterClass.MARAUDER.getId()));
		assertThat(i.ascId, equalTo(0));
		assertThat(i.fullScreen, equalTo(0));
	}

	@Test
	public void decodeWitch() throws IOException
	{
		final String string = "AAAABAMAAN-w";
		final Info i = readInfo(string);

		assertThat(i.version, equalTo(4));
		assertThat(i.charId, equalTo(CharacterClass.WITCH.getId()));
		assertThat(i.ascId, equalTo(0));
		assertThat(i.fullScreen, equalTo(0));
	}

	private Info readInfo(final String string) throws IOException
	{
		final byte[] bytes = Base64.decodeBase64(string);
		final DataInputStream targetReader = new DataInputStream(new ByteArrayInputStream(bytes));

		final Info i = new Info();
		i.version = targetReader.readInt();
		i.charId = targetReader.readByte();
		i.ascId = targetReader.readByte();
		i.fullScreen = targetReader.readByte();
		return i;
	}

	class Info
	{
		int version;

		int charId;

		int ascId;

		int fullScreen;
	}
}
