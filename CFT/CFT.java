package test_1;

import org.openqa.selenium.By;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class CFT {

//  private static FirefoxDriver browser;
    private static ChromeDriver browser;
    private static String data;
    private static String result;

    public static void main(String[] args) throws InterruptedException {
        browser = new ChromeDriver();
//      browser = new FirefoxDriver();
        browser.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        // Зайти на сайт
        browser.get("https://team.cft.ru/triangle/zadanie/triangle.html?token=1728e23a14ea49a7ae44bdc15f0aa40f");
        TimeUnit.SECONDS.sleep(1);

        /* Равнобедренный треугольник*/
        result = "Равнобедренный";  //Ожидаемый результат

        testForm(1, "0.4904", "0.4904", "0.7804");
        testForm(2, "0.4904", "0.7804", "0.4904");
        testForm(3, "0.7804", "0.4904", "0.4904");

        testForm(4, "4", "4", "6");
        testForm(5, "4", "6", "4");
        testForm(6, "6", "4", "4");

        testForm(7, "14", "14", "16");
        testForm(8, "14", "16", "14");
        testForm(9, "16", "14", "14");

        testForm(10, "514", "514", "516");
        testForm(11, "514", "516", "514");
        testForm(12, "516", "514", "514");

        testForm(13, "4514", "4514", "4516");
        testForm(14, "4514", "4516", "4514");
        testForm(15, "4516", "4514", "4514");

        testForm(16, "94514", "94514", "94516");
        testForm(17, "94514", "94516", "94514");
        testForm(18, "94516", "94514", "94514");

        /* Равносторонний треугольник*/
        result = "Равносторонний"; //Ожидаемый результат

        testForm(19, "0.89", "0.89", "0.89");
        testForm(20, "7", "7", "7");
        testForm(21, "6233", "6233", "6233");
        testForm(22, "78233", "78233", "78233");
        testForm(23, "987233", "987233", "987233");

        /* Прямоугольный треугольник*/
        result = "Прямоугольный"; //Ожидаемый результат

        testForm(24, "3", "4", "5");
        testForm(25, "3", "3", "4");
        testForm(26, "4", "3", "5");
        testForm(27, "4", "5", "3");
        testForm(28, "5", "3", "4");
        testForm(29, "5", "4", "3");


        /* Не треугольник */
        /* Наибольшая строна равна или больше сумме других сторон*/
        result = "Не треугольник"; //Ожидаемый результат

        testForm(30, "7", "5", "2");
        testForm(31, "7", "2", "5");
        testForm(32, "5", "7", "2");
        testForm(33, "5", "2", "7");
        testForm(34, "2", "7", "5");
        testForm(35, "2", "5", "7");


        browser.quit();
    }

    /*
     * Функция заполнения формы и получения результата
     */
    private static void testForm(int testRun, String one, String two, String three) throws InterruptedException {
        data = one + ", " + two + ", " + three;
        // ввести значение в поле Сторона А
        browser.findElement(By.xpath("//div[@id='content']/div/div/table/tbody/tr[1]/td[1]/input")).click();
        browser.findElement(By.xpath("//div[@id='content']/div/div/table/tbody/tr[1]/td[1]/input")).clear();
        browser.findElement(By.xpath("//div[@id='content']/div/div/table/tbody/tr[1]/td[1]/input")).sendKeys(one);

        // ввести значение в поле Сторона Б
        browser.findElement(By.xpath("//div[@id='content']/div/div/table/tbody/tr[2]/td[1]/input")).click();
        browser.findElement(By.xpath("//div[@id='content']/div/div/table/tbody/tr[2]/td[1]/input")).clear();
        browser.findElement(By.xpath("//div[@id='content']/div/div/table/tbody/tr[2]/td[1]/input")).sendKeys(two);

        // ввести значение в поле Сторона В
        browser.findElement(By.xpath("//div[@id='content']/div/div/table/tbody/tr[3]/td[1]/input")).click();
        browser.findElement(By.xpath("//div[@id='content']/div/div/table/tbody/tr[3]/td[1]/input")).clear();
        browser.findElement(By.xpath("//div[@id='content']/div/div/table/tbody/tr[3]/td[1]/input")).sendKeys(three);

        // получить результат
        browser.findElement(By.cssSelector("button.gwt-Button")).click();
        TimeUnit.SECONDS.sleep(1);


        whatsWrong(testRun, 1);
        whatsWrong(testRun, 2);
        whatsWrong(testRun, 3);

        TimeUnit.SECONDS.sleep(1);
    }

    /*
     * Функция ввывода ошибок в консоль
     */
    private static void whatsWrong(int testRun, int buttonClick) {
        String res = browser.findElement(By.cssSelector("div.answerLabel")).getText();

        if (!res.equals(result)) {
            String report = "Ошибка в #" + testRun + "." + buttonClick + ": Ожидаемый результат: " + result + ". Фактический результат: " + res + " (данные " + data + ")";
            System.out.println(report);
        }
    }
}

