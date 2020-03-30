package se.nrm.dina.loan.web.controllers;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author idali
 */
@Named("about")
@ApplicationScoped
@Slf4j
public class About implements Serializable {
  
  private final String titleEn = "About the Information/Loan Request Form";
  private final String titleSv = "Om Informations-/Låneansökan";
  
  private final String text1Sv = "Detta ansökansformulär är till för att göra informations-, foto eller låneförfrågningar av föremål från våra samlingar mer kompletta och lättarbetade. Du som sökande får direkt ett kvitto på att förfrågan mottagits, samtidigt som det underlättar för våra samlingsansvariga att hantera ärendet.";
  private final String text1En = "This request form is for making information, photo or loan requests of specimens from our collections more complete and easy to handle by our staff. For the borrower this will make the handling time shorter, and it will make the value of the request easier to assess for our collection managers.";

  private final String text2Sv = "Ansökanssystemet är uppbyggt som ett frågeformulär med informationsfält, och inkluderar möjlighet att ladda upp informationsfiler som är av nytta för vår bedömning. Formuläret bör svaras på i bästa mån, för att snabba på hanteringen av din ansökan. Den integrerar sökning mot NRM's samlingsdatabas (DINA-Specify) för lätt åtkomst av föremålsinformation, samt mot GBIF för information om taxonomiska namn.";
  private final String text2En = "The request form is built like a questionnaire including information fields and with ability to upload information files that can be of use for our assessment. The form should for fastest handling be filed and answered as completely as possible. It integrates searches against our collection database (DINA-Specify) for easy access to specimen information, as well as against GBIF for taxonomic information.";    
          
  private final String text3Sv = "Formuläret år utvecklad av Enheten för Zoologi samt Enheten för Bioinformatik.";
  private final String text3En = "The request form is developed by the Department of Zoology and the Department of Bioinformatics."; 
  
  private final String text4Sv = "Om du har några frågor om formuläret eller dess användande, eller upplever";        
  private final String text4En  = "If you have any questions about the use of this service, or if you experience any";
          
  private final String text5Sv = "med användandet, tveka inte att kontakta vår";
  private final String text5En = "with the request form don't hesitate to contact our";
   
  private final String problemsSv = "problem";
  private final String problemsEn = "problems"; 
   
  private final String webmasterSv = "web-ansvariga";
  private final String webmasterEn = "webmaster"; 
  
  @Inject
  private Languages language;
 
  public String getProblems() {
    return language.isIsSwedish() ? problemsSv : problemsEn;
  }
  public String getWebMaster() {
    return language.isIsSwedish() ? webmasterSv : webmasterEn;
  }
   
  public String getText1() {
    return language.isIsSwedish() ? text1Sv : text1En;
  }
     
  public String getText2() {
    return language.isIsSwedish() ? text2Sv : text2En;
  }
    
  public String getText3() {
    return language.isIsSwedish() ? text3Sv : text3En;
  }
  
  public String getText4() {
    return language.isIsSwedish() ? text4Sv : text4En;
  }
  
  public String getText5() {
    return language.isIsSwedish() ? text5Sv : text5En;
  }
    
  public String getTitle() {
    return language.isIsSwedish() ? titleSv : titleEn; 
  }
  
}
