package com.nttdata.stepsdefinitions;
;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static com.nttdata.core.DriverManager.getDriver;
import static com.nttdata.core.DriverManager.screenShot;
import com.nttdata.steps.LoginSteps;
import org.openqa.selenium.WebElement;


public class StoreStepDef {

    private WebDriver driv;

    @Given("estoy en la página de la tienda")
    public void estoyEnLaPáginaDeLaTienda() {
        driv=getDriver();
        driv.get("https://qalab.bensg.com/store");
        screenShot();
    }

    @And("me logueo con mi usuario {string} y clave {string}")
    public void meLogueoConMiUsuarioYClave(String username, String password) {
        LoginSteps loginSteps = new LoginSteps(driv);
        loginSteps.typeUser(username);
        loginSteps.typePassword(password);
        loginSteps.login();
        screenShot();

    }

    @When("navego a la categoria {string} y subcategoria {string}")
    public void navegoALaCategoriaClothesYSubcategoriaMen(String category, String subcategory) {
        driv.findElement(By.linkText(category)).click();
        driv.findElement(By.linkText(subcategory)).click();

    }

    @And("agrego {int} unidades del primer producto al carrito")
    public void agregoUnidadesDelPrimerProductoAlCarrito(Integer unidades) {
        WebElement producto = driv.findElement(By.cssSelector("selector_of_the_product"));
        producto.click();
        driv.findElement(By.id("quantity")).clear();
        driv.findElement(By.id("quantity")).sendKeys(unidades.toString());
        driv.findElement(By.id("addToCartButton")).click();

    }

    @Then("valido en el popup la confirmación del agregado")
    public void validoEnElPopupLaConfirmaciónDelAgregado() {
        WebElement popup = driv.findElement(By.id("confirmationPopup"));
        Assert.assertTrue(popup.isDisplayed());


    }

    @And("valido en el popup que el monto total sea calculado correctamente")
    public void validoEnElPopupQueElMontoTotalSeaCalculadoCorrectamente() {
        WebElement totalAmount = driv.findElement(By.id("totalAmount"));
        String expectedAmount = "expected_amount_based_on_calculation";
        Assert.assertEquals(expectedAmount, totalAmount.getText());


    }

    @When("finalizo la compra")
    public void finalizoLaCompra() {

        driv.findElement(By.id("checkoutButton")).click();

    }

    @Then("valido el título de la página del carrito")
    public void validoElTítuloDeLaPáginaDelCarrito() {
        String pageTitle = driv.getTitle();
        Assert.assertEquals("Carrito", pageTitle);
    }

    @And("vuelvo a validar el cálculo de precios en el carrito.")
    public void vuelvoAValidarElCálculoDePreciosEnElCarrito() {
        WebElement carritoTotal = driv.findElement(By.id("carritoTotal"));
        String expectedCarritoTotal = "expected_total_based_on_calculation";
        Assert.assertEquals(expectedCarritoTotal, carritoTotal.getText());
    }
}
