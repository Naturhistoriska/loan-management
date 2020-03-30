package se.nrm.dina.email.model.loan;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List; 
import java.util.Map; 

/**
 *
 * @author idali
 */
public class MailAttachment {
    
    private static final String TEXT_1 = "The following loans are overdue:";
    
    public String prepareLoanAttachment(List<OverDueLoan> overDueLoans) {
       
        Map<String, List<OverDueLoan>> map = getBorrowerMap(overDueLoans);
        
        StringBuilder sb = new StringBuilder();  
        sb.append(TEXT_1); 
        sb.append("test"); 
            
        return sb.toString();
    }
    
    private Map<String, List<OverDueLoan>> getBorrowerMap(List<OverDueLoan> OverDueLoans) {
        Map<String, List<OverDueLoan>> map = new HashMap<>();
        
        OverDueLoans.forEach((loan) -> {
          Person person = loan.getBorrower();
          String key = "key";
          if(person != null) {
            key = person.getAgentEmail();
          }
          if(map.containsKey(key)) {
            List list = map.get(key);
            list.add(loan);
          } else {
            List<OverDueLoan> list = new ArrayList<>();
            list.add(loan);
            map.put(key, list);
          }
      });
        
        return map;
    }
}
