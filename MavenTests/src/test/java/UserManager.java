import org.apache.commons.validator.routines.EmailValidator;

import java.util.ArrayList;
import java.util.Collection;

public class UserManager {




    ArrayList<String> userStorage= new ArrayList<String>();




    public boolean addUser(String userEmail){

        if(!EmailValidator.getInstance().isValid(userEmail)){
           throw new IllegalArgumentException("Not a valid email");


        }

      return   userStorage.add(userEmail);
    }


    public String getUser(String userEmail){


        for(int i=0;i<userStorage.size();i++){

            String r=userStorage.get(i);

            if(r==userEmail){

                System.out.println(r);
                return r;




            }


        }
        return null;

    }


    public boolean deleteUser(final String userEmail){

        for(int i=0;i<userStorage.size();i++){

            String r=userStorage.get(i);

            if(r==userEmail){

              userStorage.remove(i);
              return true;





            }else{

                continue;
            }
        }

        return false;

    }








}
//assertSame() if pointing to the same value or not
//assertEqualsDeep() collections maps and sets check each key value pair

//assertEqualsNoOrder()=> for arrays regardless of order


