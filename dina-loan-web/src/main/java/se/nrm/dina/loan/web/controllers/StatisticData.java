package se.nrm.dina.loan.web.controllers;
 
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;
import se.nrm.dina.loan.web.logic.MongoService;
import se.nrm.dina.loan.web.vo.MapVO;

/**
 *
 * @author idali
 */
@Named("statistic")
@SessionScoped
@Slf4j
public class StatisticData implements Serializable {

  @Inject
  private MongoService mongo;

  private int currentYear;
  private Map<String, Integer> statisticMap;
  private List<MapVO> mapList;
  private HashMap<String, String> mapData;
  private final String statisticViewPath = "/pages/statistic.xhtml";
   
  public StatisticData() {

  }

  @PostConstruct
  public void init() {
    log.info("init");
    currentYear = LocalDate.now().getYear(); 
    statisticMap = new HashMap<>();
    statisticMap = mongo.getStatisticData();
    
    Map<String, Integer> map = mongo.getStaticDataForMap();
    mapList = new ArrayList<>();
    map.entrySet().stream().forEach((entry) -> {
      mapList.add(new MapVO(entry.getKey(), String.valueOf(entry.getValue())));
    }); 
    setMapList(mapList);
  }
   
  public HashMap<String, String> getMapData() {  
    return mapData;
  }

  public void setMapData(HashMap<String, String> mapData) { 
    this.mapData = mapData;
  } 
  
  public void setMapList(List<MapVO> mapList) { 
    this.mapList = mapList;
  }
  
  public void updateStaticsticData() {
    log.info("updateStaticsticData");
    init(); 
  }
  
  public List<MapVO> getMapList() { 
    return mapList;
  }
    
  public int getSecientificTotalCount() {
    return statisticMap.get("sc");
  }

  public int getEducationalTotalCount() {
    return statisticMap.get("ed");
  }

  public int getOtherTotalCount() {
    return statisticMap.get("other");
  }

  public int getSecientificYearCount() {
    return statisticMap.get("sc_year");
  }

  public int getEducationalYearCount() {
    return statisticMap.get("ed_year");
  }

  public int getOtherYearCount() {
    return statisticMap.get("other_year");
  }
   
  public String getStatisticViewPath() {
    return statisticViewPath;
  }

  public int getCurrentYear() {
    return currentYear;
  } 
}
