package beans;

public class Coding {

    public static String encoding(String data) {
       Integer encriptData=data.hashCode();
       return encriptData.toString();
    }

}
