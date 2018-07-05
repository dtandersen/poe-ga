package poe.app.evolve.selenium;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
		final WebDriverWait wait = new WebDriverWait(driver, 10);
		final WebElement builds = driver.findElement(By.className("user-builds-content-container"));
		if (builds.isDisplayed())
		{
			final WebElement closeButton = driver.findElement(By.cssSelector("#user-builds-toggle"));
			closeButton.click();
		}
		closeAds(By.className("fixed-mobile-close"));
		closeAds(By.className("tynt-close-btn-container"));

		waitUntilNoAds();
		closeBackdrop();
		importPassiveSkillTree(evolutionStatus, wait);
		closeBackdrop();
	}

	private void waitUntilNoAds()
	{
		isNotPresentOrInvisible(By.className("fixed-mobile-close"));
		isNotPresentOrInvisible(By.className("tynt-close-btn-container"));
	}

	private void isNotPresentOrInvisible(final By className)
	{
		try
		{
			final WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.invisibilityOf(driver.findElement(className)));
		}
		catch (final Exception e)
		{
		}
	}

	private void importPassiveSkillTree(final EvolutionStatus evolutionStatus, final WebDriverWait wait2)
	{
		final WebDriverWait wait = new WebDriverWait(driver, 10);
		final WebElement element = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#footer-import-button")));
		try
		{
			element.click();
		}
		catch (final Exception e)
		{
			if (e.getMessage().contains("backdrop"))
			{
				sleep(1000);
				closeBackdrop();
				sleep(1000);
				element.click();
			}
			else
			{
				throw e;
			}
		}
		final WebElement searchBox = driver.findElement(By.id("passive-tree-decode"));
		searchBox.sendKeys(evolutionStatus.getCharacter().getUrl());
		final WebElement button = driver.findElement(By.id("passive-tree-decode-button"));
		button.click();
		closeBackdrop();
	}

	private void closeBackdrop()
	{
		try
		{
			final WebElement backdrop = driver.findElement(By.className("backdrop"));
			if (backdrop.isEnabled())
			{
				System.out.println("close backdrop");
				backdrop.click();
			}
			else
			{
				System.out.println("backdrop not open");
			}
		}
		catch (final Exception e)
		{
		}
	}

	private void closeAds(final By className)
	{
		try
		{
			final List<WebElement> ads = driver.findElements(className);

			// WebElement ad = driver.findElement(By.partialLinkText("Close
			// Ad"));
			// WebElement element =
			// wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#footer-import-button")));
			for (final WebElement ad : ads)
			{
				try
				{
					ad.click();
				}
				catch (final Exception e)
				{
				}
			}
		}
		catch (final Exception e)
		{
		}
	}

	@Override
	public void stop()
	{
		driver.quit();
	}

	void sleep(final int millis)
	{
		try
		{
			Thread.sleep(millis);
		}
		catch (final Exception e)
		{
		}
	}
}
