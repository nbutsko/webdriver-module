package i_can_win.page;

import org.openqa.selenium.WebDriver;

public class PastebinCreatedPastePage extends AbstractPage{

    public PastebinCreatedPastePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected AbstractPage openPage() {
        throw new RuntimeException("You couldn't open this page without creation a new paste!");
    }
}
