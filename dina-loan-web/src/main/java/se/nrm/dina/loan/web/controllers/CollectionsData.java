package se.nrm.dina.loan.web.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;
import se.nrm.dina.loan.web.logic.MongoService;
import se.nrm.dina.mongodb.loan.vo.Collection;

/**
 *
 * @author idali
 */
@ApplicationScoped
@Named("collectionsData")
@Slf4j
public class CollectionsData implements Serializable {
  
  private Map<String, List<Collection>> map; 
  private List<SelectItem> collectionItems;
  
  @Inject
  private MongoService mongo;
 
  public CollectionsData() {

  }

  @PostConstruct
  public void init() {
    if (map == null || map.isEmpty()) {
//      map = new LinkedHashMap<>();
      map = mongo.findAllScientificCollections();

      collectionItems = new ArrayList<>();
      map.entrySet().stream().map((entry) -> {
        SelectItemGroup group = new SelectItemGroup(entry.getKey());
        SelectItem[] list = new SelectItem[entry.getValue().size()];
        int count = 0;
        for (Collection value : entry.getValue()) {
          list[count] = new SelectItem(value.getName(), value.getName());
          count++;
        }
        group.setSelectItems(list);
        return group;
      }).forEach((group) -> {
        collectionItems.add(group);
      });
    }
  }

  public Map<String, List<Collection>> getMap() {
    return map;
  }

  public List<SelectItem> getCollectionItems() {
    log.info("getCollectionItems : {}", collectionItems);
    
    return collectionItems;
  } 
}
