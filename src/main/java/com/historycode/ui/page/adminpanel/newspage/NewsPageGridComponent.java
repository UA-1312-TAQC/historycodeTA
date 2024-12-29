package com.historycode.ui.page.adminpanel.newspage;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.historycode.ui.component.adminPanel.gridAdminPanel.BaseGridComponent;

import lombok.Getter;

@Getter
public class NewsPageGridComponent extends BaseGridComponent {
    public NewsPageGridComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    List<NewsRowComponent> newsRowComponents;

    public List<NewsRowComponent> getNewsRowComponents(){
        return newsRowComponents;
    }
}
