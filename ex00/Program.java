 class Program {
    public static void main (String [] args) {
        int number = 479598;
        int result = 0;
        String num = "" + number;
        
        for (int i = 0; i < num.length(); i++)
            result += num.charAt(i) - 48;
        System.out.println(result);
    }  
}