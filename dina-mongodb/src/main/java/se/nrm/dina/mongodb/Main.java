package se.nrm.dina.mongodb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import se.nrm.dina.mongodb.loan.vo.Collection;
import se.nrm.dina.mongodb.loan.vo.Loan;

import org.jongo.Jongo;
import org.jongo.MongoCollection;
import se.nrm.dina.mongodb.loan.vo.Address;
import se.nrm.dina.mongodb.loan.vo.User;

/**
 *
 * @author idali
 */
public class Main {

  public static void main(String args[]) throws JsonProcessingException {

    ObjectMapper mapper = new ObjectMapper();
    MongoClient mongo = new MongoClient("mongo", 27017);

    DB db = mongo.getDB("loans");

    User user = new User();
    user.setFirstname("ida");
    user.setLastname("Li");
    Address address = new Address();
    address.setCity("stockholm");
    address.setCountry("sweden");
    user.setAddress(address);

    Loan loan = new Loan();
    loan.setLoanNumber("3344455");
    loan.setPurpose("teest");
    loan.setDepartment("zoo");
    loan.setId("123456");
    loan.setPrimaryUser(user);

    Jongo jongo = new Jongo(db);
    Collection collection = new Collection("ida", "email", "Insects", "manager", "Zoology");

    String jsonInString = mapper.writeValueAsString(loan);

    MongoCollection co = jongo.getCollection("loan");
    co.insert(jsonInString); 
  }
}
