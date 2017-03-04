package poe.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import poe.entity.ImmutableCharacter;
import poe.repository.CharacterView;

public class SeleniumCharacterView implements CharacterView
{
	private WebDriver driver;

	public SeleniumCharacterView()
	{
	}

	@Override
	public void start()
	{
		driver = new ChromeDriver();
		driver.get("http://poeplanner.com");
	}

	@Override
	public void update(final ImmutableCharacter character)
	{
		final WebElement searchBox = driver.findElement(By.id("passive-tree-decode"));
		searchBox.sendKeys(character.getUrl());
		final WebElement button = driver.findElement(By.id("passive-tree-decode-button"));
		button.click();
	}

	@Override
	public void stop()
	{
		driver.quit();
	}
}
