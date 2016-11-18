package edu.upc.eetac.dsa;

import java.lang.reflect.InvocationTargetException;

/**
 * Hello world!
 *
 */
public class MainP3
{
    public static void main( String[] args ) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        User user1 = new User(12,"oriol","bcn");
        User user2 = new User(111245,"lolo","tgn");
        user2.insertUser();
    }
}
