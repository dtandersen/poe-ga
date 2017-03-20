package poe.app.evolve.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import poe.app.evolve.CharacterView;
import poe.command.model.EvolutionStatus;

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
	public void update(final EvolutionStatus evolutionStatus)
	{
		final WebElement searchBox = driver.findElement(By.id("passive-tree-decode"));
		searchBox.sendKeys(evolutionStatus.getCharacter().getUrl());
		final WebElement button = driver.findElement(By.id("passive-tree-decode-button"));
		button.click();
	}

	@Override
	public void stop()
	{
		driver.quit();
	}
}
