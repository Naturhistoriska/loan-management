package se.nrm.dina.mongodb.util;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 *
 * @author idali
 */
public class Util {

  public static <T> Stream<T> stream(Iterable<T> iterable) {
    return StreamSupport.stream(Spliterators.spliteratorUnknownSize((iterable.iterator()), 
            Spliterator.ORDERED), false);
  }
}
