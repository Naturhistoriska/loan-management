package se.nrm.dina.loan.web.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import se.nrm.dina.mongodb.jdbc.MongoJDBC;
import se.nrm.dina.mongodb.loan.vo.Address;
import se.nrm.dina.loan.web.util.CountryMap; 
import se.nrm.dina.mongodb.loan.vo.Collection;
import se.nrm.dina.mongodb.loan.vo.Loan;

/**
 *
 * @author idali
 */
@Slf4j
public class MongoService implements Serializable {

  @Inject
  private MongoJDBC mongo;
  
  private final String loanRequestPrefix = "ReqNo";
  
  public void saveLoan(Loan loan) {
    log.info("saveLoan : {}", loan);
    mongo.save(loan);
  }
  
  public Collection findSelectedCollection(String selectedCollection) {
    return mongo.findCollection(selectedCollection, "name");
  }

  public Map<String, List<Collection>> findAllScientificCollections() {

    Map<String, List<Collection>> map = new TreeMap<>(); 
    List<Collection> collectionList = mongo.findAllScientificCollections(); 
    collectionList.stream()
            .forEach(c -> {
               List<Collection> list = new ArrayList<>();
              if (map.containsKey(c.getGroup())) {
                list = map.get(c.getGroup()); 
              }  
              list.add(c);
              map.put(c.getGroup(), list); 
            });
    return map;
  }
  
  public boolean isUUIDExist(UUID uuid) {
    return mongo.uuidExist(uuid);
  }
  
  public String findNextLoanId() {
    return loanRequestPrefix + String.format("%06d", mongo.getNextSequence()); 
  }

  public Map<String, Integer> getStatisticData() {
    log.info("getMapData");

    Map<String, Integer> map = new HashMap();

    Calendar now = Calendar.getInstance();
    int year = now.get(Calendar.YEAR);
    String fromDate = year + "-01-01";
    String endDate = year + "-12-01";

    map.put("sc_year", mongo.findCurrentYearCountByPurpose("Scientific purpose", fromDate, endDate));
    map.put("ed_year", mongo.findCurrentYearCountByPurpose("Education/Exhibition", fromDate, endDate));
    map.put("other_year", mongo.findCurrentYearCountByPurpose("Commercial/art/other", fromDate, endDate));

    map.put("sc", mongo.findTotalCountByPurpose("Scientific purpose"));
    map.put("ed", mongo.findTotalCountByPurpose("Education/Exhibition"));
    map.put("other", mongo.findTotalCountByPurpose("Commercial/art/other"));
    return map;
  }

  public Map<String, Integer> getStaticDataForMap() {
    log.info("getMapData");

    List<Loan> loans = mongo.findAllLoans();
    Map<String, Integer> map = new HashMap(); 
    loans.stream()
            .filter(loan -> loan.getPrimaryUser() != null)
            .filter(loan -> loan.getPrimaryUser().getAddress() != null)
            .forEach(loan -> {
              Address address = loan.getPrimaryUser().getAddress();
              String countryName = CountryMap.getMappingName(address.getCountry());
              int count = map.containsKey(countryName) ? map.get(countryName) + 1 : 1;
              map.put(countryName, count);
            });
    return map;
  } 
}