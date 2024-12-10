package applicationlayer;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class SupplierPage {
	WebDriver driver;
	WebDriverWait wait;
	public SupplierPage(WebDriver driver)
	{
		this.driver=driver;
	}
	@FindBy(xpath="(//a[text()='Suppliers'])[2]")
	WebElement clickSuppierlink;
	@FindBy(xpath="(//span[@data-caption='Add'])[1]")
	WebElement clickAddIcon;
	@FindBy(name="x_Supplier_Number")
	WebElement SupplierNumber;
	@FindBy(name="x_Supplier_Name")
	WebElement SupplierName;
	@FindBy(name="x_Address")
	WebElement Address;
	@FindBy(name="x_City")
	WebElement City;
	@FindBy(name="x_Country")
	WebElement Country;
	@FindBy(name="x_Contact_Person")
	WebElement ContactPerson;
	@FindBy(name="x_Phone_Number")
	WebElement PhoneNumber;
	@FindBy(name="x__Email")
	WebElement Email;
	@FindBy(name="x_Mobile_Number")
	WebElement MobileNumber;
	@FindBy(name="x_Notes")
	WebElement Notes;
	@FindBy(name="btnAction")
	WebElement ClickAddBtn;
	@FindBy(xpath="//button[contains(text(),'OK!')]")
	WebElement clickConfirmOkBtn;
	@FindBy(xpath="(//button[text()='OK'])[6]")
	WebElement clickAlertOkBtn;
	@FindBy(xpath="//span[@data-caption='Search']")
	WebElement clickSearchPanelBtn;
	@FindBy(name="psearch")
	WebElement SearchTextbox;
	@FindBy(name="btnsubmit")
	WebElement SearchBtn;
	@FindBy(xpath = "//table[@class='table ewTable']/tbody/tr[1]/td[6]/div/span/span")
	WebElement webTable;
	//method for supplier creation
	public boolean addSupplier(String SupplierName,String Address,String City,String Country,
			String contactPerson,String PhoneNumber,String email,String MobileNumber,String Notes) throws Throwable
	{
		Actions ac = new Actions(driver);
		ac.moveToElement(this.clickSuppierlink).click().perform();
		Thread.sleep(2000);
		ac.moveToElement(this.clickAddIcon).click().perform();
		Thread.sleep(2000);
		String Exp_Data = this.SupplierNumber.getAttribute("value");
		this.SupplierName.sendKeys(SupplierName);
		this.Address.sendKeys(Address);
		this.City.sendKeys(City);
		this.Country.sendKeys(Country);
		this.ContactPerson.sendKeys(contactPerson);
		this.PhoneNumber.sendKeys(PhoneNumber);
		this.Email.sendKeys(email);
		this.MobileNumber.sendKeys(MobileNumber);
		this.Notes.sendKeys(Notes);
		this.ClickAddBtn.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		this.clickConfirmOkBtn.click();
		Thread.sleep(2000);
		this.clickAlertOkBtn.click();
		Thread.sleep(2000);
		if(!this.SearchTextbox.isDisplayed())
			this.clickSearchPanelBtn.click();
		this.SearchTextbox.clear();
		this.SearchTextbox.sendKeys(Exp_Data);
		this.SearchBtn.click();
		String Act_Data =webTable.getText();
		if(Act_Data.equals(Exp_Data))
		{
			Reporter.log("Add Supplier is Success:::"+Exp_Data+"      "+Act_Data,true);
			return true;
		}
		else
		{
			Reporter.log("Add Supplier is Fail:::"+Exp_Data+"      "+Act_Data,true);
			return false;
		}
		
	}

}
