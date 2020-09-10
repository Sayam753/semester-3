import java.util.Random;
class Sol_Tut01_01_18_158{
    public static int getIndex(String charSet, char c, int len)
    {
        for(int i=0;i<len;i++)
        {
            if (charSet.charAt(i) == c)
            {
                return i;
            }
        }
        return -1;
    }
    public static void main(String args[])
    {
        Random rand = new Random();
        int len = 150 + Math.abs((rand.nextInt() % 51));
        String charSet="ABCDEFGHIJKLMNOPQRSTUVWXVZ" + "abcdefghijklmnopqrstuvwxyz" + "0123456789";
        StringBuilder forString = new StringBuilder(len);
        for(int i=0;i<len;i++)
        {
            int index = (int) Math.abs((rand.nextInt()) % charSet.length());
            forString.append(charSet.charAt(index));
        }
        String s = forString.toString();
        System.out.println("My random string is " + s);
        StringBuilder forEncoding = new StringBuilder();
        for(int i=0;i<len;i++)
        {
            int index = getIndex(charSet, s.charAt(i), charSet.length());
            if (index == charSet.length()-1)
            {
                index=-1;
            }
            forEncoding.append(charSet.charAt(index+1));
        }
        String encodedString = forEncoding.toString();
        System.out.println("My encoded string is " + encodedString);

        StringBuilder forDecoding = new StringBuilder();
        for(int i=0;i<len;i++)
        {
            int index = getIndex(charSet, encodedString.charAt(i), charSet.length());
            if (index == 0)
            {
                index=charSet.length();
            }
            forDecoding.append(charSet.charAt(index-1));
        }
        
        String decodedString = forDecoding.toString();
        System.out.println("My decoded string is " + decodedString);
    }
}