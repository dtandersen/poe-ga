package poe.command;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.List;
import poe.entity.CharacterClass;

public class PoeComUrlBuilder
{
	private final int version = 4;

	private int characterClassId;

	private int asccendencyId;

	private int fullScreen;

	private List<Integer> passiveSkillIds;

	public PoeComUrlBuilder withPassiveSkillIds(final List<Integer> passiveSkillIds)
	{
		this.passiveSkillIds = passiveSkillIds;
		return this;
	}

	public PoeComUrlBuilder withCharacterClass(final CharacterClass characterClass)
	{
		characterClassId = characterClass.getId();
		return this;
	}

	public String toUrl()
	{
		try
		{
			final byte[] bytes = characterToBytes();
			final String base64Url = encodeBase64(bytes);

			return base64Url;
		} catch (final IOException e)
		{
			throw new RuntimeException(e);
		}
	}

	private String encodeBase64(final byte[] bytes)
	{
		final Encoder base64Encoder = Base64.getUrlEncoder();

		final String encoded = base64Encoder.encodeToString(bytes);
		return "https://www.pathofexile.com/passive-skill-tree/" + encoded;
	}

	private byte[] characterToBytes() throws IOException
	{
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		final DataOutputStream dos = new DataOutputStream(outputStream);
		dos.writeInt(version);
		dos.writeByte(characterClassId);
		dos.writeByte(asccendencyId);
		dos.writeByte(fullScreen);

		for (final int skillId : passiveSkillIds)
		{
			dos.writeShort(skillId);
		}

		final byte[] bytes = outputStream.toByteArray();
		return bytes;
	}
}
