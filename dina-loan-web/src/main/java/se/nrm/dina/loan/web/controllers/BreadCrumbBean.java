package se.nrm.dina.loan.web.controllers;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.model.menu.DefaultMenuItem;  
import se.nrm.dina.loan.web.util.BreadCrumbElement;
import se.nrm.dina.loan.web.util.LoanPurpose;

/**
 *
 * @author idali
 */
@Named(value = "breadcrumb")
@SessionScoped
@Slf4j
public class BreadCrumbBean implements Serializable { 
  private final String UNACTIVE_TAB = "unactiveTab";
  private final String ACTIVE_TAB = "activeTab";

  private final DefaultMenuItem homeItem;
  private final DefaultMenuItem onlineFormItem;
  private final DefaultMenuItem page1Item;
  private final DefaultMenuItem page2Item;
  private final DefaultMenuItem page3Item;
  private final DefaultMenuItem page4Item;
  private final DefaultMenuItem page5Item;
  private final DefaultMenuItem page6Item;
  private final DefaultMenuItem page7Item;
  private final DefaultMenuItem page8Item;
  private final DefaultMenuItem page9Item;

  private DefaultMenuItem activeItem;

  private boolean navigationPathEnabled;

  @Inject
  private Languages language;

  public BreadCrumbBean() {

    homeItem = new DefaultMenuItem();
    onlineFormItem = new DefaultMenuItem();
    page1Item = new DefaultMenuItem();
    page2Item = new DefaultMenuItem();
    page3Item = new DefaultMenuItem();
    page4Item = new DefaultMenuItem();
    page5Item = new DefaultMenuItem();
    page6Item = new DefaultMenuItem();
    page7Item = new DefaultMenuItem();
    page8Item = new DefaultMenuItem();
    page9Item = new DefaultMenuItem();

    homeItem.setId(BreadCrumbElement.Home.getText());
    homeItem.setValue(BreadCrumbElement.Home.getMainMenuTextByLocale(false));
    homeItem.setDisabled(false);
    homeItem.setStyle(UNACTIVE_TAB);

    onlineFormItem.setId(BreadCrumbElement.OnlineForm.getText());
    onlineFormItem.setValue(BreadCrumbElement.OnlineForm.getMainMenuTextByLocale(false));
    onlineFormItem.setDisabled(true);
    onlineFormItem.setStyle(UNACTIVE_TAB);

    page1Item.setValue(BreadCrumbElement.Page1.getMainMenuTextByLocale(false));
    page1Item.setId(BreadCrumbElement.Page1.getText());
    page1Item.setDisabled(true);
    page1Item.setStyle(UNACTIVE_TAB);

    page2Item.setValue(BreadCrumbElement.Page2.getMainMenuTextByLocale(false));
    page2Item.setId(BreadCrumbElement.Page2.getText());
    page2Item.setDisabled(true);
    page2Item.setStyle(UNACTIVE_TAB);

    page3Item.setValue(BreadCrumbElement.Page3.getMainMenuTextByLocale(false));
    page3Item.setId(BreadCrumbElement.Page3.getText());
    page3Item.setDisabled(true);
    page3Item.setStyle(UNACTIVE_TAB);

    page4Item.setValue(BreadCrumbElement.Page4.getMainMenuTextByLocale(false));
    page4Item.setId(BreadCrumbElement.Page4.getText());
    page4Item.setDisabled(true);
    page4Item.setStyle(UNACTIVE_TAB);

    page5Item.setValue(BreadCrumbElement.Page5.getMainMenuTextByLocale(false));
    page5Item.setId(BreadCrumbElement.Page5.getText());
    page5Item.setDisabled(true);
    page5Item.setStyle(UNACTIVE_TAB);

    page6Item.setValue(BreadCrumbElement.Page6.getMainMenuTextByLocale(false));
    page6Item.setId(BreadCrumbElement.Page6.getText());
    page6Item.setDisabled(true);
    page6Item.setStyle(UNACTIVE_TAB);

    page7Item.setValue(BreadCrumbElement.Page7.getMainMenuTextByLocale(false));
    page7Item.setId(BreadCrumbElement.Page7.getText());
    page7Item.setDisabled(true);
    page7Item.setStyle(UNACTIVE_TAB);

    page8Item.setValue(BreadCrumbElement.Page8.getMainMenuTextByLocale(false));
    page8Item.setId(BreadCrumbElement.Page8.getText());
    page8Item.setDisabled(true);
    page8Item.setStyle(UNACTIVE_TAB);

    page9Item.setValue(BreadCrumbElement.Page9.getMainMenuTextByLocale(false));
    page9Item.setId(BreadCrumbElement.Page9.getText());
    page9Item.setDisabled(true);
    page9Item.setStyle(UNACTIVE_TAB);

    navigationPathEnabled = false;
    activeItem = homeItem;
  }

  /**
   * To reset navigation path to home page
   */
  public void resetNavigationPathToHomePage() {
    log.info("resetNavigationPathToHomePage");

    homeItem.setStyle(UNACTIVE_TAB);
    activeItem = homeItem;

    onlineFormItem.setStyle(UNACTIVE_TAB);
    page1Item.setStyle(UNACTIVE_TAB);
    page2Item.setStyle(UNACTIVE_TAB);
    page3Item.setStyle(UNACTIVE_TAB);
    page4Item.setStyle(UNACTIVE_TAB);
    page5Item.setStyle(UNACTIVE_TAB);
    page6Item.setStyle(UNACTIVE_TAB);
    page7Item.setStyle(UNACTIVE_TAB);
    page8Item.setStyle(UNACTIVE_TAB);
    page9Item.setStyle(UNACTIVE_TAB);

    onlineFormItem.setDisabled(true);
    page1Item.setDisabled(true);
    page2Item.setDisabled(true);
    page3Item.setDisabled(true);
    page4Item.setDisabled(true);
    page5Item.setDisabled(true);
    page6Item.setDisabled(true);
    page7Item.setDisabled(true);
    page8Item.setDisabled(true);
    page9Item.setDisabled(true);

    onlineFormItem.setRendered(true);
    page1Item.setRendered(true);
    page2Item.setRendered(true);
    page3Item.setRendered(true);
    page4Item.setRendered(true);
    page5Item.setRendered(true);
    page6Item.setRendered(true);
    page7Item.setRendered(true);
    page8Item.setRendered(true);
    page9Item.setRendered(true);

    boolean isSwedish = language.isIsSwedish();
    page1Item.setValue(BreadCrumbElement.Page1.getMainMenuTextByLocale(isSwedish));
    page2Item.setValue(BreadCrumbElement.Page2.getMainMenuTextByLocale(isSwedish));
    page3Item.setValue(BreadCrumbElement.Page3.getMainMenuTextByLocale(isSwedish));
    page4Item.setValue(BreadCrumbElement.Page4.getMainMenuTextByLocale(isSwedish));
    page5Item.setValue(BreadCrumbElement.Page5.getMainMenuTextByLocale(isSwedish));
    page6Item.setValue(BreadCrumbElement.Page6.getMainMenuTextByLocale(isSwedish));
    page7Item.setValue(BreadCrumbElement.Page7.getMainMenuTextByLocale(isSwedish));
    page8Item.setValue(BreadCrumbElement.Page8.getMainMenuTextByLocale(isSwedish));
    page9Item.setValue(BreadCrumbElement.Page9.getMainMenuTextByLocale(isSwedish));

    navigationPathEnabled = false;
  }

  public void setPrevious(DefaultMenuItem item) {
    log.info("setPrevious");

    navigationPathEnabled = true;

    item.setStyle(UNACTIVE_TAB);
    item.setDisabled(false);
    setPreviousItem(item);
  }

  public void setNext(DefaultMenuItem item) { 
    log.info("setNext" );

    if (item == null) {
      resetNavigationPathToHomePage();
    } else {
      navigationPathEnabled = true; 
      item.setStyle(UNACTIVE_TAB);
      item.setDisabled(false);
      setNextItem(item);
    }
  }

  private void setNextItem(DefaultMenuItem item) {
    log.info("setNextItem : {}", item.getId());
    switch (item.getId()) {
      case "home":
        onlineFormItem.setDisabled(true);
        onlineFormItem.setStyle(ACTIVE_TAB);
        activeItem = onlineFormItem;
        break;
      case "onlineform":
        page1Item.setDisabled(true);
        page1Item.setStyle(ACTIVE_TAB);
        activeItem = page1Item;
        break;
      case "page1":
        page2Item.setDisabled(true);
        page2Item.setStyle(ACTIVE_TAB);
        activeItem = page2Item;
        break;
      case "page2":
        page3Item.setDisabled(true);
        page3Item.setStyle(ACTIVE_TAB);
        activeItem = page3Item;
        break;
      case "page3":
        page4Item.setDisabled(true);
        page4Item.setStyle(ACTIVE_TAB);
        activeItem = page4Item;
        break;
      case "page4":
        page5Item.setDisabled(true);
        page5Item.setStyle(ACTIVE_TAB);
        activeItem = page5Item;
        break;
      case "page5":
        page6Item.setDisabled(true);
        page6Item.setStyle(ACTIVE_TAB);
        activeItem = page6Item;
        break;
      case "page6":
        page7Item.setDisabled(true);
        page7Item.setStyle(ACTIVE_TAB);
        activeItem = page7Item;
        break;
      case "page7":
        page8Item.setDisabled(true);
        page8Item.setStyle(ACTIVE_TAB);
        activeItem = page8Item;
        break;
      case "page8":
        page9Item.setDisabled(true);
        page9Item.setStyle(ACTIVE_TAB);
        activeItem = page9Item;
        break;
      case "page9":
        break;
    }
  }
 
  public void setNotImplementDepartmentPath(boolean isImplemented) { 
    log.info("setNotImplementDepartmentPath : {}", isImplemented);

    if (isImplemented) {
      page1Item.setValue(BreadCrumbElement.Page1.getMainMenuTextByLocale(language.isIsSwedish()));
      setMainPathValue();
    } else {
      page1Item.setValue(BreadCrumbElement.Page1b.getMainMenuTextByLocale(language.isIsSwedish()));

    }
    page2Item.setRendered(isImplemented);
    page3Item.setRendered(isImplemented);
    page4Item.setRendered(isImplemented);
    page5Item.setRendered(isImplemented);
    page6Item.setRendered(isImplemented);
    page7Item.setRendered(isImplemented);
    page8Item.setRendered(isImplemented);
    page9Item.setRendered(isImplemented);

    page1Item.setDisabled(true);
    page2Item.setDisabled(true);
    page3Item.setDisabled(true);
    page4Item.setDisabled(true);
    page5Item.setDisabled(true);
    page6Item.setDisabled(true);
    page7Item.setDisabled(true);
    page8Item.setDisabled(true);
    page9Item.setDisabled(true);
  }

  /**
   * Set navigation path to photo text
   */
  public void setPhotoElement() {
    page6Item.setValue(BreadCrumbElement.Page6b.getMainMenuTextByLocale(language.isIsSwedish()));
    page6Item.setRendered(true);
    page7Item.setRendered(true);
    page3Item.setRendered(true);
  }

  /**
   * Set navigation path to photo text
   */
  public void setInformationElement() {
    page3Item.setRendered(false);
    page6Item.setRendered(false);
    page7Item.setRendered(false);
  }

  /**
   * Set navigation path to physical text
   */
  public void setPhiscalElement() {
    page6Item.setValue(BreadCrumbElement.Page6.getMainMenuTextByLocale(language.isIsSwedish()));
    page6Item.setRendered(true);
    page3Item.setRendered(true);
    page7Item.setRendered(true);
  }

  /**
   * To change navigation path language
   *
   * @param isSwedish
   * @param isImplemented -- indecate if the department is implemented or not
   * @param isPhoto
   * @param numOfPages
   */
  public void resetLocale(boolean isSwedish, boolean isImplemented, boolean isPhoto, int numOfPages) { 
    log.info("resetLocale : {}", isSwedish);

    homeItem.setValue(BreadCrumbElement.Home.getMainMenuTextByLocale(isSwedish));
    onlineFormItem.setValue(BreadCrumbElement.OnlineForm.getMainMenuTextByLocale(isSwedish));

    page1Item.setValue(isImplemented ? 
            BreadCrumbElement.Page1.getMainMenuTextByLocale(isSwedish) : 
            BreadCrumbElement.Page1b.getMainMenuTextByLocale(isSwedish));
    switch (numOfPages) {
      case 9:
        page2Item.setValue(BreadCrumbElement.Page2.getMainMenuTextByLocale(isSwedish));
        page3Item.setValue(BreadCrumbElement.Page3.getMainMenuTextByLocale(isSwedish));
        page4Item.setValue(BreadCrumbElement.Page4.getMainMenuTextByLocale(isSwedish));
        page5Item.setValue(BreadCrumbElement.Page5.getMainMenuTextByLocale(isSwedish));
        if (isPhoto) {
          page6Item.setValue(BreadCrumbElement.Page6b.getMainMenuTextByLocale(isSwedish));
        } else {
          page6Item.setValue(BreadCrumbElement.Page6.getMainMenuTextByLocale(isSwedish));
        } page7Item.setValue(BreadCrumbElement.Page7.getMainMenuTextByLocale(isSwedish));
        page8Item.setValue(BreadCrumbElement.Page8.getMainMenuTextByLocale(isSwedish));
        page9Item.setValue(BreadCrumbElement.Page9.getMainMenuTextByLocale(isSwedish));
        break;
      case 7:
        page2Item.setValue(BreadCrumbElement.Page2.getEduMenuTextByLocale(isSwedish));
        page3Item.setValue(BreadCrumbElement.Page3.getEduMenuTextByLocale(isSwedish));
        page4Item.setValue(BreadCrumbElement.Page4.getEduMenuTextByLocale(isSwedish));
        page5Item.setValue(BreadCrumbElement.Page5.getEduMenuTextByLocale(isSwedish));
        page6Item.setValue(BreadCrumbElement.Page6.getEduMenuTextByLocale(isSwedish));
        page7Item.setValue(BreadCrumbElement.Page7.getEduMenuTextByLocale(isSwedish));
        break;
      default:
        page2Item.setValue(BreadCrumbElement.Page2.getArtMenuTextByLocale(isSwedish));
        page3Item.setValue(BreadCrumbElement.Page3.getArtMenuTextByLocale(isSwedish));
        page4Item.setValue(BreadCrumbElement.Page4.getArtMenuTextByLocale(isSwedish));
        page5Item.setValue(BreadCrumbElement.Page5.getArtMenuTextByLocale(isSwedish));
        page6Item.setValue(BreadCrumbElement.Page6.getArtMenuTextByLocale(isSwedish));
        break;
    }
  }

  public void resetNavigationPath(LoanPurpose loanPurpose) { 
    log.info("resetNavigationPath : {}", loanPurpose);

    boolean isSwedish = language.isIsSwedish();

    if (loanPurpose.isEducation()) {
      page3Item.setRendered(true);
      page6Item.setRendered(true);
      page7Item.setRendered(true);
      page8Item.setRendered(false);
      page9Item.setRendered(false);

      page2Item.setValue(BreadCrumbElement.Page2.getEduMenuTextByLocale(isSwedish));
      page3Item.setValue(BreadCrumbElement.Page3.getEduMenuTextByLocale(isSwedish));
      page4Item.setValue(BreadCrumbElement.Page4.getEduMenuTextByLocale(isSwedish));
      page5Item.setValue(BreadCrumbElement.Page5.getEduMenuTextByLocale(isSwedish));
      page6Item.setValue(BreadCrumbElement.Page6.getEduMenuTextByLocale(isSwedish));
      page7Item.setValue(BreadCrumbElement.Page7.getEduMenuTextByLocale(isSwedish));
    } else if (loanPurpose.isCommercial()) {
      page3Item.setRendered(true);
      page6Item.setRendered(true);
      page7Item.setRendered(false);
      page8Item.setRendered(false);
      page9Item.setRendered(false);

      page2Item.setValue(BreadCrumbElement.Page2.getArtMenuTextByLocale(isSwedish));
      page3Item.setValue(BreadCrumbElement.Page3.getArtMenuTextByLocale(isSwedish));
      page4Item.setValue(BreadCrumbElement.Page4.getArtMenuTextByLocale(isSwedish));
      page5Item.setValue(BreadCrumbElement.Page5.getArtMenuTextByLocale(isSwedish));
      page6Item.setValue(BreadCrumbElement.Page6.getArtMenuTextByLocale(isSwedish));
    } else {
      page7Item.setRendered(true);
      page8Item.setRendered(true);
      page9Item.setRendered(true);

      page2Item.setValue(BreadCrumbElement.Page2.getMainMenuTextByLocale(isSwedish));
      page3Item.setValue(BreadCrumbElement.Page3.getMainMenuTextByLocale(isSwedish));
      page4Item.setValue(BreadCrumbElement.Page4.getMainMenuTextByLocale(isSwedish));
      page5Item.setValue(BreadCrumbElement.Page5.getMainMenuTextByLocale(isSwedish));
      page6Item.setValue(BreadCrumbElement.Page6.getMainMenuTextByLocale(isSwedish));
      page7Item.setValue(BreadCrumbElement.Page7.getMainMenuTextByLocale(isSwedish));
      page8Item.setValue(BreadCrumbElement.Page8.getMainMenuTextByLocale(isSwedish));
      page9Item.setValue(BreadCrumbElement.Page9.getMainMenuTextByLocale(isSwedish));
    }

    page2Item.setDisabled(true);
    page3Item.setDisabled(true);
    page4Item.setDisabled(true);
    page5Item.setDisabled(true);
    page6Item.setDisabled(true);
    page7Item.setDisabled(true);
    page8Item.setDisabled(true);
    page9Item.setDisabled(true);
  }

  public void enableManuItem(DefaultMenuItem item) {
    log.info("disableManuItem : {}", item.getId());
    item.setStyle(UNACTIVE_TAB);
    item.setDisabled(false);
  }

  public void disableManuItem(DefaultMenuItem item) {
    log.info("disableManuItem : {}", item.getId());
    item.setStyle(UNACTIVE_TAB);
    item.setDisabled(false);
  }

  public void setManuItem(DefaultMenuItem item) { 
    log.info("setManuItem : {}", item.getId());
    item.setStyle(ACTIVE_TAB);
    item.setDisabled(true);
    disableOldActiveElement();
    activeItem = item;
  }
 
  public void setManuItem(DefaultMenuItem item, int numOfPages) {

    item.setStyle(ACTIVE_TAB);
    item.setDisabled(true);

    switch (activeItem.getId()) {
      case "home":
        navigationPathEnabled = false;
      case "onlineform":
        onlineFormItem.setStyle(UNACTIVE_TAB);
        onlineFormItem.setDisabled(false);
        break;
      case "page1":
        page1Item.setStyle(UNACTIVE_TAB);
        page1Item.setDisabled(false);
        break;
      case "page2":
        page2Item.setStyle(UNACTIVE_TAB);
        page2Item.setDisabled(false);
        break;
      case "page3":
        page3Item.setStyle(UNACTIVE_TAB);
        page3Item.setDisabled(false);
        break;
      case "page4":
        page4Item.setStyle(UNACTIVE_TAB);
        page4Item.setDisabled(false);
        break;
      case "page5":
        page5Item.setStyle(UNACTIVE_TAB);
        page5Item.setDisabled(false);
        break;
      case "page6":
        page6Item.setStyle(UNACTIVE_TAB);
        page6Item.setDisabled(false);
        break;
      case "page7":
        page7Item.setStyle(UNACTIVE_TAB);
        page7Item.setDisabled(false);
        break;
      case "page8":
        page8Item.setStyle(UNACTIVE_TAB);
        page8Item.setDisabled(false);
        break;
      case "page9":
        page9Item.setStyle(UNACTIVE_TAB);
        page9Item.setDisabled(false);
        break;
    }
    activeItem = item;
  }

  public DefaultMenuItem getHomeItem() {
    return homeItem;
  }

  public DefaultMenuItem getOnlineFormItem() {
    return onlineFormItem;
  }

  public DefaultMenuItem getPage1Item() {
    return page1Item;
  }

  public DefaultMenuItem getPage2Item() {
    return page2Item;
  }

  public DefaultMenuItem getPage3Item() {
    return page3Item;
  }

  public DefaultMenuItem getPage4Item() {
    return page4Item;
  }

  public DefaultMenuItem getPage5Item() {
    return page5Item;
  }

  public DefaultMenuItem getPage6Item() {
    return page6Item;
  }

  public DefaultMenuItem getPage7Item() {
    return page7Item;
  }

  public DefaultMenuItem getPage8Item() {
    return page8Item;
  }

  public DefaultMenuItem getPage9Item() {
    return page9Item;
  }

  public boolean isNavigationPathEnabled() {
    return navigationPathEnabled;
  }

  public void setNavigationPathEnabled(boolean navigationPathEnabled) {
    this.navigationPathEnabled = navigationPathEnabled;
  }

  private void setMainPathValue() {
    boolean isSwedish = language.isIsSwedish();
    page1Item.setValue(BreadCrumbElement.Page1.getMainMenuTextByLocale(isSwedish));
    page2Item.setValue(BreadCrumbElement.Page2.getMainMenuTextByLocale(isSwedish));
    page3Item.setValue(BreadCrumbElement.Page3.getMainMenuTextByLocale(isSwedish));
    page4Item.setValue(BreadCrumbElement.Page4.getMainMenuTextByLocale(isSwedish));
    page5Item.setValue(BreadCrumbElement.Page5.getMainMenuTextByLocale(isSwedish));
    page6Item.setValue(BreadCrumbElement.Page6.getMainMenuTextByLocale(isSwedish));
    page7Item.setValue(BreadCrumbElement.Page7.getMainMenuTextByLocale(isSwedish));
    page8Item.setValue(BreadCrumbElement.Page8.getMainMenuTextByLocale(isSwedish));
    page9Item.setValue(BreadCrumbElement.Page9.getMainMenuTextByLocale(isSwedish));
  }
  
  private void setPreviousItem(DefaultMenuItem item) { 
    log.info("setPreviousItem : {}", item.getId());
    switch (item.getId()) {
      case "home":
      case "onlineform":
        navigationPathEnabled = false;
        break;
      case "page1":
        onlineFormItem.setDisabled(true);
        onlineFormItem.setStyle(ACTIVE_TAB);
        activeItem = onlineFormItem;
        break;
      case "page2":
        page1Item.setDisabled(true);
        page1Item.setStyle(ACTIVE_TAB);
        activeItem = page1Item;
        break;
      case "page3":
        page2Item.setDisabled(true);
        page2Item.setStyle(ACTIVE_TAB);
        activeItem = page2Item;
        break;
      case "page4":
        page3Item.setDisabled(true);
        page3Item.setStyle(ACTIVE_TAB);
        activeItem = page3Item;
        break;
      case "page5":
        page4Item.setDisabled(true);
        page4Item.setStyle(ACTIVE_TAB);
        activeItem = page4Item;
        break;
      case "page6":
        page5Item.setDisabled(true);
        page5Item.setStyle(ACTIVE_TAB);
        activeItem = page5Item;
        break;
      case "page7":
        page6Item.setDisabled(true);
        page6Item.setStyle(ACTIVE_TAB);
        activeItem = page6Item;
        break;
      case "page8":
        page7Item.setDisabled(true);
        page7Item.setStyle(ACTIVE_TAB);
        activeItem = page7Item;
        break;
      case "page9":
        page8Item.setDisabled(true);
        page8Item.setStyle(ACTIVE_TAB);
        activeItem = page8Item;
        break;
    }
  }
  
  private void disableOldActiveElement() {
    switch (activeItem.getId()) {
      case "home":
        resetNavigationPathToHomePage();
      case "onlineform":
        onlineFormItem.setStyle(UNACTIVE_TAB);
        onlineFormItem.setDisabled(false);
        break;
      case "page1":
        page1Item.setStyle(UNACTIVE_TAB);
        page1Item.setDisabled(false);
        break;
      case "page2":
        page2Item.setStyle(UNACTIVE_TAB);
        page2Item.setDisabled(false);
        break;
      case "page3":
        page3Item.setStyle(UNACTIVE_TAB);
        page3Item.setDisabled(false);
        break;
      case "page4":
        page4Item.setStyle(UNACTIVE_TAB);
        page4Item.setDisabled(false);
        break;
      case "page5":
        page5Item.setStyle(UNACTIVE_TAB);
        page5Item.setDisabled(false);
        break;
      case "page6":
        page6Item.setStyle(UNACTIVE_TAB);
        page6Item.setDisabled(false);
        break;
      case "page7":
        page7Item.setStyle(UNACTIVE_TAB);
        page7Item.setDisabled(false);
        break;
      case "page8":
        page8Item.setStyle(UNACTIVE_TAB);
        page8Item.setDisabled(false);
        break;
      case "page9":
        page9Item.setStyle(UNACTIVE_TAB);
        page9Item.setDisabled(false);
        break;
    }
  }
}
